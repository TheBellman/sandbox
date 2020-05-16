package net.parttimepolymath.sandbox.picker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringListFilePickerTest {
    private StringListFilePicker instance;

    @BeforeEach
    void setUp() {
        instance = new StringListFilePicker("names.txt");
    }

    @Test
    void size() {
        assertEquals(200, instance.size());
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
