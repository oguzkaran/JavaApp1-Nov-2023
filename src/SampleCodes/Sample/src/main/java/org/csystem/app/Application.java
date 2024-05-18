/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte klavyeden sıfır girilene kadar alınan int türden sayılara ilişkin çeşitli istatistiksel bilgiler elde
    edilmiştir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

class Application {
    public static void run(String[] args)
    {
        var result =  IntStream.generate(() -> Console.readInt("Bir sayı giriniz:"))
                .takeWhile(v -> v != 0)
                .summaryStatistics();

        if (result.getCount() != 0)
            Console.writeLine("Rapor:%nToplam:%d%nOrtalama:%f%nGirilen sayı miktarı:%d%nEn büyük sayı:%d%nEn küçük sayı:%d",
                    result.getSum(), result.getAverage(), result.getCount(), result.getMax(), result.getMax());
        else
            Console.writeLine("Hiç sayı girmediniz!...");
    }
}
