/*----------------------------------------------------------------------------------------------------------------------
    Nested class'ın ait olduğu sınıf, nested class'ın private bölümüne erişebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        A.foo(10);
    }
}

class A {
    //...
    public static void foo(int a)
    {
        Console.writeLine("A.foo");

        B.foo(a);
    }

    public static class B {
        //...
        private static void foo(int a)
        {
            Console.writeLine("B.foo");
        }
    }
}