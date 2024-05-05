/*----------------------------------------------------------------------------------------------------------------------
    Stream arayüzlerinin findFirst metotları ilgili Stream'in ilk elemanına geri döner. Stream boş olabileceği için
    bu metotların geri dönüş değerleriilgili optional sınıfı türündendir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

class Application {
    public static void run(String[] args)
    {
        IntStream.range(0, 26).forEach(i -> Console.write((char)('A' + i)));
        IntStream.range(0, 26).forEach(i -> Console.write((char)('a' + i)));
    }
}
