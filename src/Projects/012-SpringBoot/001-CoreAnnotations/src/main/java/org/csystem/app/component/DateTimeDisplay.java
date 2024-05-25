package org.csystem.app.component;

import com.karandev.io.util.console.Console;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DateTimeDisplay {
    public DateTimeDisplay()
    {
        Console.writeLine("I am a default ctor of DateTimeDisplay");
    }
}
