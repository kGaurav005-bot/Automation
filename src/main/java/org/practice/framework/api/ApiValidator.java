package org.practice.framework.api;

import io.restassured.response.Response;
import org.practice.framework.exception.ApiValidationException;

import static org.assertj.core.api.Assertions.assertThat;

public final class ApiValidator {

    private ApiValidator() {
    }

    public static void assertStatusCode(
            Response response,
            int expectedStatusCode) {

        int actualStatusCode =
                response.statusCode();

        if (actualStatusCode != expectedStatusCode) {

            throw new ApiValidationException(
                    "Unexpected HTTP status. "
                            + "Expected: "
                            + expectedStatusCode
                            + ", Actual: "
                            + actualStatusCode
            );
        }
    }
    public static void assertResponseTime(
            Response response,
            long maxResponseTimeMs) {

        long actual =
                response.time();

        if (actual > maxResponseTimeMs) {

            throw new ApiValidationException(
                    "API response time exceeded threshold. "
                            + "Expected <= "
                            + maxResponseTimeMs
                            + " ms, Actual: "
                            + actual
                            + " ms"
            );
        }
    }
    public static void assertSuccess(
            Response response) {

        assertThat(response.statusCode())
                .as("HTTP status code")
                .isBetween(200, 299);
    }

    public static void assertClientError(
            Response response) {

        assertThat(response.statusCode())
                .as("HTTP status code")
                .isBetween(400, 499);
    }

    public static void assertServerError(
            Response response) {

        assertThat(response.statusCode())
                .as("HTTP status code")
                .isBetween(500, 599);
    }
}