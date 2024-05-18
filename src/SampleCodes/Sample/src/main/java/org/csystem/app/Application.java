/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.collection.CollectionUtil;
import org.csystem.util.datasource.factory.NameFactory;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .toList();

            names.forEach(Console::writeLine);
            names = CollectionUtil.toModifiableList(names);
            names.add("ali");
            names.forEach(Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getClass().getName());
        }
    }
}

