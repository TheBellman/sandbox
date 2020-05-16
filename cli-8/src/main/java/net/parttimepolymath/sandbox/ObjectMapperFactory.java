package net.parttimepolymath.sandbox;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * it's usually safest when using Jackson to build a single instance of the object mapper, and
 * it's definitely convenient to have a convenient way of getting it for tests.
 *
 * @author Robert Hook
 * @since 2020-05-16
 */
@ThreadSafe
public final class ObjectMapperFactory {
    private static AtomicReference<ObjectMapper> instance = new AtomicReference<>();

    /**
     * get the existing object mapper or make a new one. This is thread safe, but does
     * have the side effect that in a race condition more than one mapper might get created
     * and then discarded.
     *
     * note also the use of findAndRegisterModules() to allow us to deal with Instant/ISO8601 conversion
     *
     * @return a non-null object mapper.
     */
    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = instance.get();
        if (mapper == null) {
            mapper = new ObjectMapper()
                            .findAndRegisterModules()
                            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                            .disable(SerializationFeature.INDENT_OUTPUT)
                            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

            instance.compareAndSet(null, mapper);
        }
        return instance.get();
    }
}
