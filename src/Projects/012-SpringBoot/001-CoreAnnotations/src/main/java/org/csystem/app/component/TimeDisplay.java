package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@Lazy
public class TimeDisplay {
    public TimeDisplay()
    {
        Console.writeLine("I am a default ctor of TimeDisplay");
    }

    public void displayLocalTime()
    {
        var now = LocalTime.now();

        Console.writeLine("TimeDisplay:%s", now.format(DateTimeFormatter.ISO_TIME));
    }
}
