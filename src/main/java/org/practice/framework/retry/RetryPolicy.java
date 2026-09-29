package org.practice.framework.retry;

public class RetryPolicy {

    private final int maxAttempts;

    private final long initialDelayMs;

    private final long maxDelayMs;

    private final double multiplier;

    private final long jitterMs;

    public RetryPolicy(
            int maxAttempts,
            long initialDelayMs,
            long maxDelayMs,
            double multiplier,
            long jitterMs) {

        if (maxAttempts < 1) {

            throw new IllegalArgumentException(
                    "maxAttempts must be at least 1"
            );
        }

        if (initialDelayMs < 0) {

            throw new IllegalArgumentException(
                    "initialDelayMs cannot be negative"
            );
        }

        if (multiplier < 1.0) {

            throw new IllegalArgumentException(
                    "multiplier must be >= 1.0"
            );
        }

        this.maxAttempts = maxAttempts;
        this.initialDelayMs = initialDelayMs;
        this.maxDelayMs = maxDelayMs;
        this.multiplier = multiplier;
        this.jitterMs = jitterMs;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public long getInitialDelayMs() {
        return initialDelayMs;
    }

    public long getMaxDelayMs() {
        return maxDelayMs;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public long getJitterMs() {
        return jitterMs;
    }
}