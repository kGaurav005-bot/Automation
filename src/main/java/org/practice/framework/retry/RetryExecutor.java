package org.practice.framework.retry;

import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.practice.framework.exception.ApiConnectionException;
import org.practice.framework.exception.ApiRetryExhaustedException;
import org.practice.framework.utils.FrameworkLogger;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public final class RetryExecutor {

    private static final Logger LOGGER =
            FrameworkLogger.getLogger(
                    RetryExecutor.class
            );

    private RetryExecutor() {
    }

    public static Response execute(
            Supplier<Response> operation,
            RetryPolicy policy) {

        for (int attempt = 1;
             attempt <= policy.getMaxAttempts();
             attempt++) {

            try {

                LOGGER.info(
                        "Executing API attempt {}/{}",
                        attempt,
                        policy.getMaxAttempts()
                );

                Response response =
                        operation.get();

                // Non-retryable response:
                // return it to the caller immediately.
                if (!FailureClassifier
                        .shouldRetry(response)) {

                    return response;
                }

                LOGGER.warn(
                        "Retryable HTTP status {} received "
                                + "on attempt {}/{}",
                        response.statusCode(),
                        attempt,
                        policy.getMaxAttempts()
                );

                // Retryable HTTP response,
                // but no attempts remain.
                if (attempt ==
                        policy.getMaxAttempts()) {

                    throw new ApiRetryExhaustedException(
                            "API retry exhausted after "
                                    + policy.getMaxAttempts()
                                    + " attempts. "
                                    + "Final HTTP status: "
                                    + response.statusCode()
                    );
                }

            } catch (ApiRetryExhaustedException e) {

                // Do not re-classify our own framework exception.
                throw e;

            } catch (Throwable throwable) {

                if (!FailureClassifier
                        .isRetryableException(
                                throwable
                        )) {

                    throw new ApiConnectionException(
                            "Non-retryable API communication failure",
                            throwable
                    );
                }

                LOGGER.warn(
                        "Retryable API exception on attempt {}/{}: {}",
                        attempt,
                        policy.getMaxAttempts(),
                        throwable.getMessage()
                );

                if (attempt ==
                        policy.getMaxAttempts()) {

                    throw new ApiRetryExhaustedException(
                            "API retry exhausted after "
                                    + policy.getMaxAttempts()
                                    + " attempts due to "
                                    + "a communication failure",
                            throwable
                    );
                }
            }

            long delay =
                    calculateDelay(
                            attempt,
                            policy
                    );

            LOGGER.info(
                    "Waiting {} ms before retry",
                    delay
            );

            sleep(delay);
        }

        // Defensive fallback.
        // Normally execution can never reach here.
        throw new ApiRetryExhaustedException(
                "API retry execution ended unexpectedly"
        );
    }

    private static long calculateDelay(
            int attempt,
            RetryPolicy policy) {

        double exponentialDelay =
                policy.getInitialDelayMs()
                        * Math.pow(
                        policy.getMultiplier(),
                        attempt - 1
                );

        long cappedDelay =
                Math.min(
                        (long) exponentialDelay,
                        policy.getMaxDelayMs()
                );

        long jitter = 0;

        if (policy.getJitterMs() > 0) {

            jitter =
                    ThreadLocalRandom
                            .current()
                            .nextLong(
                                    policy.getJitterMs() + 1
                            );
        }

        return cappedDelay + jitter;
    }

    private static void sleep(
            long delayMs) {

        try {

            Thread.sleep(delayMs);

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            throw new ApiRetryExhaustedException(
                    "API retry interrupted while "
                            + "waiting for the next attempt",
                    e
            );
        }
    }
}