package org.practice.framework.factory;

import org.practice.framework.config.ConfigManager;
import org.practice.framework.driver.DriverManager;
import org.practice.framework.strategy.BrowserStrategy;

public class DriverFactory {
    public static void initializeDriver(){

        String browser =
                ConfigManager.getInstance().getBrowser();
/****
 * BrowserStrategy strategy = strategies
 *     .get(browser.toLowerCase())
 *     .get();
 */
        BrowserStrategy strategy =
                BrowserStrategyFactory.getStrategy(browser);

        DriverManager.setDriver(
                strategy.createDriver());

    }
}
