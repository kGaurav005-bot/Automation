package org.practice.tests.security;

import org.practice.framework.security.SensitiveDataMasker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SensitiveDataMaskerTest {

    @Test
    public void shouldMaskSensitiveFields() {

        String json = """
                {
                  "username": "john",
                  "password": "MySecret123",
                  "token": "abc123",
                  "email": "john@test.com"
                }
                """;

        String masked =
                SensitiveDataMasker.maskJson(json);

        Assert.assertTrue(
                masked.contains("********")
        );

        Assert.assertFalse(
                masked.contains("MySecret123")
        );

        Assert.assertFalse(
                masked.contains("abc123")
        );

        Assert.assertTrue(
                masked.contains("john@test.com")
        );
    }
}