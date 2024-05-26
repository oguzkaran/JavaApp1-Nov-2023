package com.karandev.datetime.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

import static com.karandev.datetime.configuration.constant.BeanName.*;

@Configuration
public class DateTimeFormatterConfig {
    @Bean(DATETIME_FORMATTER_TR_BEAN)
    @Primary
    public DateTimeFormatter createDateTimeFormatter()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH.mm.ss");
    }

    @Bean(TIME_FORMATTER_TR_BEAN)
    public DateTimeFormatter createTimeFormatter()
    {
        return DateTimeFormatter.ofPattern("HH.mm.ss");
    }

    @Bean(DATE_FORMATTER_TR_BEAN)
    public DateTimeFormatter createDateFormatter()
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Bean(CUSTOM_DATETIME_FORMATTER_BEAN)
    public DateTimeFormatter createCustomDateTimeFormatter(
            @Value("${com.karandev.datetime.format.datetime.custom:dd/MM/yyyy HH:mm:ss}") String pattern)
    {
        return DateTimeFormatter.ofPattern(pattern);
    }

    @Bean(CUSTOM_DATE_FORMATTER_BEAN)
    public DateTimeFormatter createCustomDateFormatter(
            @Value("${com.karandev.datetime.format.date.custom:dd/MM/yyyy}") String pattern)
    {
        return DateTimeFormatter.ofPattern(pattern);
    }

    @Bean(CUSTOM_TIME_FORMATTER_BEAN)
    public DateTimeFormatter createCustomTimeFormatter(
            @Value("${com.karandev.datetime.format.time.custom:HH:mm:ss}") String pattern)
    {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
