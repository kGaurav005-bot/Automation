package org.practice.framework.api;

import io.restassured.specification.RequestSpecification;
import org.practice.framework.api.auth.AuthenticationStrategy;
import org.practice.framework.api.logging.ApiLoggingFilter;
import org.practice.framework.config.ConfigManager;

import static io.restassured.RestAssured.given;

public class ApiRequestBuilder {

    private ApiRequestBuilder() {
    }

    public static RequestSpecification build(
            AuthenticationStrategy authStrategy) {

        String baseUrl =
                ConfigManager.getInstance()
                        .getBaseUrl();

        RequestSpecification request =
                given()
                        .baseUri(baseUrl)
                        .contentType("application/json")
                        .accept("application/json")
                        .filter(new ApiLoggingFilter());

        authStrategy.apply(request);

        return request;
    }
}