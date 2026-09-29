package org.practice.tests.login;

import org.practice.framework.driver.DriverManager;
import org.practice.framework.pages.HomePage;
import org.practice.framework.pages.LoginPage;
import org.practice.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage =
                new LoginPage(
                        DriverManager.getDriver()
                );

        HomePage homePage =
                loginPage.login(
                        "testuser",
                        "password"
                );

        Assert.assertTrue(
                homePage.isDashboardDisplayed(),
                "Dashboard should be displayed after successful login"
        );
    }
}