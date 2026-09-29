package org.practice.framework.testdata;

import org.practice.framework.testdata.strategy.JsonTestDataStrategy;
import org.practice.framework.testdata.strategy.TestDataStrategy;

public class TestDataFactory {

    public static <T> TestDataStrategy<T> createJsonStrategy(
            Class<T> dataType) {

        return new JsonTestDataStrategy<>(dataType);
    }
}