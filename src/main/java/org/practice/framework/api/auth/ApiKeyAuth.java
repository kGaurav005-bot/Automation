package org.practice.framework.api.auth;

import io.restassured.specification.RequestSpecification;

public class ApiKeyAuth
        implements AuthenticationStrategy {

    private final String headerName;
    private final String apiKey;

    public ApiKeyAuth(
            String headerName,
            String apiKey) {

        this.headerName = headerName;
        this.apiKey = apiKey;
    }

    @Override
    public void apply(RequestSpecification request) {

        request.header(
                headerName,
                apiKey
        );
    }
}