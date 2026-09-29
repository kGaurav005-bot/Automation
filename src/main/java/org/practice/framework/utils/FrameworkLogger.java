package org.practice.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FrameworkLogger {

    private FrameworkLogger() {
    }

    public static Logger getLogger(
            Class<?> clazz) {

        return LogManager.getLogger(clazz);
    }
}