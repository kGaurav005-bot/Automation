package org.practice.framework.utils;

import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private final WebDriver driver;

    public BrowserActions(WebDriver driver) {

        this.driver = driver;
    }

    public void navigateTo(String url) {

        driver.get(url);
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    public String getTitle() {

        return driver.getTitle();
    }

    public void refresh() {

        driver.navigate().refresh();
    }

    public void back() {

        driver.navigate().back();
    }

    public void forward() {

        driver.navigate().forward();
    }
}