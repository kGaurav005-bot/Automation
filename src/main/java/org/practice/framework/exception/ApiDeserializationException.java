package org.practice.framework.exception;

public class ApiDeserializationException
        extends FrameworkException {

    public ApiDeserializationException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}