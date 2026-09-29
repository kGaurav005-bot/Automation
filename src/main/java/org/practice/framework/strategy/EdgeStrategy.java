package org.practice.framework.strategy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeStrategy implements BrowserStrategy{

    @Override
    public WebDriver createDriver(){

        WebDriverManager.edgedriver().setup();

        return new EdgeDriver();

    }

}