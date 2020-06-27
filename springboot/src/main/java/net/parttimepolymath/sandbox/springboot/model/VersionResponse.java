package net.parttimepolymath.sandbox.springboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.parttimepolymath.sandbox.springboot.configuration.Version;

/**
 * class used to transfer the version information out of the /version API end point.
 * For some reason we cannot use the Version itself, because Jackson goes down a recursive rabbit hole
 * if we try to simply serialize that object. We also need to make this an object that can be
 * created from JSON in order to use it during our tests.
 *
 * @since 2020-06-27
 * @author Robert Hook
 */
@Data
@NoArgsConstructor
public class VersionResponse {

    private String name;
    private String version;
    private String build;
    private String profile;

    public VersionResponse(final Version vers) {
        name = vers.getName();
        version = vers.getVersion();
        build = vers.getBuild();
        profile = vers.getProfile();
    }
}
