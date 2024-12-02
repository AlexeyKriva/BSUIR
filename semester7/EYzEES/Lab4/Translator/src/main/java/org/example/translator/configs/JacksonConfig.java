package org.example.translator.configs;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        JsonFactory factory = new JsonFactory();
        StreamWriteConstraints constraints = StreamWriteConstraints.builder().maxNestingDepth(200000).build();
        factory.setStreamWriteConstraints(constraints);

        return new ObjectMapper(factory);
    }
}
