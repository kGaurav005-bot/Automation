package org.practice.framework.context;

public class ExecutionContext {

    private final String testCaseId;

    private final String environment;

    private final String correlationId;

    public ExecutionContext(
            String testCaseId,
            String environment,
            String correlationId) {

        this.testCaseId = testCaseId;
        this.environment = environment;
        this.correlationId = correlationId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getCorrelationId() {
        return correlationId;
    }
}