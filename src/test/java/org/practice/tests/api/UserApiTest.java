package org.practice.tests.api;

import org.practice.framework.api.ApiResponse;
import org.practice.framework.api.ApiValidator;
import org.practice.framework.api.IdempotencyKeyGenerator;
import org.practice.framework.api.clients.UserApiClient;
import org.practice.framework.api.validation.JsonSchemaValidator;
import org.practice.framework.model.request.UserRequest;
import org.practice.framework.model.response.UserResponse;
import org.practice.tests.data.UserDataProvider;
import org.practice.tests.mock.MockApiLifecycle;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTest extends MockApiLifecycle {

    @Test(
            dataProvider = "userData",
            dataProviderClass = UserDataProvider.class
    )
    public void createUserTest(
            UserRequest request) {

        UserApiClient userApi =
                new UserApiClient();

        ApiResponse<UserResponse> response =
                userApi.createUser(request);

        ApiValidator.assertStatusCode(
                response.getRawResponse(),
                201
        );

        UserResponse user =
                response.getBody();

        assertThat(user.getName())
                .isEqualTo(request.getName());

        assertThat(user.getEmail())
                .isEqualTo(request.getEmail());
    }

    @Test
    public void getUserSchemaTest() {

        UserApiClient userApi =
                new UserApiClient();

        ApiResponse<UserResponse> response =
                userApi.getUser(1);

        ApiValidator.assertStatusCode(
                response.getRawResponse(),
                200
        );

        JsonSchemaValidator.validate(
                response.getRawResponse(),
                "schemas/user-response-schema.json"
        );
    }
    @Test
    public void shouldRetryPostWithIdempotencyKey() {

        // Arrange
        UserRequest request =
                UserRequest.builder()
                        .name("Retry User")
                        .email("retry.user@test.com")
                        .build();

        String idempotencyKey =
                IdempotencyKeyGenerator.generate();

        UserApiClient userApi =
                new UserApiClient();

        // Act
        ApiResponse<UserResponse> response =
                userApi.createUserWithRetry(
                        request,
                        idempotencyKey
                );

        // Assert final response
        ApiValidator.assertStatusCode(
                response.getRawResponse(),
                201
        );

        assertThat(response.getBody().getName())
                .isEqualTo(request.getName());

        assertThat(response.getBody().getEmail())
                .isEqualTo(request.getEmail());

        // Assert retry behavior
        verify(
                2,
                postRequestedFor(
                        urlEqualTo("/users/retry")
                )
                        .withHeader(
                                "Idempotency-Key",
                                equalTo(idempotencyKey)
                        )
        );
    }
    @Test
    public void shouldRetryTransientFailure() {

        UserApiClient userApi =
                new UserApiClient();

        ApiResponse<UserResponse> response =
                userApi.getUserWithRetry();

        ApiValidator.assertStatusCode(
                response.getRawResponse(),
                200
        );

        assertThat(
                response.getBody().getName()
        ).isEqualTo("Retry User");
    }
}