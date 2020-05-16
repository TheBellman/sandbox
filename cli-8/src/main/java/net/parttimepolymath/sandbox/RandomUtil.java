package net.parttimepolymath.sandbox;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * convenience wrapper around ThreadLocalRandom - these are intended to map generation of a random integer
 * into various application or business contexts, making the use of the random integer a bit more readable.
 *
 * @author Robert Hook
 * @since 2020-05-16
 */
public final class RandomUtil {
    /**
     * given a collection like a list that has "size" elements, return a random between 0 and size-1
     *
     * @param size the collection size
     * @return a random integer between 0 and size-1, inclusive
     */
    public static int randomOffset(final int size) {
        return ThreadLocalRandom.current().nextInt(size) % size;
    }

    /**
     * get a random int between lower and upper inclusive
     *
     * @param lower the inclusive lower bound
     * @param upper the inclusive upper bound
     * @return a random integer between lower and upper inclusive
     */
    public static int randomBetween(final int lower, final int upper) {
        assert lower < upper : "invalid range";
        return ThreadLocalRandom.current().nextInt(lower, upper + 1);
    }

    /**
     * return true for a one-in-n chance
     *
     * @param n the odds
     * @return true if we have hit a one-in n chance
     */
    public static boolean oneIn(final int n) {
        return ThreadLocalRandom.current().nextInt(n) == 0;
    }

    /**
     * generates a random date in the past between a minimum and maximum number of days past
     *
     * @param min the lower bound of the possible date
     * @param max the upper bound of the possible date
     * @return a non null LocalDate
     */
    public static LocalDate pastDate(final int min, final int max) {
        assert min < max : "invalid range";
        int days = ThreadLocalRandom.current().nextInt(max - min + 1) + min;
        return LocalDate.now().minus(days, ChronoUnit.DAYS);
    }
}
