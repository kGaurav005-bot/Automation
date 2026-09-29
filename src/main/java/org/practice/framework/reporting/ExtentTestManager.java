package org.practice.framework.reporting;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

    private static final ThreadLocal<ExtentTest>
            TEST = new ThreadLocal<>();

    private ExtentTestManager() {
    }

    public static void startTest(
            String testName) {

        ExtentTest extentTest =
                ExtentManager
                        .getInstance()
                        .createTest(testName);

        TEST.set(extentTest);
    }

    public static ExtentTest getTest() {

        return TEST.get();
    }

    public static void clear() {

        TEST.remove();
    }
}