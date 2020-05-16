package net.parttimepolymath.sandbox;

import lombok.*;
import net.jcip.annotations.NotThreadSafe;

import java.time.Instant;

/**
 * this class is just to illustrate the use of lombok, and the use of jackson.
 * It's a simple POJO with lombok annotations setup to allow a variety of data-like semantics.
 * Lombok offers a lot more features than below - we are not using the equals/hash generator for instance.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NotThreadSafe
public class ResultHolder {
    private long count;
    private Instant startTime;
}
