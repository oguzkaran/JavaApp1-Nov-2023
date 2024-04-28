/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte satış fiyatı, verilen minPrice ve maxPrice arasında kalan ürünler listelenmiştir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory, BigDecimal minPrice, BigDecimal maxPrice)
    {
        factory.PRODUCTS.stream()
                .filter(p -> p.getPrice().compareTo(minPrice) >= 0)
                .filter(p -> p.getPrice().compareTo(maxPrice) <= 0)
                .forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var minPrice = new BigDecimal(args[1]);
            var maxPrice = new BigDecimal(args[2]);

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(pf -> dataExistsCallback(pf, minPrice, maxPrice), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
