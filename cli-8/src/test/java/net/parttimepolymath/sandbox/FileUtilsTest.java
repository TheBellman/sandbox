package net.parttimepolymath.sandbox;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void hasFile() {
        assertTrue(FileUtils.hasFile("pom.xml"));
    }

    @Test
    void readFileAsList() {
        List<String> result = FileUtils.readFileAsList("pom.xml");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

}