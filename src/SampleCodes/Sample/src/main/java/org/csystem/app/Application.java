/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            IntStream.rangeClosed(Integer.parseInt(args[0]), Integer.parseInt(args[1]))
                    .forEach(i -> Console.write("%d ", i));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
