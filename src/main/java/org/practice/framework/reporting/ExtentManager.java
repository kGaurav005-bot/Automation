package org.practice.framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {

        if (extentReports == null) {

            try {

                Path reportDirectory =
                        Path.of("target", "extent-report");

                Files.createDirectories(
                        reportDirectory
                );

                String reportPath =
                        reportDirectory
                                .resolve("index.html")
                                .toString();

                ExtentSparkReporter reporter =
                        new ExtentSparkReporter(
                                reportPath
                        );

                reporter.config()
                        .setDocumentTitle(
                                "Automation Framework Report"
                        );

                reporter.config()
                        .setReportName(
                                "API/UI Automation Execution"
                        );

                extentReports =
                        new ExtentReports();

                extentReports.attachReporter(
                        reporter
                );

                extentReports.setSystemInfo(
                        "Framework",
                        "AutomationFramework"
                );

                extentReports.setSystemInfo(
                        "Environment",
                        System.getProperty(
                                "env",
                                "QA"
                        )
                );

                extentReports.setSystemInfo(
                        "Java",
                        System.getProperty(
                                "java.version"
                        )
                );

            } catch (Exception e) {

                throw new RuntimeException(
                        "Unable to initialize ExtentReports",
                        e
                );
            }
        }

        return extentReports;
    }
//Because we're supporting parallel execution.
    public static synchronized void flush() {

        if (extentReports != null) {
            extentReports.flush();
        }
    }
}