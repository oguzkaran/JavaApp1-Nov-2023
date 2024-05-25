package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StartDateTimeDisplay {
    private final LocalDateTime m_startDateTime;
    private final DateTimeFormatter m_dateTimeFormatter;

    public StartDateTimeDisplay(LocalDateTime startDateTime, DateTimeFormatter dateTimeFormatter)
    {
        m_startDateTime = startDateTime;
        m_dateTimeFormatter = dateTimeFormatter;
    }

    @PostConstruct
    public void displayStartTime()
    {
        Console.writeLine("Start Date Time:%s", m_startDateTime.format(m_dateTimeFormatter));
    }
}
