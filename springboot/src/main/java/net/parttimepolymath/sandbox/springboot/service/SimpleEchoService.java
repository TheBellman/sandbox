package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * implementation of the EchoService that just assembles a response and does nothing else.
 *
 * @since 2020-06-26
 * @author Robert Hook
 */
@Component
public class SimpleEchoService implements EchoService {
    @Override
    public EchoResponse echo(EchoRequest request) {
        return EchoResponse.builder()
                .id(UUID.randomUUID())
                .message(request.getMessage())
                .build();
    }
}
