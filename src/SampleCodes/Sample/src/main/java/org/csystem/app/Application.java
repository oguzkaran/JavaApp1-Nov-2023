/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte işe giriş tarihi, verilen minDate ve maxDate arasında kalan çalışanların elde edilmiştir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            var count = Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getEntryDate().isAfter(minDate))
                    .filter(s -> s.getEntryDate().isBefore(maxDate))
                    .count();

            Console.writeLine("Count:%d", count);
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
