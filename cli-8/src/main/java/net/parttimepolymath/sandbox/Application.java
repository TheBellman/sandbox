package net.parttimepolymath.sandbox;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.NotThreadSafe;
import net.parttimepolymath.sandbox.picker.StringPairListFilePicker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@Slf4j
@NotThreadSafe
public class Application implements Runnable {

    @Builder.Default
    @Getter
    private ResultHolder result = ResultHolder.builder().startTime(Instant.now()).build();

    private int iterations;
    private boolean debugMode = false;
    private String namesFile;

    @Override
    public void run() {
        log.info("Start executing {} iterations", iterations);

        StringPairListFilePicker namesPicker = new StringPairListFilePicker(namesFile, " ");

        log.info("loaded {} random names", namesPicker.size());

        // pick a random name from the list for each iteration, and count the ones that match "Cruz"
        result.setCount(
                IntStream.range(0, iterations)
                .mapToObj(i -> namesPicker.pickPair())
                .filter(pair -> pair.getRight().equals("Cruz"))
                .count()
        );
        log.info("the surname name 'Cruz' was found {} times", result.getCount());

        // build an instant range and traverse it.
        InstantRange range = new InstantRange(
                Instant.parse("2020-03-08T00:00:00Z"),
                Instant.parse("2020-05-16T00:00:00Z"),
                ChronoUnit.DAYS);
        log.info("Instant range: {}",  range.asStream()
            .map(Instant::toString).collect( Collectors.joining(", " ))
        );

        log.info("Finish executing {} iterations", iterations);
    }
}
