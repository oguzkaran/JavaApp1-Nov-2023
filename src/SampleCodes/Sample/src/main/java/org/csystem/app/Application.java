/*----------------------------------------------------------------------------------------------------------------------
    Collection<T> arayüzü: Bu arayüz Iterable<T> arayüzünden türetilmiştir. 
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);
            var iter = generator.iterator();

            if (iter.hasNext()) iter.next();
            if (iter.hasNext()) iter.next();

            iter.forEachRemaining(val -> Console.write("%d ", val));
            Console.writeLine();
        }
    }
}
