package net.parttimepolymath.sandbox.springboot.web;

import net.parttimepolymath.sandbox.springboot.exception.ResourceNotFoundException;
import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import net.parttimepolymath.sandbox.springboot.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Controller that responds to a valid post to "/api/echo"
 *
 * @since 2020-06-26
 * @author Robert Hook
 */
@RestController
@RequestMapping("/api")
@Validated
public class EchoController {
    private final EchoService service;

    public EchoController(@Autowired final EchoService service) {
        this.service = service;
    }

    @Valid
    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public EchoResponse echo(@RequestBody EchoRequest echoRequest) {
        return service.echo(echoRequest);
    }

    @RequestMapping(value = "/echo/{id}", method = RequestMethod.GET)
    public EchoResponse fetch(@PathVariable(value = "id") String id) {
        return service.fetch(UUID.fromString(id)).orElseThrow(() -> new ResourceNotFoundException("Message", "id", id) );
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public List<EchoResponse> fetch() {
        return service.fetch();
    }

}
