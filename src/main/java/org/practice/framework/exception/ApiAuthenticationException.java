package org.practice.framework.exception;
//useful for missing token
//expired token acquisition
//OAuth failure
//missing credentials
public class ApiAuthenticationException
        extends FrameworkException {

    public ApiAuthenticationException(
            String message) {

        super(message);
    }

    public ApiAuthenticationException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}
