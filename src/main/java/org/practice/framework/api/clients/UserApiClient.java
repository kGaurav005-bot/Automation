package org.practice.framework.api.clients;

import io.restassured.response.Response;
import org.practice.framework.api.ApiClient;
import org.practice.framework.api.ApiResponse;
import org.practice.framework.api.auth.AuthenticationManager;
import org.practice.framework.api.auth.BearerTokenAuth;
import org.practice.framework.model.request.UserRequest;
import org.practice.framework.model.response.UserResponse;

public class UserApiClient extends ApiClient {

    public UserApiClient() {

        super(
                new BearerTokenAuth(
                        AuthenticationManager.getAccessToken()
                )
        );
    }
    public ApiResponse<UserResponse>
    getUserWithRetry() {

        return get(
                "/users/retry",
                UserResponse.class
        );
    }
    public ApiResponse<UserResponse> getUser(
            int userId) {

        return get(
                "/users/" + userId,
                UserResponse.class
        );
    }
    public ApiResponse<UserResponse> createUser(
            UserRequest request) {

        return post(
                "/users",
                request,
                UserResponse.class
        );
    }
    public ApiResponse<UserResponse>
    createUserWithRetry(
            UserRequest request,
            String idempotencyKey) {

        return postWithIdempotency(
                "/users/retry",
                request,
                idempotencyKey,
                UserResponse.class
        );
    }
    public ApiResponse<UserResponse> updateUser(
            int userId,
            UserRequest request) {

        return put(
                "/users/" + userId,
                request,
                UserResponse.class
        );
    }
    public ApiResponse<UserResponse>
    getAlwaysFailingUser() {

        return get(
                "/users/always-fail",
                UserResponse.class
        );
    }
    public Response deleteUser(int userId) {

        return delete("/users/" + userId);
    }
}