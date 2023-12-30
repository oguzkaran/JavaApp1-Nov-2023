/*----------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input a number:");

            Console.writeLine("a = %d", a);
            Console.writeLine("a = 0b%s", Long.toBinaryString(a));

            if (a == 0)
                break;
        }
    }
}

