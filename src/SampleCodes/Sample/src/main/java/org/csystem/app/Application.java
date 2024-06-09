/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte, int -> int.. dönüşümü int -> long... dönüşümünden daha kaliteli olduğundan long parametreli foo
    çağrılır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample.foo(10);
    }
}

class Sample {
    public static void foo(long...a)
    {
        Console.writeLine("foo, long...");
    }

    public static void foo(int...a)
    {
        Console.writeLine("foo, int...");
    }
}