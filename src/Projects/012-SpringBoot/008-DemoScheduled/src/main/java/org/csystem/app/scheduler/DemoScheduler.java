package org.csystem.app.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
@Slf4j
public class DemoScheduler {
    private final ApplicationContext m_applicationContext;
    private final DateTimeFormatter m_dateTimeFormatter;

    public DemoScheduler(ApplicationContext applicationContext, DateTimeFormatter dateTimeFormatter)
    {
        m_applicationContext = applicationContext;
        m_dateTimeFormatter = dateTimeFormatter;
    }

    //@Scheduled(initialDelay = 3, fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    //@Scheduled(cron = "1-10 42,43 14 * * SUN#2")
    @Scheduled(cron = "@monthly") //0 0 0 1 * *
    public void schedule()
    {
        System.out.printf("Now:%s%n", m_applicationContext.getBean(LocalDateTime.class).format(m_dateTimeFormatter));
    }
}
