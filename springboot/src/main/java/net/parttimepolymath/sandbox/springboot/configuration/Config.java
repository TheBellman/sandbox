package net.parttimepolymath.sandbox.springboot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * holder for general beans we want to pop into existence.
 *
 * @since 2020-06-27
 * @author Robert Hook
 */
@Configuration
public class Config {
    /**
     * get an instance of the Jackson ObjectMapper.
     *
     * @return a non-null Object Mapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return ObjectMapperFactory.getObjectMapper();
    }
}
