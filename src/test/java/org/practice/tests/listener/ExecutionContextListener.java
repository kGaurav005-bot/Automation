package org.practice.tests.listener;

import org.practice.framework.context.ExecutionContext;
import org.practice.framework.context.ExecutionContextManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.UUID;

public class ExecutionContextListener
        implements ITestListener {

    @Override
    public void onTestStart(
            ITestResult result) {

        String testCaseId =
                result.getMethod()
                        .getMethodName();

        String environment =
                System.getProperty(
                        "env",
                        "QA"
                );

        String correlationId =
                UUID.randomUUID()
                        .toString();

        ExecutionContext context =
                new ExecutionContext(
                        testCaseId,
                        environment,
                        correlationId
                );

        ExecutionContextManager.set(
                context
        );
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        ExecutionContextManager.clear();
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        ExecutionContextManager.clear();
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        ExecutionContextManager.clear();
    }
}