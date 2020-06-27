package net.parttimepolymath.sandbox.springboot.service;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;

/**
 * interface to represent service or services that handle our incoming API request.
 *
 * @since 2020-06-26
 * @author Robert Hook
 */
public interface EchoService {
    public EchoResponse echo(EchoRequest request);
}
