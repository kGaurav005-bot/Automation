package org.practice.framework.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.practice.framework.api.auth.AuthenticationStrategy;
import org.practice.framework.config.ConfigManager;
import org.practice.framework.reporting.ApiReportingLogger;
import org.practice.framework.retry.RetryExecutor;
import org.practice.framework.retry.RetryPolicy;
import org.practice.framework.utils.FrameworkLogger;

public abstract class ApiClient {
    private final RetryPolicy retryPolicy;
    private final AuthenticationStrategy authenticationStrategy;
    private static final Logger LOGGER =
            FrameworkLogger.getLogger(ApiClient.class);
    protected ApiClient(
            AuthenticationStrategy authenticationStrategy) {

        this.authenticationStrategy =
                authenticationStrategy;

        ConfigManager config =
                ConfigManager.getInstance();

        this.retryPolicy =
                new RetryPolicy(
                        config.getApiRetryMaxAttempts(),
                        config.getApiRetryInitialDelayMs(),
                        config.getApiRetryMaxDelayMs(),
                        config.getApiRetryMultiplier(),
                        config.getApiRetryJitterMs()
                );
    }

    protected RequestSpecification request() {

        return ApiRequestBuilder
                .build(authenticationStrategy);
    }

    // =========================
    // GET
    // =========================

    protected Response get(String endpoint) {
        LOGGER.info(
                "Executing GET request: {}",
                endpoint
        );
        Response response =  RetryExecutor.execute(
                () -> request()
                        .when()
                        .get(endpoint),
                retryPolicy
        );
        LOGGER.info(
                "Response status: {} | time: {} ms",
                response.statusCode(),
                response.time()
        );
        ApiReportingLogger.logResponse(
                "GET",
                endpoint,
                response
        );

        return response;
    }

    protected <T> ApiResponse<T> get(
            String endpoint,
            Class<T> responseClass) {

        Response response =
                get(endpoint);

        return ApiResponseParser.parse(
                response,
                responseClass
        );
    }

    // =========================
    // POST
    // =========================

    protected Response post(
            String endpoint,
            Object requestBody) {

        Response response =  RetryExecutor.execute(
                () -> request()
                        .body(requestBody)
                        .when()
                        .post(endpoint),
                retryPolicy
        );

        ApiReportingLogger.logResponse(
                "POST",
                endpoint,
                response
        );

        return response;
    }

    protected <T> ApiResponse<T> post(
            String endpoint,
            Object requestBody,
            Class<T> responseClass) {

        Response response =
                post(endpoint, requestBody);

        return ApiResponseParser.parse(
                response,
                responseClass
        );
    }
    protected Response postWithIdempotency(
            String endpoint,
            Object requestBody,
            String idempotencyKey) {

        Response response =
                RetryExecutor.execute(
                        () -> request()
                                .header(
                                        "Idempotency-Key",
                                        idempotencyKey
                                )
                                .body(requestBody)
                                .when()
                                .post(endpoint),
                        retryPolicy
                );

        ApiReportingLogger.logResponse(
                "POST",
                endpoint,
                response
        );

        return response;
    }
    protected <T> ApiResponse<T> postWithIdempotency(
            String endpoint,
            Object requestBody,
            String idempotencyKey,
            Class<T> responseClass) {

        Response response =
                postWithIdempotency(
                        endpoint,
                        requestBody,
                        idempotencyKey
                );

        return ApiResponseParser.parse(
                response,
                responseClass
        );
    }
    // =========================
    // PUT
    // =========================

    protected Response put(
            String endpoint,
            Object requestBody) {

        Response response = RetryExecutor.execute(
                () -> request()
                        .body(requestBody)
                        .when()
                        .post(endpoint),
                retryPolicy
        );

        ApiReportingLogger.logResponse(
                "PUT",
                endpoint,
                response
        );

        return response;
    }

    protected <T> ApiResponse<T> put(
            String endpoint,
            Object requestBody,
            Class<T> responseClass) {

        Response response =
                put(endpoint, requestBody);

        return ApiResponseParser.parse(
                response,
                responseClass
        );
    }

    // =========================
    // DELETE
    // =========================

    protected Response delete(
            String endpoint) {

        Response response = RetryExecutor.execute(
                () -> request()
                        .when()
                        .delete(endpoint),
                retryPolicy
        );
        ApiReportingLogger.logResponse(
                "DELETE",
                endpoint,
                response
        );
        return response;
    }
}