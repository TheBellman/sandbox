package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}