/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.scheduler.CountDownTimer;

import java.util.concurrent.TimeUnit;

class Application {
    public static void run(String[] args)
    {
        new CountDownTimer(10, 1, TimeUnit.SECONDS) {
            public void onTick(long remainingMilliseconds)
            {
                Console.write("%s\r", remainingMilliseconds / 1000);
            }

            public void onFinish()
            {
                Console.writeLine("\nFinished");
            }
        }.start();
    }
}

