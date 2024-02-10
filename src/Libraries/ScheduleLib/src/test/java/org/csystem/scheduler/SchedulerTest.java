package org.csystem.scheduler;

import com.karandev.io.util.console.Console;
import org.csystem.scheduler.timeout.Alarm;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Ignore
public class SchedulerTest {
    @Test
    public void test()
    {
        var scheduler = Scheduler.of(1000);
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        scheduler.schedule(() -> Console.write("%s\r", formatter.format(LocalDateTime.now())));

        Console.readChar();
    }
}
