package org.practice.framework.exception;
/*

for exception like API retry exhausted after 3 attempts
 */
public class ApiRetryExhaustedException
        extends FrameworkException {

    public ApiRetryExhaustedException(
            String message) {

        super(message);
    }

    public ApiRetryExhaustedException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}