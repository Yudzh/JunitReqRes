package rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import rest.error.ApiError;
import rest.pojos.UserRequest;
import rest.pojos.UserResponse;
import rest.pojos.Unknown;
import rest.pojos.User;
import rest.storage.Storage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Execution(ExecutionMode.CONCURRENT)
public class RestTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void getUserById(int id) {

        User userFromResponse = ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .users.getUserById(id);
        User expectedUser = Storage.users.get(id);

        assertEquals(expectedUser.getId(), userFromResponse.getId());
        assertEquals(expectedUser.getEmail(), userFromResponse.getEmail());
        assertEquals(expectedUser.getFirstName(), userFromResponse.getFirstName());
        assertEquals(expectedUser.getLastName(), userFromResponse.getLastName());
    }

    @Test
    public void getUsers() {
        List<User> users = ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .users.getListOfUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    @DisplayName("Negative case for gettin user by id")
    public void getUserByIdNegative() {

        ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .users.getUserById(23);
        assertEquals(404, ApiError.error.errorCode);
    }

    @Test
    @DisplayName("Failed login")
    public void negativeLogin() {

        ApiContainer.login("eve.holt@reqres.in", "");
        assertEquals(400, ApiError.error.errorCode);
        assertEquals("{error=Missing password}", ApiError.error.errorMessage);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void getUnknownById(int id) {

        Unknown unknownFromResponse = ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .unknown.getUnknownById(id);
        Unknown expectedUnknown = Storage.unknowns.get(id);

        assertEquals(expectedUnknown.getId(), unknownFromResponse.getId());
        assertEquals(expectedUnknown.getName(), unknownFromResponse.getName());
        assertEquals(expectedUnknown.getYear(), unknownFromResponse.getYear());
        assertEquals(expectedUnknown.getColor(), unknownFromResponse.getColor());
        assertEquals(expectedUnknown.getPantoneValue(), unknownFromResponse.getPantoneValue());
    }

    @Test
    public void getUnknowns() {
        List<Unknown> unknowns = ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .unknown.getListOfUnknown();
        assertFalse(unknowns.isEmpty());
    }

    @Test
    @DisplayName("Negative case for gettin user by id")
    public void getUnknownByIdNegative() {
        ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .unknown.getUnknownById(23);
        assertEquals(404, ApiError.error.errorCode);
    }

    @Test
    public void createUser(){
        UserRequest request = new UserRequest("morpheus","leader");
        UserResponse response = ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .users.createUser(request);
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getJob(), response.getJob());
    }

    @Test
    public void updateUser(){
        UserRequest request = new UserRequest("morpheus","zion resident");
        UserResponse response = ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .users.updateUser(request);
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getJob(), response.getJob());
    }

    @Test
    public void deleteUser(){
        ApiContainer.login("eve.holt@reqres.in", "cityslicka") //Login method may return NULL. Use it for negative cases
                .users.deleteUser(2);
    }

    @Test
    public void register(){
        ApiContainer.register("eve.holt@reqres.in", "pistol");
    }

    @Test
    public void registerNegative(){
        ApiContainer.register("sydney@fife", "");
        assertEquals(400, ApiError.error.errorCode);
        assertEquals("{error=Missing password}", ApiError.error.errorMessage);
    }
}
