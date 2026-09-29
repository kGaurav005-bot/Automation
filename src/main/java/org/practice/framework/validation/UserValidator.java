package org.practice.framework.validation;

import org.practice.framework.model.response.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;

public final class UserValidator {

    private UserValidator() {
    }

    public static void validate(UserResponse user) {

        assertThat(user)
                .as("User response")
                .isNotNull();

        assertThat(user.getId())
                .as("User ID")
                .isPositive();

        assertThat(user.getName())
                .as("User name")
                .isNotBlank();

        assertThat(user.getEmail())
                .as("User email")
                .contains("@");

        assertThat(user.getStatus())
                .as("User status")
                .isIn(
                        "ACTIVE",
                        "INACTIVE"
                );
    }
}