package net.parttimepolymath.sandbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private Application instance;

    // note this will verify that the builder will in fact work
    @BeforeEach
    void setUp() {
        instance = Application.builder().debugMode(false).iterations(20).namesFile("names.txt").build();
    }

    // tests variations on the builder
    @Test
    void build() {
        assertAll("build", () -> assertNotNull(Application.builder().build()),
                () -> assertNotNull(Application.builder().debugMode(true).build()),
                () -> assertNotNull(Application.builder().debugMode(false).build()),
                () -> assertNotNull(Application.builder().iterations(20).build()),
                () -> assertNotNull(Application.builder().debugMode(true).iterations(20).build())
        );
    }

    @Test
    void run() {
        assertDoesNotThrow(() -> instance.run());
    }
}