/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo Örnekte TreeSet<T> içerisindeki elemanlar azalan sırada tutulmaktadır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.random.lottery.NumericLottery;

import java.util.Random;

import static org.csystem.util.array.ArrayUtil.print;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var lottery = new NumericLottery(random);

        while (true) {
            var n = Console.readInt("Kaç tane kupon oynamak istersiniz:");

            if (n <= 0)
                break;

            print(2, lottery.getNumbers(n));
        }
    }
}