/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.datasource.generator.random.ObjectArrayGenerator;

import java.util.Arrays;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(1, args.length, "wrong number of arguments!...");

        try {
            var count = Integer.parseInt(args[0]);
            var generator = new ObjectArrayGenerator();
            var objects = Arrays.stream(generator.createObjects(count))
                    .peek(Console::writeLine)
                    .filter(o -> !(o instanceof Double))
                    .toArray();

            Console.writeLine("###################################################");
            ArrayUtil.forEach(objects, Console::writeLine);

        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}

interface MyCollector<T, A, R> {
    //...
}

class MyStream<T> {
    public <R, A> R collect(MyCollector<? super T, A, R> collector)
    {
        throw new UnsupportedOperationException();
    }
}