package net.parttimepolymath.sandbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationPropertiesTest {

    @Test
    void getAppName() {
        assertNotNull(ApplicationProperties.getAppName());
    }

    @Test
    void getIterations() {
        assertTrue(ApplicationProperties.getIterations() > -1);
    }
}