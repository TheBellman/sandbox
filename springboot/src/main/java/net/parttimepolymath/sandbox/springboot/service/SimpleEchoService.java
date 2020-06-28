package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

/**
 * implementation of the EchoService that uses local storage.
 *
 * @author Robert Hook
 * @since 2020-06-26
 */
@Component
@Profile("development")
public class SimpleEchoService implements EchoService {
    private final Map<UUID, String> cache = new TreeMap<>();

    @Override
    public EchoResponse echo(final EchoRequest request) {
        UUID id = UUID.randomUUID();
        String message = Optional.ofNullable(request).orElse(new EchoRequest("")).getMessage();
        cache.put(id, message);
        return EchoResponse.builder().id(id).message(message).build();
    }

    @Override
    public Optional<EchoResponse> fetch(UUID id) {
        String message = cache.get(id);
        if (message == null) {
            return Optional.empty();
        } else {
            return Optional.of(new EchoResponse(id, message));
        }
    }
}
