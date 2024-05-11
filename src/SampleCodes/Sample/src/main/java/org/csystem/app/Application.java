/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(3, args.length, "wrong number of arguments!...");

        try {
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var a = Integer.parseInt(args[1]);
            var b = Integer.parseInt(args[2]) + 1;

            var numbers = IntStream.generate(() -> random.nextInt(a, b))
                    .distinct()
                    .limit(count)
                    .sorted()
                    .peek(v -> Console.write("%d ", v))
                    .toArray();

            Console.writeLine();

            Arrays.stream(numbers).forEach(v -> Console.write("%d ", v));
            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}