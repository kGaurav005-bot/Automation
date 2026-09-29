package org.practice.framework.validation;

import org.practice.framework.model.request.UserRequest;

public final class UserRequestValidator {

    private UserRequestValidator() {
    }

    public static void validate(
            UserRequest request) {

        if (request == null) {

            throw new IllegalArgumentException(
                    "UserRequest cannot be null"
            );
        }

        if (request.getName() == null
                || request.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "User name is required"
            );
        }

        if (request.getEmail() == null
                || request.getEmail().isBlank()) {

            throw new IllegalArgumentException(
                    "User email is required"
            );
        }
    }
}