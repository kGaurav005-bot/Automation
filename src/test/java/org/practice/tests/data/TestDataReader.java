package org.practice.tests.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public final class TestDataReader {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper();

    private TestDataReader() {
    }

    public static <T> List<T> readList(
            String resourcePath,
            TypeReference<List<T>> typeReference) {

        try (InputStream inputStream =
                     TestDataReader.class
                             .getClassLoader()
                             .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {

                throw new IllegalArgumentException(
                        "Test data file not found: "
                                + resourcePath
                );
            }

            return OBJECT_MAPPER.readValue(
                    inputStream,
                    typeReference
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read test data: "
                            + resourcePath,
                    e
            );
        }
    }
}