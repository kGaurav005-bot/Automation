package org.practice.framework.retry;

import io.restassured.response.Response;

public final class FailureClassifier {

    private FailureClassifier() {
    }

    public static boolean isRetryableStatus(int statusCode) {

        return statusCode == 500
                || statusCode == 502
                || statusCode == 503
                || statusCode == 504;
    }

    public static boolean isRetryableException(Throwable throwable) {

        if (throwable == null) {
            return false;
        }

        String message =
                throwable.getMessage() == null
                        ? ""
                        : throwable.getMessage().toLowerCase();

        return throwable instanceof java.net.ConnectException
                || throwable instanceof java.net.SocketTimeoutException
                || message.contains("connection reset")
                || message.contains("connection refused")
                || message.contains("timed out");
    }

    public static boolean shouldRetry(Response response) {

        return response != null
                && isRetryableStatus(
                response.statusCode()
        );
    }
}