package org.practice.framework.exception;
/*

unexpected status
schema mismatch
business validation failure
 */
public class ApiValidationException
        extends FrameworkException {

    public ApiValidationException(
            String message) {

        super(message);
    }

    public ApiValidationException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}