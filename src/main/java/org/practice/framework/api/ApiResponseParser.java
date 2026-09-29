package org.practice.framework.api;

import io.restassured.response.Response;
import org.practice.framework.exception.ApiDeserializationException;

public final class ApiResponseParser {

    private ApiResponseParser() {
    }

    public static <T> ApiResponse<T> parse(
            Response response,
            Class<T> responseClass) {

        try {

            T body =
                    response.as(
                            responseClass
                    );

            return new ApiResponse<>(
                    response,
                    body
            );

        } catch (Exception e) {

            throw new ApiDeserializationException(
                    "Unable to deserialize API response "
                            + "into "
                            + responseClass.getSimpleName()
                            + ". HTTP status: "
                            + response.statusCode(),
                    e
            );
        }
    }
}