/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.Random;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(args.length, 3, "wrong number of arguments");

        try {
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);
            var random = new Random();

            for (var i = 0; i < count; ++i)
                Console.writeLine(StringUtil.generateRandomTextEN(random, random.nextInt(min, bound)));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments!...");
        }
    }
}
