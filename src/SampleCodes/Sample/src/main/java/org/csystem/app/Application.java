/*----------------------------------------------------------------------------------------------------------------------
    Soru: Bir sayının ikinin kuvveti olup olmadığı bitsel operatörler kullanarak nasıl anlaşılır?
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {

        while (true) {
            var val = Console.readInt("Input a number:");

            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            Console.writeLine(BitwiseUtil.toBinaryStr(val & 1));
            Console.writeLine((val & 1) == 1 ? "Odd" : "Even");

            if (val == 0)
                break;
        }
    }
}

