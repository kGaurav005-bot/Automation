package org.practice.framework.api.validation;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public final class JsonSchemaValidator {

    private JsonSchemaValidator() {
    }

    public static void validate(
            Response response,
            String schemaPath) {

        response.then()
                .assertThat()
                .body(
                        matchesJsonSchemaInClasspath(
                                schemaPath
                        )
                );
    }
}