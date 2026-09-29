package org.practice.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By dashboard =
            By.id("dashboard");

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public boolean isDashboardDisplayed() {

        return elementActions.isDisplayed(
                dashboard
        );
    }
}