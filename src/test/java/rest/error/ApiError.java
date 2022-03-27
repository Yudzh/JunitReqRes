package rest.error;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class ApiError {
    public int errorCode;
    public String errorMessage;

    public static ApiError error;

    private ApiError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public static void saveError(Response response) {
        int status = response.statusCode();
        String errorMessage = response.then().extract().jsonPath().get().toString();
        error = new ApiError(status, errorMessage);
    }
}
