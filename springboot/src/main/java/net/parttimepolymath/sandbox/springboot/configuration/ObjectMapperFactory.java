package net.parttimepolymath.sandbox.springboot.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;

/**
 * factory to create an instance of the ObjectMapper. This is wrapped up with the Lombok
 * UtilityClass annotation and a double-locked builder to try to make sure we only ever have
 * one of these floating around. The definition of the Bean itself is in Config in order
 * to make it easy to build a mapper without having to fire up all the spring wiring in tests.
 */
@UtilityClass
public class ObjectMapperFactory {
    private volatile ObjectMapper instance = null;

    /**
     * obtain a non-null ObjectMapper.
     *
     * @return a non-null ObjectMapper.
     */
    public ObjectMapper getObjectMapper() {
        if (instance == null) {
            synchronized (ObjectMapperFactory.class) {
                if (instance == null) {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.disable(SerializationFeature.INDENT_OUTPUT);
                    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
                    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
                    instance = mapper;
                }
            }
        }
        return instance;
    }
}
