package net.parttimepolymath.sandbox.springboot.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {
    private Config config;

    @BeforeEach
    void setUp() {
        config = new Config();
    }

    @Test
    void testObjectMapper() {
        assertNotNull(config.objectMapper());
    }
}