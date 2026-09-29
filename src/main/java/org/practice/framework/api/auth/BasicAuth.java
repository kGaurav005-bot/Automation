package org.practice.framework.api.auth;

import io.restassured.specification.RequestSpecification;

public class BasicAuth
        implements AuthenticationStrategy {

    private final String username;
    private final String password;

    public BasicAuth(
            String username,
            String password) {

        this.username = username;
        this.password = password;
    }

    @Override
    public void apply(RequestSpecification request) {

        request.auth()
                .preemptive()
                .basic(username, password);
    }
}