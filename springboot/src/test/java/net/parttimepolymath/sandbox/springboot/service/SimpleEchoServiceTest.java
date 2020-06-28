package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SimpleEchoServiceTest {
    private SimpleEchoService instance;

    @BeforeEach
    void setUp() {
        instance = new SimpleEchoService();
    }

    @Test
    void testEcho() {
        EchoResponse response = instance.echo(new EchoRequest("baz"));
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("baz", response.getMessage());
    }

    @Test
    void testBadEcho() {
        EchoResponse response = instance.echo(null);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("", response.getMessage());
    }

    @Test
    void testFetch() {
        EchoResponse response = instance.echo(new EchoRequest("fred"));
        Optional<EchoResponse> result = instance.fetch(response.getId());
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), response.getId());
        assertEquals(result.get().getMessage(), response.getMessage());
    }

    @Test
    void testFetchBad() {
        Optional<EchoResponse> result = instance.fetch(UUID.randomUUID());
        assertFalse(result.isPresent());
    }

    @Test
    void testFetchAll() {
        List<EchoResponse> result = instance.fetch();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        Arrays.asList("a", "b", "c", "d", "e", "f").forEach(msg -> {
            EchoResponse response = instance.echo(new EchoRequest(msg));
            assertNotNull(response);
        });

        result = instance.fetch();
        assertNotNull(result);
        assertEquals(6, result.size());
    }
}