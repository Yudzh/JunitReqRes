package rest.service;

import io.restassured.response.Response;
import rest.error.ApiError;
import rest.pojos.Unknown;
import rest.pojos.UnknownData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UnknownService extends RestService {
    public UnknownService(String token) {
        super(token);
    }

    @Override
    String getBasePath() {
        return "unkown";
    }

    public Unknown getUnknownById(int id) {
        LOGGER.info(String.format("Getting unknown by %s id", id));

        Response response = given()
                .spec(REQ_SPEC)
                .basePath(String.format("%s/%d", getBasePath(), id))
                .when().get();

        try {
            return response.then().statusCode(200)
                    .extract().as(UnknownData.class).getUnknown();
        } catch (AssertionError e) {
            LOGGER.warn("Got an assertion error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public List<Unknown> getListOfUnknown() {
        Response response = given()
                .spec(REQ_SPEC)
                .when().get();

        try {
            return response.then().statusCode(200)
                    .extract().jsonPath().getList("data", Unknown.class);
        } catch (AssertionError e) {
            LOGGER.warn("Got an assertion error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }
}
