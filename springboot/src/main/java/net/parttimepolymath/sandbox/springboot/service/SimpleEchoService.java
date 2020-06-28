package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * implementation of the EchoService that just assembles a response and does nothing else.
 *
 * @author Robert Hook
 * @since 2020-06-26
 */
@Component
@Profile("development")
public class SimpleEchoService implements EchoService {
    @Override
    public EchoResponse echo(final EchoRequest request) {
        if (request == null) {
            return EchoResponse.builder().id(UUID.randomUUID()).message("").build();
        }

        return EchoResponse.builder().id(UUID.randomUUID()).message(request.getMessage()).build();
    }
}
