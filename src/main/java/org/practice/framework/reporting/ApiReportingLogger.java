package org.practice.framework.reporting;

import io.restassured.response.Response;

public final class ApiReportingLogger {

    private ApiReportingLogger() {
    }

    public static void logResponse(
            String method,
            String endpoint,
            Response response) {

        if (ExtentTestManager.getTest() == null) {
            return;
        }

        ExtentTestManager
                .getTest()
                .info(
                        "HTTP Method: " + method
                );

        ExtentTestManager
                .getTest()
                .info(
                        "Endpoint: " + endpoint
                );

        ExtentTestManager
                .getTest()
                .info(
                        "Status Code: "
                                + response.statusCode()
                );

        ExtentTestManager
                .getTest()
                .info(
                        "Response Time: "
                                + response.time()
                                + " ms"
                );
    }
}