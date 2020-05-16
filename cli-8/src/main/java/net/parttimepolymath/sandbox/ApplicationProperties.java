package net.parttimepolymath.sandbox;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Properties;

/**
 * convenience wrapper around the application properties with getter semantics.
 *
 * @author Robert Hook
 * @since 2020-05-16
 */
public final class ApplicationProperties {
    private static final Properties PROPERTIES = ResourceUtils.loadProperties("application.properties");

    /**
     * get the application name from application.name
     *
     * @return a non-null but possibly empty string.
     */
    public static String getAppName() {
        return PROPERTIES.getProperty("application.name", "");
    }

    /**
     * get the default iterations from default.iterations
     *
     * @return a positive integer
     */
    public static int getIterations() {
        return NumberUtils.toInt(PROPERTIES.getProperty("default.iterations"), 200);
    }
}
