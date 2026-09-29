package org.practice.tests.base;

import org.practice.framework.config.ConfigManager;
import org.practice.framework.driver.DriverManager;
import org.practice.framework.factory.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {

        DriverFactory.initializeDriver();

        DriverManager.getDriver().get(
                ConfigManager.getInstance().getBaseUrl()
        );
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();

    }
}