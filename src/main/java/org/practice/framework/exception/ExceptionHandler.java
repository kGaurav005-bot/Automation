package org.practice.framework.exception;

import org.apache.logging.log4j.Logger;
import org.practice.framework.utils.FrameworkLogger;

public final class ExceptionHandler {

    private static final Logger LOGGER =
            FrameworkLogger.getLogger(
                    ExceptionHandler.class
            );

    private ExceptionHandler() {
    }

    public static void handle(
            Throwable throwable) {

        if (throwable instanceof FrameworkException) {

            LOGGER.error(
                    "Framework failure: {}",
                    throwable.getMessage(),
                    throwable
            );

        } else {

            LOGGER.error(
                    "Unexpected automation failure: {}",
                    throwable.getMessage(),
                    throwable
            );
        }
    }
}