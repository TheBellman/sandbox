package net.parttimepolymath.sandbox.springboot.web;

import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import net.parttimepolymath.sandbox.springboot.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that responds to a valid post to "/api/echo"
 *
 * @since 2020-06-26
 * @author Robert Hook
 */
@RestController
@RequestMapping("/api")
public class EchoController {
    private final EchoService service;

    public EchoController(@Autowired final EchoService service) {
        this.service = service;
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public EchoResponse echo(@RequestBody EchoRequest echoRequest) {
        return service.echo(echoRequest);
    }
}
