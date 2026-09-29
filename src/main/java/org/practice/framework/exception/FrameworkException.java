package org.practice.framework.exception;

//All framework-specific exceptions will extend this
public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}