package net.parttimepolymath.sandbox.springboot.database;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessagesTest {

    private Messages instanceOne;
    private Messages instanceTwo;
    private Messages instanceThree;

    private final String id1 = UUID.randomUUID().toString();
    private final String id3 = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        instanceOne = Messages.builder().id(id1).message("foo").build();
        instanceTwo = Messages.builder().id(id1).message("foo").build();
        instanceThree = Messages.builder().id(id3).message("baz").build();
    }

    @Test
    void builder() {
        assertNotNull(instanceOne);
        assertNotNull(instanceTwo);
        assertNotNull(instanceThree);
    }

    @Test
    void testToString() {
        assertEquals(String.format("Messages(id=%s, message=foo)", id1), instanceOne.toString());
        assertEquals(String.format("Messages(id=%s, message=foo)", id1), instanceTwo.toString());
        assertEquals(String.format("Messages(id=%s, message=baz)", id3), instanceThree.toString());
    }

    @Test
    void getId() {
        assertEquals(id1, instanceOne.getId());
    }

    @Test
    void getMessage() {
        assertEquals("baz", instanceThree.getMessage());
    }

    @Test
    void setId() {
        instanceOne.setId("1234");
        assertEquals("1234", instanceOne.getId());
    }

    @Test
    void setMessage() {
        instanceOne.setMessage("baz");
        assertEquals("baz", instanceOne.getMessage());
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
    void toResponse() {
        EchoResponse response = instanceOne.toResponse();
        assertNotNull(response);
        assertEquals(String.format("EchoResponse(id=%s, message=foo)", id1), response.toString());
    }

    @Test
    void toResponseFrom() {
        EchoResponse response = Messages.toResponse(instanceOne);
        assertNotNull(response);
        assertEquals(String.format("EchoResponse(id=%s, message=foo)", id1), response.toString());
    }

    @Test
    void fromRequest() {
        EchoRequest request = new EchoRequest("foo");
        Messages result = Messages.fromRequest(request);
        assertNotNull(result.getId());
        assertEquals(request.getMessage(), result.getMessage());
    }

    @Test
    void fromRequestBad() {
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            Messages result = Messages.fromRequest(null);
        });

        assertTrue(exception.getMessage().contains("request must not be null"));
    }
}