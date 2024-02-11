package org.csystem.scheduler.timeout;

import com.karandev.io.util.console.Console;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;

@Ignore
public class AlarmTest {
    private void alarmCallback()
    {
        Console.writeLine("Alarm!....");
    }

    @Test
    public void test()
    {
        var alarm = Alarm.of(LocalDateTime.now().plusSeconds(10));

        alarm.start(this::alarmCallback);

        Console.readChar();
    }
}
