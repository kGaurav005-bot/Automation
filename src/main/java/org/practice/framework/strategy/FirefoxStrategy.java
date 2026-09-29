package org.practice.framework.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.practice.framework.config.ConfigManager;

public class FirefoxStrategy implements BrowserStrategy {

    @Override
    public WebDriver createDriver(){

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();

        if(ConfigManager.getInstance().isHeadless()){

            options.addArguments("-headless");

        }

        return new FirefoxDriver(options);

    }

}