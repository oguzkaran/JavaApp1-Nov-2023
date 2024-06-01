package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.karandev.datetime.configuration.constant.BeanName.LOCAL_CURRENT_DATETIME_BEAN;

@Component
@Scope("prototype")
public class DateTimeDisplay {
    private final ApplicationContext m_applicationContext;
    private final DateTimeFormatter m_dateTimeFormatter;

    //ctor injection
    public DateTimeDisplay(ApplicationContext applicationContext, DateTimeFormatter dateTimeFormatter)
    {
        m_applicationContext = applicationContext;
        m_dateTimeFormatter = dateTimeFormatter;
    }

    @PostConstruct
    public void displayDateTime()
    {
        var now = m_applicationContext.getBean(LOCAL_CURRENT_DATETIME_BEAN, LocalDateTime.class);

        Console.writeLine("Current Date Time:%s", now.format(m_dateTimeFormatter));
    }
}
