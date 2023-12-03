/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte aynı sayılar için isPrime metotlarının performanslarını gözlemleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.apache.commons.lang3.time.StopWatch;
import org.csystem.util.numeric.NumberUtil;

import java.math.BigInteger;

class Application {
    public static void run(String[] args)
    {
        var stopWatch = new StopWatch();

        stopWatch.start();
        Console.writeLine(NumberUtil.isPrime(710_584_055_392_819_667L));
        stopWatch.stop();

        Console.writeLine("%f seconds", stopWatch.getNanoTime() / 1_000_000_000.);

        stopWatch.reset();
        stopWatch.start();
        Console.writeLine(NumberUtil.isPrime(BigInteger.valueOf(710_584_055_392_819_667L)));
        stopWatch.stop();

        Console.writeLine("%f seconds", stopWatch.getNanoTime() / 1_000_000_000.);
    }
}

