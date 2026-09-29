package org.practice.framework.api.logging;

public final class SensitiveDataMasker {

    private SensitiveDataMasker() {
    }

    public static String mask(String value) {

        if (value == null || value.isBlank()) {
            return value;
        }

        return value
                .replaceAll(
                        "(?i)(\"password\"\\s*:\\s*\")[^\"]*(\")",
                        "$1****$2"
                )
                .replaceAll(
                        "(?i)(\"token\"\\s*:\\s*\")[^\"]*(\")",
                        "$1****$2"
                )
                .replaceAll(
                        "(?i)(\"access_token\"\\s*:\\s*\")[^\"]*(\")",
                        "$1****$2"
                )
                .replaceAll(
                        "(?i)(\"client_secret\"\\s*:\\s*\")[^\"]*(\")",
                        "$1****$2"
                )
                .replaceAll(
                        "(?i)(Authorization:\\s*Bearer\\s+)[^\\s]+",
                        "$1****"
                );
    }
}