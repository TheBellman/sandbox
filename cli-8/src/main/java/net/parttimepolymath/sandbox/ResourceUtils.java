package net.parttimepolymath.sandbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for dealing with resource files from the JAR or from code base during tests.
 *
 * @author Robert Hook
 * @since 2020-16-05
 */
@Slf4j
public final class ResourceUtils {
    /**
     * load a set of Properties from a nominated file in the JAR or code base.
     *
     * @param fileName the name of the file to load.
     * @return a non-null set of properties. This may be empty if the file cannot be loaded.
     */
    public static Properties loadProperties(final String fileName) {
        Properties prop = new Properties();

        getResourceAsStream(fileName).ifPresent(inStream -> {
            try {
                prop.load(inStream);
            } catch (IOException e) {
                log.warn("Could not load properties from '{}'", fileName);
            }
        });
        return prop;
    }

    /**
     * load a file from the JAR or code base as a list of strings (if possible).
     * @param fileName the name of the file to load
     * @return a non-null but possibly empty list of strings
     */
    public static List<String> readResourceFileAsList(final String fileName) {
        return getResourceAsStream(fileName)
                .map(inputStream -> new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    /**
     * create an input stream from a file in the JAR or code base.
     *
     * @param fileName the name of the file to treat as an input stream. This will be prefixed with "/" if it is not already.
     * @return a non-null Optional
     */
    public static Optional<InputStream> getResourceAsStream(final String fileName) {
        if (fileName == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(ResourceUtils.class.getResourceAsStream(
                StringUtils.prependIfMissing(fileName, "/"))
        );
    }
}
