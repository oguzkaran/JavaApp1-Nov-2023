/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: org-csystem-util kütüphanesinin 12.0.0 sürümüne aşağıdaki factorial metodunu ve birim testi
    kodlarını yazınız:
        public static BigInteger factorialBigInteger(int a);
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigDecimal;

class Application {
    public static void run(String[] args)
    {
        var bound = new BigDecimal(2 * Math.PI);
        var inc = BigDecimal.valueOf(0.001);

        for (var i = new BigDecimal(-2 * Math.PI); i.compareTo(bound) < 0; i = i.add(inc))
            Console.writeLine(i);
    }
}

