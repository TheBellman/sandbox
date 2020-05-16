package net.parttimepolymath.sandbox.picker;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class StringPairListFilePickerTest {
    private StringPairListFilePicker instance;

    @BeforeEach
    void setUp() {
        instance = new StringPairListFilePicker("names.txt", " ");
    }

    @Test
    void size() {
        assertEquals(200, instance.size());
    }

    @Test
    void pickPair() {
        IntStream.rangeClosed(1, 10000).forEach(x -> {
            Pair<String, String> result = instance.pickPair();
            assertNotNull(result);
            assertFalse(StringUtils.isEmpty(result.getLeft()));
            assertFalse(StringUtils.isEmpty(result.getRight()));
        });

    }

    @Test
    void pick() {
        IntStream.rangeClosed(1, 10000).forEach(x -> {
            assertNotNull(instance.pick());
        });
    }

    @Test
    void maybePick() {
        AtomicInteger count = new AtomicInteger();
        IntStream.rangeClosed(1, 10000).forEach(x -> {
            Optional<String> result = instance.maybePick(10);
            assertNotNull(result);
            if (result.isPresent()) count.getAndIncrement();
        });

        assertTrue(count.get()>900);
        assertTrue(count.get()<1100);
    }
}