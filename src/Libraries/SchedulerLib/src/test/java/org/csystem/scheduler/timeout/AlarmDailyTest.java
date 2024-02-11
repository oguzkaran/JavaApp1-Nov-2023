package org.csystem.scheduler.timeout;

import com.karandev.io.util.console.Console;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Ignore
public class AlarmDailyTest {
    @Test
    public void test()
    {
        var alarm = Alarm.of(LocalTime.now().plusSeconds(4), true);

        alarm.start(() -> Console.writeLine("Alarm!...."));

        Console.readChar();
    }
}
