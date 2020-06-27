package net.parttimepolymath.sandbox.springboot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.sandbox.springboot.configuration.ObjectMapperFactory;
import net.parttimepolymath.sandbox.springboot.configuration.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VersionResponseTest {
    private VersionResponse instance;
    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

    @BeforeEach
    void setUp() {
        Version version = new Version();
        version.setName("name");
        version.setBuild("build");
        version.setVersion("version");
        version.setProfile("profile");

        instance = new VersionResponse(version);
    }

    @Test
    void getName() {
        assertEquals("name", instance.getName());
    }

    @Test
    void getVersion() {
        assertEquals("version", instance.getVersion());
    }

    @Test
    void getBuild() {
        assertEquals("build", instance.getBuild());
    }

    @Test
    void getProfile() {
        assertEquals("profile", instance.getProfile());
    }

    @Test
    void toJson() throws JsonProcessingException {
        assertEquals("{\"name\":\"name\",\"version\":\"version\",\"build\":\"build\",\"profile\":\"profile\"}",
                mapper.writeValueAsString(instance));
    }

    @Test
    void fromJson() throws JsonProcessingException {
        assertEquals(instance, mapper.readValue("{\"name\":\"name\",\"version\":\"version\",\"build\":\"build\",\"profile\":\"profile\"}", VersionResponse.class));
    }
}