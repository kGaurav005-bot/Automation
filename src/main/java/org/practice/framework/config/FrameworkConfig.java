package org.practice.framework.config;

public interface FrameworkConfig {

    String getBaseUrl();

    String getBrowser();

    boolean isHeadless();

    String getExecution();

    int getImplicitWait();

    int getExplicitWait();

    int getThreadCount();

    boolean isParallel();

    String getUsername();

    String getPassword();

    String getApiBaseUrl();

    String getApiToken();

    long getApiRetryDelayMs();
    int getApiRetryMaxAttempts();

    long getApiRetryInitialDelayMs();

    long getApiRetryMaxDelayMs();

    double getApiRetryMultiplier();

    long getApiRetryJitterMs();
}