package net.parttimepolymath.sandbox.springboot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMapperFactoryTest {

    @Test
    void testConstruction() {
        assertNotNull(ObjectMapperFactory.getObjectMapper());
    }

    @Test
    void testSingleton() {
        ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
        assertEquals(mapper, ObjectMapperFactory.getObjectMapper());
    }
}