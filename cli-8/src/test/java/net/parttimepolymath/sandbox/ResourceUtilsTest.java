package net.parttimepolymath.sandbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ResourceUtilsTest {

    @Test
    void loadProperties() {
        assertAll("goodFile",
                () -> assertNotNull(ResourceUtils.loadProperties("application.properties")),
                () -> assertFalse(ResourceUtils.loadProperties("application.properties").isEmpty())
        );

        assertAll("badfile",
                () -> assertNotNull(ResourceUtils.loadProperties("nosuchfile")),
                () -> assertTrue(ResourceUtils.loadProperties("nosuchfile").isEmpty())
        );
    }

    @Test
    void readResourceFileAsList() {
        assertAll("goodFile",
                () -> assertNotNull(ResourceUtils.readResourceFileAsList("names.txt")),
                () -> assertFalse(ResourceUtils.readResourceFileAsList("names.txt").isEmpty())
                );

        assertAll("badFile",
                () -> assertNotNull(ResourceUtils.readResourceFileAsList("nosuch.txt")),
                () -> assertTrue(ResourceUtils.readResourceFileAsList("nosuch.txt").isEmpty())
        );

        assertAll("nullFile",
                () -> assertNotNull(ResourceUtils.readResourceFileAsList(null)),
                () -> assertTrue(ResourceUtils.readResourceFileAsList(null).isEmpty())
        );

    }

    @Test
    void getResourceAsStream() {
        assertAll("goodFile",
                () -> assertNotNull(ResourceUtils.getResourceAsStream("application.properties")),
                () -> assertTrue(ResourceUtils.getResourceAsStream("application.properties").isPresent())
        );

        assertAll("badFile",
                () -> assertNotNull(ResourceUtils.getResourceAsStream("nosuchfile")),
                () -> assertFalse(ResourceUtils.getResourceAsStream("nosuchfile").isPresent())
        );

        assertAll("goodFilePrefix",
                () -> assertNotNull(ResourceUtils.getResourceAsStream("/application.properties")),
                () -> assertTrue(ResourceUtils.getResourceAsStream("/application.properties").isPresent())
        );

        assertAll("badFilePrefix",
                () -> assertNotNull(ResourceUtils.getResourceAsStream("/nosuchfile")),
                () -> assertFalse(ResourceUtils.getResourceAsStream("/nosuchfile").isPresent())
        );
    }
}