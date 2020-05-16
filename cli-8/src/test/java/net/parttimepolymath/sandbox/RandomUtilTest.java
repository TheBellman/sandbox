package net.parttimepolymath.sandbox;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RandomUtilTest {

    @Test
    void randomOffset() {

        AtomicInteger zeroCount = new AtomicInteger();
        AtomicInteger maxCount = new AtomicInteger();

        IntStream.rangeClosed(1, 10000).forEach(x -> {
            int result = RandomUtil.randomOffset(10);
            assertAll(() -> assertTrue(result > -1), () -> assertTrue(result < 10));
            if (result == 0) zeroCount.getAndIncrement();
            if (result == 10) maxCount.getAndIncrement();
        });

        // somewhere in our 10000 iterations we should get at least one zero, we hope.
        // we also must not get any instances of the out of range value!
        assertTrue(zeroCount.get() > 0);
        assertEquals(0, maxCount.get());
    }

    @Test
    void randomBetween() {
        IntStream.rangeClosed(1, 10000).forEach(x -> {
            int result = RandomUtil.randomBetween(10, 20);
            assertAll(() -> assertTrue(result >= 10), () -> assertTrue(result <= 20));
        });
    }

    @Test
    void randomBetweenBadRange() {
        assertThrows(AssertionError.class, () -> RandomUtil.randomBetween(20, 10));
        assertThrows(AssertionError.class, () -> RandomUtil.randomBetween(10, 10));
        assertDoesNotThrow(() -> RandomUtil.randomBetween(10, 20));
    }

    @Test
    void oneIn() {
        // if we look for 1-in-100 100,000 times, we should get about 1000 matches
        long result = IntStream.rangeClosed(1, 100000).filter(x -> RandomUtil.oneIn(100)).count();
        assertTrue(result > 900);
        assertTrue(result < 1100);
    }

    @Test
    void pastDate() {
        LocalDate today = LocalDate.now();
        IntStream.rangeClosed(1, 1000).forEach(x -> {
            LocalDate result = RandomUtil.pastDate(10, 100);
            assertTrue(today.minusDays(9).isAfter(result));
            assertTrue(today.minusDays(101).isBefore(result));
        });
    }
}