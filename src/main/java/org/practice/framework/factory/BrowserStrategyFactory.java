package org.practice.framework.factory;

import org.practice.framework.strategy.BrowserStrategy;
import org.practice.framework.strategy.ChromeStrategy;
import org.practice.framework.strategy.EdgeStrategy;
import org.practice.framework.strategy.FirefoxStrategy;

public class BrowserStrategyFactory {
    public static BrowserStrategy getStrategy(String browser){

        /*
The current BrowserStrategyFactory still contains a switch statement. It's acceptable and common,
 but in very large frameworks you can eliminate even that by using a registration-based approach:
        private static final Map<String, Supplier<BrowserStrategy>> strategies = Map.of(
    "chrome", ChromeStrategy::new,
    "firefox", FirefoxStrategy::new,
    "edge", EdgeStrategy::new
);

BrowserStrategy strategy = strategies
    .get(browser.toLowerCase())
    .get();
         */

        return switch(browser.toLowerCase()){

            case "chrome" -> new ChromeStrategy();

            case "firefox" -> new FirefoxStrategy();

            case "edge" -> new EdgeStrategy();

            default -> throw new RuntimeException(
                    "Unsupported Browser : "+browser);

        };

    }

}
