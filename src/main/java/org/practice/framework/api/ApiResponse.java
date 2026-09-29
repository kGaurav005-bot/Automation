package org.practice.framework.api;

import io.restassured.response.Response;

public class ApiResponse<T> {

    private final Response rawResponse;
    private final T body;

    public ApiResponse(
            Response rawResponse,
            T body) {

        this.rawResponse = rawResponse;
        this.body = body;
    }

    public Response getRawResponse() {
        return rawResponse;
    }

    public T getBody() {
        return body;
    }

    public int getStatusCode() {
        return rawResponse.statusCode();
    }

    public long getResponseTime() {
        return rawResponse.time();
    }
}