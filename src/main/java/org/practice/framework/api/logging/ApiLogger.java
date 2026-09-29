package org.practice.framework.api.logging;

public final class ApiLogger {

    private ApiLogger() {
    }

    public static void logRequest(
            String method,
            String url,
            String headers,
            String body) {

        System.out.println();
        System.out.println("========== API REQUEST ==========");

        System.out.println("Method  : " + method);
        System.out.println("URL     : " + url);

        System.out.println("Headers :");
        System.out.println(
                SensitiveDataMasker.mask(headers)
        );

        if (body != null && !body.isBlank()) {

            System.out.println("Body    :");
            System.out.println(
                    SensitiveDataMasker.mask(body)
            );
        }

        System.out.println(
                "================================="
        );
    }

    public static void logResponse(
            int statusCode,
            long responseTime,
            String headers,
            String body) {

        System.out.println();
        System.out.println("========== API RESPONSE ==========");

        System.out.println(
                "Status        : " + statusCode
        );

        System.out.println(
                "Response Time : " + responseTime + " ms"
        );

        System.out.println("Headers       :");
        System.out.println(
                SensitiveDataMasker.mask(headers)
        );

        if (body != null && !body.isBlank()) {

            System.out.println("Body          :");
            System.out.println(
                    SensitiveDataMasker.mask(body)
            );
        }

        System.out.println(
                "=================================="
        );
    }
}