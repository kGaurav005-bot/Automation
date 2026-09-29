package org.practice.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username =
            By.id("username");

    private final By password =
            By.id("password");

    private final By loginButton =
            By.id("login");

    private final By errorMessage =
            By.id("error-message");

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public LoginPage enterUsername(String username) {

        elementActions.type(
                this.username,
                username
        );

        return this;
    }

    public LoginPage enterPassword(String password) {

        elementActions.type(
                this.password,
                password
        );

        return this;
    }

    public HomePage clickLogin() {

        elementActions.click(loginButton);

        return new HomePage(driver);
    }

    public HomePage login(
            String username,
            String password) {

        enterUsername(username)
                .enterPassword(password);

        return clickLogin();
    }

    public String getErrorMessage() {

        return elementActions.getText(
                errorMessage
        );
    }
}