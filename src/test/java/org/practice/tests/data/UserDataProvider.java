package org.practice.tests.data;

import com.fasterxml.jackson.core.type.TypeReference;
import org.practice.framework.model.request.UserRequest;
import org.testng.annotations.DataProvider;

import java.util.List;

public class UserDataProvider {
//By default, DataProvider executions aren't necessarily parallel. we need to add it
@DataProvider(
        name = "userData",
        parallel = true
)
public static Object[][] userData() {

    List<UserRequest> users =
            TestDataReader.readList(
                    "testdata/users.json",
                    new TypeReference<List<UserRequest>>() {
                    }
            );

    return users.stream()
            .map(user -> new Object[]{user})
            .toArray(Object[][]::new);
}
}