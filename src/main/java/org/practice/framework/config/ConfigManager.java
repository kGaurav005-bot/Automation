package org.practice.framework.config;

import org.practice.framework.exception.ConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager implements FrameworkConfig {

    private static ConfigManager instance;

    private final Properties properties =
            new Properties();

    private ConfigManager() {
        loadProperties();
    }

    @Override
    public int getApiRetryMaxAttempts() {

        return Integer.parseInt(
                properties.getProperty(
                        "api.retry.max.attempts",
                        "3"
                )
        );
    }

    @Override
    public long getApiRetryInitialDelayMs() {

        return Long.parseLong(
                properties.getProperty(
                        "api.retry.initial.delay.ms",
                        "1000"
                )
        );
    }

    @Override
    public long getApiRetryMaxDelayMs() {

        return Long.parseLong(
                properties.getProperty(
                        "api.retry.max.delay.ms",
                        "5000"
                )
        );
    }

    @Override
    public double getApiRetryMultiplier() {

        return Double.parseDouble(
                properties.getProperty(
                        "api.retry.multiplier",
                        "2.0"
                )
        );
    }

    @Override
    public long getApiRetryJitterMs() {

        return Long.parseLong(
                properties.getProperty(
                        "api.retry.jitter.ms",
                        "250"
                )
        );
    }
    @Override
    public long getApiRetryDelayMs() {

        return Long.parseLong(
                properties.getProperty(
                        "api.retry.delay.ms",
                        "1000"
                )
        );
    }
    public static synchronized ConfigManager getInstance() {

        if (instance == null) {
            instance = new ConfigManager();
        }

        return instance;
    }

    private void loadProperties() {

        try {

            // -----------------------------
            // Load common configuration
            // -----------------------------

            Properties commonProperties =
                    new Properties();

            commonProperties.load(
                    new FileInputStream(
                            "src/main/resources/config.properties"
                    )
            );

            String environment =
                    commonProperties
                            .getProperty("environment");

            if (environment == null
                    || environment.isBlank()) {

                throw new IllegalStateException(
                        "Environment is not configured "
                                + "in config.properties"
                );
            }

            environment =
                    environment
                            .trim()
                            .toLowerCase();

            // -----------------------------
            // Add common properties
            // -----------------------------

            properties.putAll(
                    commonProperties
            );

            // -----------------------------
            // Load environment properties
            // -----------------------------

            String environmentFile =
                    "src/main/resources/"
                            + environment
                            + ".properties";

            properties.load(
                    new FileInputStream(
                            environmentFile
                    )
            );

        } catch (IOException e) {

            throw new ConfigurationException(
                    "Unable to load configuration",
                    e
            );
        }
    }

    @Override
    public String getBaseUrl() {

        return properties.getProperty(
                "base.url"
        );
    }

    @Override
    public String getApiBaseUrl() {

        return properties.getProperty(
                "api.base.url"
        );
    }

    @Override
    public String getBrowser() {

        return properties.getProperty(
                "browser"
        );
    }

    @Override
    public boolean isHeadless() {

        return Boolean.parseBoolean(
                properties.getProperty("headless")
        );
    }

    @Override
    public String getExecution() {

        return properties.getProperty(
                "execution"
        );
    }

    @Override
    public int getImplicitWait() {

        return Integer.parseInt(
                properties.getProperty(
                        "implicit.wait"
                )
        );
    }

    @Override
    public int getExplicitWait() {

        return Integer.parseInt(
                properties.getProperty(
                        "explicit.wait"
                )
        );
    }

    @Override
    public int getThreadCount() {

        return Integer.parseInt(
                properties.getProperty(
                        "thread.count"
                )
        );
    }

    @Override
    public boolean isParallel() {

        return Boolean.parseBoolean(
                properties.getProperty(
                        "parallel"
                )
        );
    }

    @Override
    public String getUsername() {

        return properties.getProperty(
                "username"
        );
    }

    @Override
    public String getPassword() {

        return properties.getProperty(
                "password"
        );
    }

    @Override
    public String getApiToken() {

        // 1. Try environment variable
        String environmentVariable =
                properties.getProperty(
                        "api.token.env"
                );

        String token = null;

        if (environmentVariable != null
                && !environmentVariable.isBlank()) {

            token = System.getenv(
                    environmentVariable
            );
        }

        // 2. Local dummy-token fallback
        if (token == null || token.isBlank()) {

            token = properties.getProperty(
                    "api.token"
            );
        }

        if (token == null || token.isBlank()) {

            throw new ConfigurationException(
                    "API token is not configured"
            );
        }

        return token;
    }
}