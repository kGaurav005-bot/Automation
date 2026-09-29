package org.practice.framework.utils;

public final class ThreadUtils {

    private ThreadUtils() {
    }

    public static String getThreadName() {

        return Thread.currentThread()
                .getName();
    }

    public static long getThreadId() {

        return Thread.currentThread().getId();
    }
}