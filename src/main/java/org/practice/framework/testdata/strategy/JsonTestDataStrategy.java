package org.practice.framework.testdata.strategy;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class JsonTestDataStrategy<T>
        implements TestDataStrategy<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> dataType;

    public JsonTestDataStrategy(Class<T> dataType) {

        this.objectMapper = new ObjectMapper();
        this.dataType = dataType;
    }

    @Override
    public List<T> loadData(String source) {

        try {

            InputStream inputStream =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(source);

            if (inputStream == null) {

                throw new RuntimeException(
                        "Test data file not found: " + source
                );
            }

            JavaType listType =
                    objectMapper.getTypeFactory()
                            .constructCollectionType(
                                    List.class,
                                    dataType
                            );

            return objectMapper.readValue(
                    inputStream,
                    listType
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to load test data: " + source,
                    e
            );
        }
    }
}