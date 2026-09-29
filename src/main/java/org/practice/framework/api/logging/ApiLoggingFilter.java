package org.practice.framework.api.logging;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiLoggingFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext context) {

        String method =
                requestSpec.getMethod();

        String url =
                requestSpec.getURI();

        String headers =
                requestSpec.getHeaders()
                        .toString();

        String body =
                requestSpec.getBody();

        ApiLogger.logRequest(
                method,
                url,
                headers,
                body
        );

        long startTime =
                System.currentTimeMillis();

        Response response =
                context.next(
                        requestSpec,
                        responseSpec
                );

        long responseTime =
                System.currentTimeMillis()
                        - startTime;

        ApiLogger.logResponse(
                response.statusCode(),
                responseTime,
                response.getHeaders().toString(),
                response.getBody().asString()
        );

        return response;
    }
}