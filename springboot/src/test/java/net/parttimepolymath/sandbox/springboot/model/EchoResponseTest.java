package net.parttimepolymath.sandbox.springboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.sandbox.springboot.configuration.ObjectMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EchoResponseTest {

    private EchoResponse instanceOne;
    private EchoResponse instanceTwo;
    private EchoResponse instanceThree;

    private final UUID id1 = UUID.randomUUID();
    private final UUID id3 = UUID.randomUUID();

    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

    @BeforeEach
    void setUp() {
        instanceOne = EchoResponse.builder().id(id1).message("foo").build();
        instanceTwo = EchoResponse.builder().id(id1).message("foo").build();
        instanceThree = EchoResponse.builder().id(id3).message("baz").build();
    }

    @Test
    void builder() {
        assertNotNull(instanceOne);
        assertNotNull(instanceTwo);
        assertNotNull(instanceThree);
    }

    @Test
    void testToString() {
        assertEquals(String.format("EchoResponse(id=%s, message=foo)", id1.toString()), instanceOne.toString());
        assertEquals(String.format("EchoResponse(id=%s, message=foo)", id1.toString()), instanceTwo.toString());
        assertEquals(String.format("EchoResponse(id=%s, message=baz)", id3.toString()), instanceThree.toString());
    }

    @Test
    void testEquals() {
        assertEquals(instanceOne, instanceTwo);
        assertNotEquals(instanceOne, instanceThree);
    }

    @Test
    void canEqual() {
        assertTrue(instanceOne.canEqual(instanceTwo));
        assertTrue(instanceOne.canEqual(instanceThree));
        assertFalse(instanceOne.canEqual("A kind of fish"));
    }

    @Test
    void testHashCode() {
        assertEquals(instanceOne.hashCode(), instanceTwo.hashCode());
        assertNotEquals(instanceOne.hashCode(), instanceThree.hashCode());
        assertNotEquals(instanceOne.hashCode(), "a kind of fish".hashCode());
    }

    @Test
    void toJson() throws JsonProcessingException {
        assertEquals(String.format("{\"id\":\"%s\",\"message\":\"foo\"}", id1.toString()), mapper.writeValueAsString(instanceOne));
    }

    @Test
    void fromJson() throws JsonProcessingException {
        EchoResponse result = mapper.readValue(String.format("{\"id\":\"%s\",\"message\":\"foo\"}", id1.toString()), EchoResponse.class);
        assertNotNull(result);
        assertEquals(instanceOne, result);
    }
}