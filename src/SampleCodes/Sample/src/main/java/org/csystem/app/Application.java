/*----------------------------------------------------------------------------------------------------------------------
    Yakalanan bir yerel değişken veya parametre değişkeninin değeri faaliyet alanı (scope) boyunca değiştirilemez. Bu
    anlamda değişken "effectively final" olarak ele alınır. Effectively final kavramı Java 8 ile dile eklenmiştir. Bu
    anlamda Java 8'den önce yakalanan bir değişkenin final olarak bildirilmesi zorunluluğu vardır.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample.foo(Console.readInt("Bir sayı giriniz:"));
    }
}
class Sample {
    public static void foo(int val)
    {
        class Util {
            public boolean isEven()
            {
                return val++ % 2 == 0; //error
            }
        }

        var util = new Util();

        Console.writeLine(util.isEven() ? "Çift" : "Tek");
    }
}
