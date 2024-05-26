package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.karandev.datetime.configuration.constant.BeanName.CUSTOM_DATETIME_FORMATTER_BEAN;
import static com.karandev.datetime.configuration.constant.BeanName.LOCAL_START_DATETIME_BEAN;

@Component
@ComponentScan("com.karandev")
public class StartDateTimeDisplay {
    private final LocalDateTime m_startDateTime;
    private final DateTimeFormatter m_dateTimeFormatter;

    @Value("${app.datetime.message.start}")
    private String m_message;

    public StartDateTimeDisplay(@Qualifier(LOCAL_START_DATETIME_BEAN) LocalDateTime startDateTime,
                                @Qualifier(CUSTOM_DATETIME_FORMATTER_BEAN) DateTimeFormatter dateTimeFormatter)
    {
        m_startDateTime = startDateTime;
        m_dateTimeFormatter = dateTimeFormatter;
    }

    @PostConstruct
    public void displayStartTime()
    {
        Console.writeLine("%s:%s", m_message, m_startDateTime.format(m_dateTimeFormatter));
    }
}
