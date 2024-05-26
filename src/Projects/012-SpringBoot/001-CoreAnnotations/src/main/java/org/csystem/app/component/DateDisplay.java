package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateDisplay {
    private final LocalDate m_localDate;

    @Value("${app.datetime.message.current.date:Date}")
    private String m_message;

    public DateDisplay(LocalDate localDate)
    {
        m_localDate = localDate;
    }

    @PostConstruct
    public void displayLocalDate()
    {
        Console.writeLine("%s:%s", m_message, m_localDate.format(DateTimeFormatter.ISO_DATE));
    }
}
