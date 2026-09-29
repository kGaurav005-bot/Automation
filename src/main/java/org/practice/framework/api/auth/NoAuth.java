package org.practice.framework.api.auth;

import io.restassured.specification.RequestSpecification;

public class NoAuth
        implements AuthenticationStrategy {

    @Override
    public void apply(RequestSpecification request) {
        // No authentication required
    }
}