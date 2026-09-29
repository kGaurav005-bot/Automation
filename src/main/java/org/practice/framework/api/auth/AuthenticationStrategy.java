package org.practice.framework.api.auth;

import io.restassured.specification.RequestSpecification;
//Any authentication mechanism must know how to apply itself to a REST Assured request.
public interface AuthenticationStrategy {

    void apply(RequestSpecification request);

}