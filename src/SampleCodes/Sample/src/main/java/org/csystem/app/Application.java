/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.scheduler.CountDownSchedulerEx;

import java.util.concurrent.TimeUnit;

class Application {
    public static void run(String[] args)
    {
        new CountDownSchedulerEx(10, 1, TimeUnit.SECONDS) {
            public void onStart()
            {
                Console.writeLine("Count down started!...");
            }

            public void onTick(long remainingMilliseconds)
            {
                Console.write("%s\r", (remainingMilliseconds + 1000) / 1000);
            }

            public void onFinish()
            {
                Console.write(0);
                Console.writeLine("\nFinished");
            }
        }.startScheduler();
    }
}

