package net.parttimepolymath.sandbox.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * top level application starter class.
 * @since 2020-06-26
 * @author Robert Hook
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // use this if we don't need to customise the startup
        // SpringApplication.run(App.class, args);

        SpringApplication app = new SpringApplication(App.class);
        // application behaviour can be configured here before launching it.
        app.run(args);
    }
}
