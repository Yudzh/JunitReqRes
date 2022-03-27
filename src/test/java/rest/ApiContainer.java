package rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rest.error.ApiError;
import rest.pojos.LoginResponse;
import rest.pojos.UserLogin;
import rest.service.UnknownService;
import rest.service.UserService;

import static io.restassured.RestAssured.given;

public class ApiContainer {

    private static Logger LOGGER = LoggerFactory.getLogger(ApiContainer.class);

    protected UserService users;
    protected UnknownService unknown;
    private static final String BASE_URI = "https://reqres.in/api/";

    private static RequestSpecification REQ_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setBasePath("/login")
            .setContentType(ContentType.JSON).build();

    public ApiContainer(String token) {
        Assertions.assertNotNull(token);
        this.users = new UserService(token);
        this.unknown = new UnknownService(token);
    }

    public static ApiContainer login(String login, String password) {

        LOGGER.info("Login as " + login);

        Response response = given().spec(REQ_SPEC)
                .body(new UserLogin(login, password))
                .when().post();
        try {
            return new ApiContainer(response.then().statusCode(200)
                    .extract().as(LoginResponse.class).getToken());
        } catch (AssertionError e) {
            LOGGER.warn("Got an assertion error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            LOGGER.error("Got a fatal error. Error info: " + exception.getMessage());
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }

    public static ApiContainer register(String login, String password) {
        LOGGER.info("Register as " + login);

        Response response = given().spec(REQ_SPEC)
                .body(new UserLogin(login, password))
                .basePath("/register")
                .when().post();
        try {
            return new ApiContainer(response.then().statusCode(200)
                    .extract().as(LoginResponse.class).getToken());
        } catch (AssertionError e) {
            LOGGER.warn("Got an assertion error. Error info: " + e.getMessage());
            ApiError.saveError(response);
        } catch (Exception exception) {
            LOGGER.error("Got a fatal error. Error info: " + exception.getMessage());
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        return null;
    }


}
