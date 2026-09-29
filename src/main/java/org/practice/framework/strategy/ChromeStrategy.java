package org.practice.framework.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.practice.framework.config.ConfigManager;

public class ChromeStrategy implements BrowserStrategy {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if(ConfigManager.getInstance().isHeadless()){

            options.addArguments("--headless=new");

        }
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

}