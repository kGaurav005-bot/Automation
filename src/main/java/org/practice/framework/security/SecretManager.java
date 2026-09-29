package org.practice.framework.security;

public final class SecretManager {

    private SecretManager() {
    }

    public static String getSecret(
            String environmentVariable) {

        String value =
                System.getenv(environmentVariable);

        if (value == null ||
                value.isBlank()) {

            throw new IllegalStateException(
                    "Required secret not found: "
                            + environmentVariable
            );
        }

        return value;
    }
}