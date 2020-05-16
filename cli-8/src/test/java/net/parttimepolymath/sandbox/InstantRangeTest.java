package net.parttimepolymath.sandbox;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class InstantRangeTest {
    private Instant from;
    private Instant to;
    private InstantRange instance;

    @BeforeEach
    public void setUp() throws Exception {
        from = Instant.now().minus(10, ChronoUnit.HOURS);
        to = Instant.now();
        instance = new InstantRange(from, to, ChronoUnit.HOURS);
    }

    @Test
    public void testConstructor() {
        assertEquals(from.truncatedTo(ChronoUnit.HOURS), instance.getFromInstant());
        assertEquals(to.truncatedTo(ChronoUnit.HOURS), instance.getToInstant());
        assertEquals(ChronoUnit.HOURS, instance.getInterval());
    }

    @Test
    public void testReverseConstructor() {
        instance = new InstantRange(to, from, ChronoUnit.HOURS);
        assertEquals(from.truncatedTo(ChronoUnit.HOURS), instance.getFromInstant());
        assertEquals(to.truncatedTo(ChronoUnit.HOURS), instance.getToInstant());
        assertEquals(ChronoUnit.HOURS, instance.getInterval());
    }

    @Test
    public void testNullRange() {
        instance = new InstantRange(null, null, ChronoUnit.HOURS);
        assertNotNull(instance.getFromInstant());
        assertNotNull(instance.getToInstant());
        assertTrue(instance.getFromInstant().isBefore(instance.getToInstant()));
    }

    @Test
    public void testNullUnit() {
        instance = new InstantRange(from, to, null);
        assertNotNull(instance.getFromInstant());
        assertNotNull(instance.getToInstant());
        assertEquals(ChronoUnit.HOURS, instance.getInterval());
    }

    @Test
    public void testAllNull() {
        instance = new InstantRange(null, null, null);
        assertNotNull(instance.getFromInstant());
        assertNotNull(instance.getToInstant());
        assertTrue(instance.getFromInstant().isBefore(instance.getToInstant()));
        assertEquals(ChronoUnit.HOURS, instance.getInterval());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(instance.isEmpty());

        instance = new InstantRange(from, from, ChronoUnit.HOURS);
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals(String.format("InstantRange(fromInstant=%s, toInstant=%s, interval=%s)",
                from.truncatedTo(ChronoUnit.HOURS), to.truncatedTo(ChronoUnit.HOURS), ChronoUnit.HOURS),
                instance.toString());
    }

    @Test
    public void testGetIterator() {
        assertNotNull(instance.iterator());
    }

    @Test
    public void testSize() {
        assertEquals(11, instance.size());

        // this should be 2 because we have distinct beginning and end of range
        instance = new InstantRange(null, null, ChronoUnit.MINUTES);
        assertEquals(2, instance.size());

        // this should be 1 because we will have a single tick.
        instance = new InstantRange(from, from, ChronoUnit.MINUTES);
        assertEquals(1, instance.size());
    }

    @Test
    public void testIterator() {
        int count = 0;
        for (Instant instant : instance) {
            assertNotNull(instant);
            count++;
        }
        // 11 because we are going from "10 hours ago" up to "current hour". Clocks are not always intuitive.
        assertEquals(11, count);
    }

    @Test
    public void testAsStream() {
        instance.asStream().forEach(Assertions::assertNotNull);
        assertEquals(11, instance.asStream().count());
    }

    @Test
    public void testToArray() {
        Object[] result = instance.toArray();
        assertNotNull(result);
        assertEquals(11, result.length);
        for (Object o : result) {
            assertNotNull(o);
            assertTrue(o instanceof Instant);
        }
    }

    @Test
    public void testToArrayAlternate() {
        Instant[] result = instance.toArray(new Instant[0]);
        assertNotNull(result);
        assertEquals(11, result.length);
        for (Instant instant : result) {
            assertNotNull(instant);
        }
    }

    @Test
    public void testToArrayAlternateTwo() {
        Instant[] result = instance.toArray(new Instant[20]);
        assertNotNull(result);
        assertEquals(20, result.length);
        assertNull(result[12]);
    }

    @Test
    public void testHash() {
        assertEquals(instance.hashCode(), new InstantRange(from, to, ChronoUnit.HOURS).hashCode());
        assertNotEquals(instance.hashCode(), new InstantRange(from, Instant.now(), ChronoUnit.MILLIS).hashCode());
    }

    @Test
    public void testEquals() {
        assertNotEquals(null, instance);
        assertNotEquals("bottle of wine", instance);
        assertEquals(instance, instance);

        assertEquals(instance, new InstantRange(from, to, ChronoUnit.HOURS));
        assertNotEquals(instance, new InstantRange(null, null, null));
    }

    @Test
    public void testContains() {
        assertFalse(instance.contains(null));
        assertTrue(instance.contains(from.plus(1, ChronoUnit.HOURS).truncatedTo(ChronoUnit.HOURS)));
        assertFalse(instance.contains(to.plus(1, ChronoUnit.HOURS).truncatedTo(ChronoUnit.HOURS)));
        assertTrue(instance.contains(to.truncatedTo(ChronoUnit.HOURS)));
        assertTrue(instance.contains(from.truncatedTo(ChronoUnit.HOURS)));
    }

    @Test
    public void testContainsAll() {
        assertTrue(instance.containsAll(Arrays.asList(from.plus(1, ChronoUnit.HOURS).truncatedTo(ChronoUnit.HOURS),
                to.truncatedTo(ChronoUnit.HOURS), from.truncatedTo(ChronoUnit.HOURS))));

        assertFalse(instance.containsAll(Arrays.asList(Instant.now().plusSeconds(500), from.plusSeconds(500),
                to.truncatedTo(ChronoUnit.HOURS), from.truncatedTo(ChronoUnit.HOURS))));
    }

    @Test
    public void testIteratorEmptyRange() {
        int count = 0;
        for (Instant instant : new InstantRange(from, from, ChronoUnit.DAYS)) {
            assertNotNull(instant);
            count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void testClear() {
        assertThrows(UnsupportedOperationException.class, () -> instance.clear());
    }

    @Test
    public void testAdd() {
        assertThrows(UnsupportedOperationException.class, () -> instance.add(Instant.now()));
    }

    @Test
    public void testAddAll() {
        assertThrows(UnsupportedOperationException.class, () -> instance.addAll(Collections.singletonList(Instant.now())));
    }

    @Test
    public void testRemove() {
        assertThrows(UnsupportedOperationException.class,
                () -> instance.remove(Instant.now()));
    }

    @Test
    public void testRemoveAll() {
        assertThrows(UnsupportedOperationException.class,
                () -> instance.removeAll(Collections.singletonList(Instant.now())));
    }

    @Test
    public void testRetainAll() {
        assertThrows(UnsupportedOperationException.class,
                () -> instance.retainAll(Collections.singletonList(Instant.now())));
    }
}