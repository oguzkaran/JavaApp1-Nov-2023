/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.LinkedList;

class Application {
    public static void run(String[] args)
    {
        var arrayList = new ArrayList<>();
        var linkedList = new LinkedList<>();

        var stopWatch = new StopWatch();
        stopWatch.start();
        for (var i = 0; i < 1_000_000; ++i)
            arrayList.add(i * 10);

        stopWatch.stop();

        Console.writeLine("Time of add operation in nanoseconds of ArrayList<E> is %d", stopWatch.getNanoTime());

        stopWatch = new StopWatch();
        stopWatch.start();
        for (var i = 0; i < 1_000_000; ++i)
            linkedList.add(i * 10);

        stopWatch.stop();

        Console.writeLine("Time of add operation in nanoseconds of LinkedList<E> is %d", stopWatch.getNanoTime());

        stopWatch = new StopWatch();
        stopWatch.start();
        arrayList.get(900_000);
        stopWatch.stop();

        Console.writeLine("Time of get operation in nanoseconds of ArrayList<E> is %d", stopWatch.getNanoTime());

        stopWatch = new StopWatch();
        stopWatch.start();
        linkedList.get(900_000);
        stopWatch.stop();

        Console.writeLine("Time of get operation in nanoseconds of LinkedList<E> is %d", stopWatch.getNanoTime());
    }
}