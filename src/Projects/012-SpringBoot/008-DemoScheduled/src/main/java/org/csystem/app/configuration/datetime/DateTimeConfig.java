package org.csystem.app.configuration.datetime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig {
    @Bean
    public DateTimeFormatter createFormatter()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    @Bean
    @Scope("prototype")
    public LocalDateTime createLocalDateTime()
    {
        return LocalDateTime.now();
    }
}
