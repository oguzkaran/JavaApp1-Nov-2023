/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte bir stack'in elemanları dolaşılmıştır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var stack = new Stack<Integer>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            Console.write("%d ", val);
            stack.push(val);
        }

        Console.writeLine();

        try {
            while (true)
                Console.write("%d ", stack.pop());
        }
        catch (EmptyStackException ignore) {
            Console.writeLine();
        }
    }
}
