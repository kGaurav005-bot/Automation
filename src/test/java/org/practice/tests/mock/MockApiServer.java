package org.practice.tests.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockApiServer {

    private static WireMockServer wireMockServer;

    public static void start() {

        wireMockServer =
                new WireMockServer(
                        wireMockConfig()
                                .port(8089)
                                .globalTemplating(true)
                );

        wireMockServer.start();

        configureFor(
                "localhost",
                8089
        );

        UserApiStubs.register();

        System.out.println(
                "Mock API started at http://localhost:8089"
        );
    }

    public static void stop() {

        if (wireMockServer != null) {

            wireMockServer.stop();

            System.out.println(
                    "Mock API stopped"
            );
        }
    }

    public static WireMockServer getServer() {
        return wireMockServer;
    }
}