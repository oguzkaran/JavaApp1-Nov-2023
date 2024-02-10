/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte doWork metotlarına geçilen LE'lerin parametre değişkenlerine tür bilgisi yazılmazsa
    ambiguity oluşur
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.scheduler.Scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

class Application {
    public static void run(String[] args)
    {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Scheduler.of(2, 1, TimeUnit.SECONDS).schedule(() -> Console.write("%s\r", formatter.format(LocalDateTime.now())));
    }
}
