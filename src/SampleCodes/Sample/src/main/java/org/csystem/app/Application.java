/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.InputMismatchException;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        while (true) {
            try {
                var count = Console.readUInt("Input count:");
                if (count == 0)
                    break;

                var min = Console.readUInt("Input origin:");
                var bound = Console.readUInt("Input bound:");
                var generator = RandomIntGenerator.of(random, min, bound, count);
                var optInt = generator.findFirst(NumberUtil::isPrime);
                var value = optInt.orElseGet(() -> random.nextInt(-99, 0));

                Console.writeLine("Value:%d", value);
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Invalid values!...");
            }
        }
    }
}
