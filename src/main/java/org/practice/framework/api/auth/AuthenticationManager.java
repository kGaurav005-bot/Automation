package org.practice.framework.api.auth;

import org.practice.framework.config.ConfigManager;
import org.practice.framework.exception.ApiAuthenticationException;

public class AuthenticationManager {

    // Token validity: 30 minutes
    private static final long TOKEN_VALIDITY_MS =
            30 * 60 * 1000; //1,800,000 milliseconds

    // Refresh token 1 minute before actual expiration
    private static final long TOKEN_BUFFER_MS =
            60 * 1000;

    private static String accessToken;
    private static long tokenExpiryTime;

    private AuthenticationManager() {
    }

    public static synchronized String getAccessToken() {

        if (accessToken == null || isTokenExpired()) {

            accessToken = generateToken();

            tokenExpiryTime =
                    System.currentTimeMillis()
                            + TOKEN_VALIDITY_MS;
        }

        return accessToken;
    }

    private static boolean isTokenExpired() {

        return System.currentTimeMillis()
                >= tokenExpiryTime - TOKEN_BUFFER_MS;
    }

    private static String generateToken() {

        try {

            return ConfigManager
                    .getInstance()
                    .getApiToken();

        } catch (Exception e) {

            throw new ApiAuthenticationException(
                    "Unable to obtain API access token",
                    e
            );
        }
    }
}