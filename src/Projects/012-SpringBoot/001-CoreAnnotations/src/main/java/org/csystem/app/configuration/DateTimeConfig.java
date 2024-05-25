package org.csystem.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DateTimeConfig {
    @Bean
    public LocalDateTime startTime()
    {
        return LocalDateTime.now();
    }
}
