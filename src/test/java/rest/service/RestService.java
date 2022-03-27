package rest.service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.oauth2;

public abstract class RestService {

    protected static Logger LOGGER = LoggerFactory.getLogger(RestService.class);
    protected static final String BASE_URI = "https://reqres.in/api/";
    protected RequestSpecification REQ_SPEC;

    abstract String getBasePath();

    protected RestService(String token) {
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .setAuth(oauth2(token))
                .build();
    }
}
