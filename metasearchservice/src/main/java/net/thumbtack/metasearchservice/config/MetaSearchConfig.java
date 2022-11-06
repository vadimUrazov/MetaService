package net.thumbtack.metasearchservice.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetaSearchConfig {
    @Bean
    protected ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }


}
