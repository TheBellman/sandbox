package net.parttimepolymath.sandbox.springboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.sandbox.springboot.configuration.ObjectMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchoRequestTest {

    private EchoRequest instanceOne;
    private EchoRequest instanceTwo;
    private EchoRequest instanceThree;

    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

    @BeforeEach
    void setUp() {
        instanceOne = new EchoRequest();
        instanceTwo = new EchoRequest();
        instanceThree = new EchoRequest();

        instanceOne.setMessage("foo");
        instanceTwo.setMessage(instanceOne.getMessage());
        instanceThree.setMessage("baz");
    }

    @Test
    void getMessage() {
        assertEquals("foo", instanceOne.getMessage());
        assertEquals("foo", instanceTwo.getMessage());
        assertEquals("baz", instanceThree.getMessage());
    }

    @Test
    void setMessage() {
        instanceOne.setMessage("baz");
        instanceTwo.setMessage(instanceOne.getMessage());
        instanceThree.setMessage("foo");


        assertEquals("baz", instanceOne.getMessage());
        assertEquals("baz", instanceTwo.getMessage());
        assertEquals("foo", instanceThree.getMessage());
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
    void testToString() {
        assertEquals("EchoRequest(message=foo)", instanceOne.toString());
        assertEquals("EchoRequest(message=foo)", instanceTwo.toString());
        assertEquals("EchoRequest(message=baz)", instanceThree.toString());
    }

    @Test
    void toJson() throws JsonProcessingException {
        String result = mapper.writeValueAsString(instanceOne);
        assertEquals("{\"message\":\"foo\"}", result);
    }

    @Test
    void fromJson() throws JsonProcessingException {
        EchoRequest result = mapper.readValue("{\"message\":\"baz\"}", EchoRequest.class);
        assertEquals(instanceThree, result);
    }
}