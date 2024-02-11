package org.csystem.scheduler;

import com.karandev.io.util.console.Console;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Ignore
public class SchedulerTimeoutTest {
    @Test
    public void test()
    {
        var scheduler = Scheduler.of();

        scheduler.schedule(() -> Console.writeLine("Timeout"), LocalDateTime.now().plusSeconds(3));

        Console.readChar();
    }
}
