package org.practice.tests.listener;

import com.aventstack.extentreports.Status;
import org.practice.framework.context.ExecutionContext;
import org.practice.framework.context.ExecutionContextManager;
import org.practice.framework.exception.ExceptionHandler;
import org.practice.framework.reporting.ExtentManager;
import org.practice.framework.reporting.ExtentTestManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportingListener
        implements ITestListener {

    @Override
    public void onTestStart(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getQualifiedName();

        ExtentTestManager.startTest(
                testName
        );

        ExecutionContext context =
                ExecutionContextManager.get();

        if (context != null) {

            ExtentTestManager
                    .getTest()
                    .assignCategory(
                            context.getEnvironment()
                    );

            ExtentTestManager
                    .getTest()
                    .info(
                            "Test Case ID: "
                                    + context.getTestCaseId()
                    );

            ExtentTestManager
                    .getTest()
                    .info(
                            "Environment: "
                                    + context.getEnvironment()
                    );

            ExtentTestManager
                    .getTest()
                    .info(
                            "Correlation ID: "
                                    + context.getCorrelationId()
                    );
        }
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        ExtentTestManager
                .getTest()
                .log(
                        Status.PASS,
                        "Test passed"
                );

        ExtentTestManager.clear();
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        Throwable throwable =
                result.getThrowable();

        ExceptionHandler.handle(
                throwable
        );

        if (ExtentTestManager.getTest() != null) {

            ExtentTestManager
                    .getTest()
                    .fail(
                            throwable.getMessage()
                    );

            ExtentTestManager
                    .getTest()
                    .fail(
                            throwable
                    );
        }

        ExtentTestManager.clear();
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        ExtentTestManager
                .getTest()
                .skip(
                        "Test skipped"
                );

        ExtentTestManager.clear();
    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        ExtentManager.flush();
    }
}