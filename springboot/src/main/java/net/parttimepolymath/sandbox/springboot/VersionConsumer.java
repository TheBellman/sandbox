package net.parttimepolymath.sandbox.springboot;

import lombok.extern.slf4j.Slf4j;
import net.parttimepolymath.sandbox.springboot.configuration.Version;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Simple object to be managed by spring. This is just to illustrate that the configuration is being
 * wired in during the application startup - when this object is built during startup, it will
 * log the Version that has been setup from the configuration.
 */
@Component
@Slf4j
public class VersionConsumer implements InitializingBean {
    private final Version version;

    public VersionConsumer(@Autowired Version version) {
        this.version = version;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Version details : {}", version);
    }
}
