package net.parttimepolymath.sandbox.springboot.service;

import lombok.extern.slf4j.Slf4j;
import net.parttimepolymath.sandbox.springboot.database.Messages;
import net.parttimepolymath.sandbox.springboot.database.MessagesRepository;
import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * instance of the echo service that persists messages in the database.
 *
 * @since 2020-06-27
 * @author Robert Hook
 */
@Component
@Profile("release")
@Slf4j
public class StoringEchoService implements EchoService {
    private final MessagesRepository repository;

    public StoringEchoService(@Autowired final MessagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public EchoResponse echo(EchoRequest request) {
        if (request == null) {
            return EchoResponse.builder().id(UUID.randomUUID()).message("").build();
        }

        Messages message = Messages.fromRequest(request);
        repository.save(message);

        return EchoResponse.builder().id(UUID.fromString(message.getId())).message(request.getMessage()).build();
    }

    @Override
    public Optional<EchoResponse> fetch(UUID id) {
        if (id == null) {
            return Optional.empty();
        }

        return repository.findById(id.toString()).map(messages -> messages.toResponse());
    }

}
