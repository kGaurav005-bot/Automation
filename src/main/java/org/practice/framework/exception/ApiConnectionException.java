package org.practice.framework.exception;

/*
useful for
ConnectException
SocketTimeoutException
Connection reset
DNS/network problems
*/
public class ApiConnectionException
        extends FrameworkException {

    public ApiConnectionException(
            String message,
            Throwable cause) {

        super(message, cause);
    }
}