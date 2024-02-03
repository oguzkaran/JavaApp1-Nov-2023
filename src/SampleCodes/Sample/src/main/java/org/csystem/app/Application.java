/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.scheduler.timeout.Alarm;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimerTask;

class Application {
    public static void run(String[] args)
    {
        var alarm = Alarm.of(LocalDateTime.now().plusSeconds(10));

        alarm.start(new TimerTask() {
            public void run()
            {
                Console.writeLine("Wake up!...");
            }
        });
    }
}

