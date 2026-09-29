package org.practice.tests.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
public final class UserApiStubs {

    private UserApiStubs() {
    }

    public static void register() {

        // GET /users/1
        stubFor(
                get(urlEqualTo("/users/1"))
                        .withHeader(
                                "Authorization",
                                equalTo("Bearer dummy-token-12345")
                        )
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(
                                                "Content-Type",
                                                "application/json"
                                        )
                                        .withBody("""
                                                {
                                                  "id": 1,
                                                  "name": "John Doe",
                                                  "email": "john.doe@test.com",
                                                  "status": "ACTIVE"
                                                }
                                                """)
                        )
        );

        // POST /users
        stubFor(
                post(urlEqualTo("/users"))
                        .withHeader(
                                "Authorization",
                                equalTo("Bearer dummy-token-12345")
                        )
                        .willReturn(
                                aResponse()
                                        .withStatus(201)
                                        .withHeader(
                                                "Content-Type",
                                                "application/json"
                                        )
                                        .withTransformers("response-template")
                                        .withBody("""
                                        {
                                          "id": 101,
                                          "name": "{{jsonPath request.body '$.name'}}",
                                          "email": "{{jsonPath request.body '$.email'}}",
                                          "status": "ACTIVE"
                                        }
                                        """)
                        )
        );
        stubFor(
                get(urlEqualTo("/users/retry"))
                        .inScenario("Retry Scenario")
                        .whenScenarioStateIs(STARTED)
                        .willReturn(
                                aResponse()
                                        .withStatus(503)
                        )
                        .willSetStateTo(
                                "Second Attempt"
                        )
        );

        stubFor(
                get(urlEqualTo("/users/retry"))
                        .inScenario("Retry Scenario")
                        .whenScenarioStateIs(
                                "Second Attempt"
                        )
                        .willReturn(
                                aResponse()
                                        .withStatus(503)
                        )
                        .willSetStateTo(
                                "Success"
                        )
        );

        stubFor(
                get(urlEqualTo("/users/retry"))
                        .inScenario("Retry Scenario")
                        .whenScenarioStateIs(
                                "Success"
                        )
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(
                                                "Content-Type",
                                                "application/json"
                                        )
                                        .withBody("""
                                        {
                                          "id": 1,
                                          "name": "Retry User",
                                          "email": "retry@test.com",
                                          "status": "ACTIVE"
                                        }
                                        """)
                        )
        );
        stubFor(
                post(urlEqualTo("/users/retry"))
                        .inScenario("POST Retry")
                        .whenScenarioStateIs(STARTED)
                        .withHeader(
                                "Idempotency-Key",
                                matching(".+")
                        )
                        .willReturn(
                                aResponse()
                                        .withStatus(503)
                        )
                        .willSetStateTo(
                                "POST Success"
                        )
        );
        stubFor(
                post(urlEqualTo("/users/retry"))
                        .inScenario("POST Retry")
                        .whenScenarioStateIs(
                                "POST Success"
                        )
                        .withHeader(
                                "Idempotency-Key",
                                matching(".+")
                        )
                        .willReturn(
                                aResponse()
                                        .withStatus(201)
                                        .withHeader(
                                                "Content-Type",
                                                "application/json"
                                        )
                                        .withTransformers(
                                                "response-template"
                                        )
                                        .withBody("""
                                        {
                                          "id": 200,
                                          "name": "{{jsonPath request.body '$.name'}}",
                                          "email": "{{jsonPath request.body '$.email'}}",
                                          "status": "ACTIVE"
                                        }
                                        """)
                        )
        );
        stubFor(
                get(urlEqualTo("/users/always-fail"))
                        .willReturn(
                                aResponse()
                                        .withStatus(503)
                                        .withHeader(
                                                "Content-Type",
                                                "application/json"
                                        )
                        )
        );
    }
}