/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte stokta bulunmayan ilk ürünün bilgileri listelenmiştir. Tüm ürünler stokta ise uygun mesaj
    verilmiştir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        products.stream()
                .filter(p -> p.getStock() <= 0)
                .findFirst()
                .ifPresentOrElse(Console::writeLine, () -> Console.writeLine("All products are in stock!..."));
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
