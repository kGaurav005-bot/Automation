package org.practice.framework.testdata.strategy;

import java.util.List;

public interface TestDataStrategy<T> {

    List<T> loadData(String source);

}