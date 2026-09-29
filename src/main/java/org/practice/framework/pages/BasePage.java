package org.practice.framework.pages;

import org.openqa.selenium.WebDriver;
import org.practice.framework.utils.BrowserActions;
import org.practice.framework.utils.ElementActions;
import org.practice.framework.utils.WaitUtils;

public abstract class BasePage {

    protected final WebDriver driver;

    protected final ElementActions elementActions;

    protected final BrowserActions browserActions;

    protected final WaitUtils waitUtils;

    protected BasePage(WebDriver driver) {

        this.driver = driver;

        this.elementActions =
                new ElementActions(driver);

        this.browserActions =
                new BrowserActions(driver);

        this.waitUtils =
                new WaitUtils(driver);
    }
}