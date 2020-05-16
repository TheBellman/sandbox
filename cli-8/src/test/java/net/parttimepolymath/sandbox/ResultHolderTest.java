package net.parttimepolymath.sandbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ResultHolderTest {
    private static ObjectMapper mapper;

    @BeforeAll
    static void setupTest() {
        mapper = ObjectMapperFactory.getObjectMapper();
    }

    @Test
    void noArgsConstructor() {
        ResultHolder instance = new ResultHolder();
        assertAll(
                () -> assertNotNull(instance),
                () -> assertEquals(0, instance.getCount()),
                () -> assertNull(instance.getStartTime()));
    }

    @Test
    void allArgsConstructor() {
        ResultHolder instance = new ResultHolder(10, Instant.now());
        assertAll(
                () -> assertNotNull(instance),
                () -> assertEquals(10, instance.getCount()),
                () -> assertNotNull(instance.getStartTime()));
    }

    @Test
    void builder() {
        Instant now = Instant.now();
        ResultHolder instance = ResultHolder.builder().count(100).startTime(now).build();
        assertAll(
                () -> assertNotNull(instance),
                () -> assertEquals(100, instance.getCount()),
                () -> assertEquals(now, instance.getStartTime()));
    }

    @Test
    void setters() {
        Instant then = Instant.now().minusSeconds(100);
        ResultHolder instance = ResultHolder.builder().count(100).startTime(Instant.now()).build();
        instance.setCount(10);
        instance.setStartTime(then);
        assertAll(
                () -> assertNotNull(instance),
                () -> assertEquals(10, instance.getCount()),
                () -> assertEquals(then, instance.getStartTime()));
    }

    @Test
    void testToString() {
        ResultHolder instance = ResultHolder.builder()
                .count(100)
                .startTime(Instant.parse("2020-05-16T18:35:24.00Z")).build();
        assertEquals("ResultHolder(count=100, startTime=2020-05-16T18:35:24Z)", instance.toString());
    }

    @Test
    void toJson() throws JsonProcessingException {
        ResultHolder instance = ResultHolder.builder()
                .count(100)
                .startTime(Instant.parse("2020-05-16T18:35:24.00Z")).build();
        String result = mapper.writeValueAsString(instance);
        assertEquals("{\"count\":100,\"startTime\":\"2020-05-16T18:35:24Z\"}", result);
    }

    @Test
    void fromJson() throws JsonProcessingException {
        ResultHolder instance = ResultHolder.builder()
                .count(100)
                .startTime(Instant.parse("2020-05-16T18:35:24.00Z")).build();
        assertEquals(instance, mapper.readValue("{\"count\":100,\"startTime\":\"2020-05-16T18:35:24Z\"}", ResultHolder.class));
    }
}