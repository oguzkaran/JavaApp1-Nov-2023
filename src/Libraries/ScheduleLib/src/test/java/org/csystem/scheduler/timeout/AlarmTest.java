package org.csystem.scheduler.timeout;

import com.karandev.io.util.console.Console;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.TimerTask;

@Ignore
public class AlarmTest {
    @Test
    public void test()
    {
        var alarm = Alarm.of(LocalDateTime.now().plusMinutes(1));

        alarm.start(new TimerTask() {

            public void run()
            {
                Console.writeLine("Alarm!....");
            }
        });

        Console.readChar();
    }
}
