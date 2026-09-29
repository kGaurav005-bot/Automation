package org.practice.framework.api;

import java.util.UUID;

public final class IdempotencyKeyGenerator {

    private IdempotencyKeyGenerator() {
    }

    public static String generate() {

        return UUID.randomUUID()
                .toString();
    }
}