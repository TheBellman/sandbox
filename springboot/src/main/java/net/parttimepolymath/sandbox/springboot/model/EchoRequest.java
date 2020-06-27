package net.parttimepolymath.sandbox.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jcip.annotations.NotThreadSafe;

/**
 * very simple POJO to represent an incoming request to our service.
 *
 * @since 2020-06-27
 * @author Robert Hook
 */

@Data
@NoArgsConstructor
@NotThreadSafe
public class EchoRequest {

    /**
     * the message we want echoed.
     */
    @JsonProperty
    private String message;
}
