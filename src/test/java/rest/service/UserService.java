package rest.service;

import io.restassured.response.Response;
import rest.error.ApiError;
import rest.pojos.UserRequest;
import rest.pojos.UserResponse;
import rest.pojos.User;
import rest.pojos.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends RestService {
    public UserService(String token) {
        super(token);
    }

    @Override
    String getBasePath() {
        return "users";
    }

    public User getUserById(int id) {
        LOGGER.info(String.format("Getting user by %s id", id));

        Response response = given()
                .spec(REQ_SPEC)
                .basePath(String.format("%s/%d", getBasePath(), id))
                .when().get();

        try {
            return response.then().statusCode(200)
                    .extract().as(UserData.class).getUser();
        } catch (AssertionError e) {
            LOGGER.warn("Got an error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public List<User> getListOfUsers() {
        Response response = given()
                .spec(REQ_SPEC)
                .when().get();

        try {
            return response.then().statusCode(200)
                    .extract().jsonPath().getList("data", User.class);
        } catch (AssertionError e) {
            LOGGER.warn("Got an error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public UserResponse createUser(UserRequest request) {
        Response response = given()
                .spec(REQ_SPEC)
                .body(request)
                .when().post();

        try {
            return response.then().statusCode(201)
                    .extract().as(UserResponse.class);
        } catch (AssertionError e) {
            LOGGER.warn("Got an error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public UserResponse updateUser(UserRequest request) {
        Response response = given()
                .spec(REQ_SPEC)
                .body(request)
                .when().post();

        try {
            return response.then().statusCode(201)
                    .extract().as(UserResponse.class);
        } catch (AssertionError e) {
            LOGGER.warn("Got an error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public void deleteUser(int id) {
        Response response = given()
                .spec(REQ_SPEC)
                .basePath(getBasePath() + "/" + id)
                .when().delete();

        try {
            response.then().statusCode(204);
        } catch (AssertionError e) {
            LOGGER.warn("Got an error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}
