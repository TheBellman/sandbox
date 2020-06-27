package net.parttimepolymath.sandbox.springboot.web;

import net.parttimepolymath.sandbox.springboot.configuration.Version;
import net.parttimepolymath.sandbox.springboot.model.VersionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that some version information. Note the use of RequestMapping to ensure the version is returned
 * for any of : /api, /api/, /version, /version/
 *
 * @author Robert Hook
 * @since 2020-06-27
 */
@RestController
@RequestMapping("/api")
public class VersionController {

    private final Version version;

    public VersionController(@Autowired Version version) {
        this.version = version;
    }

    @RequestMapping(value = {"/version", ""}, method = RequestMethod.GET)
    public VersionResponse getVersion() {
        return new VersionResponse(version);
    }
}
