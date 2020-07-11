package org.jason.util;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

public final class JasonThreadUtil {

    public static void sleepQuietly(@Nonnull TimeUnit unit, int count) {

        Objects.requireNonNull(unit);
        Parameters.requireTrue(count > 0, "sleep number cannot be negative");

        try {
            unit.sleep(count);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepQuietlyInSeconds(int seconds) {
        sleepQuietly(TimeUnit.SECONDS, seconds);
    }
}
