package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * interface to represent service or services that handle our incoming API request.
 *
 * @author Robert Hook
 * @since 2020-06-26
 */
public interface EchoService {
    /**
     * make a request for an echo, and get a response back.
     *
     * @param request the request to make, assumed non null.
     * @return a non-null response.
     */
    EchoResponse echo(EchoRequest request);

    /**
     * ask for a previous echo response to be returned.
     *
     * @param id the ID to ask for.
     * @return a non-null but possibly empty response.
     */
    Optional<EchoResponse> fetch(UUID id);

    /**
     * ask for all previous echo responses to be returned.
     * @return a non-null but possibly empty list
     */
    List<EchoResponse> fetch();
}
