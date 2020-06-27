package net.parttimepolymath.sandbox.springboot.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

/**
 * simple POJO to contain some properties injected from the maven build.
 *
 * This illustrates the semantics of wiring properties to a configuration object. Note that by making this a
 * simple pojo and not using the @Value annotation, the spring-boot-configuration-processor (see the pom.xml)
 * will create META-INF/spring-configuration-metadata.json. This then becomes visible to modern IDEs, allowing
 * context sensitive help in the properties files. We are also using validation constraints on the fields.
 * Note also the use of Lombok to remove a lot of boiler plate code.
 *
 * Please see https://www.baeldung.com/configuration-properties-in-spring-boot for a good introduction.
 *
 * @since 2020-06-27
 * @author Robert Hook
 */

@Data
@Configuration
@ConfigurationProperties(prefix="app.version")
public class Version {
    public static final String UNKNOWN="unknown";

    /**
     * The name of the application.
     */
    @NotBlank
    private String name=UNKNOWN;

    /**
     * The version of the application.
     */
    @NotBlank
    private String version=UNKNOWN;

    /**
     * The date and time of the artifact build.
     */
    @NotBlank
    private String build=UNKNOWN;

    /**
     * the active spring profile. which should align to the maven profile.
     */
    @NotBlank
    private String profile=UNKNOWN;
}
