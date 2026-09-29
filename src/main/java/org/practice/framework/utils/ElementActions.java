package org.practice.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {

    private final WebDriver driver;
    private final WaitUtils wait;

    public ElementActions(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void click(By locator) {

        wait.waitForClickable(locator).click();
    }

    public void type(By locator, String text) {

        WebElement element =
                wait.waitForVisible(locator);

        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {

        return wait.waitForVisible(locator)
                .getText();
    }

    public boolean isDisplayed(By locator) {

        try {

            return wait.waitForVisible(locator)
                    .isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void clear(By locator) {

        wait.waitForVisible(locator)
                .clear();
    }
}