/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyimiz
    ~/src/Libraries/CSDLibraries/MathLib kütüphanesini inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Collections;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 10);

        fractions.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var minFraction = Collections.min(fractions);
            var maxFraction = Collections.max(fractions);

            Console.writeLine("Minimum:%s%nMaximum:%s", minFraction, maxFraction);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}