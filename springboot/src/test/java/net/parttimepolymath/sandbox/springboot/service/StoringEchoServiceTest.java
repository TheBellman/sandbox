package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("release")
class StoringEchoServiceTest {
    @Autowired
    private StoringEchoService instance;

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
        assertFalse(result.isEmpty());
        assertEquals(22, result.size());

        Arrays.asList("a", "b", "c", "d", "e", "f").forEach(msg -> {
            EchoResponse response = instance.echo(new EchoRequest(msg));
            assertNotNull(response);
        });

        List<EchoResponse> result2 = instance.fetch();
        assertNotNull(result2);
        assertEquals(6, result2.size() - result.size());
    }
}