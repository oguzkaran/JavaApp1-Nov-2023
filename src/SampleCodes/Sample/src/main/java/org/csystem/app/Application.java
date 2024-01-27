/*----------------------------------------------------------------------------------------------------------------------
    Anonim Sınıflar: Programcının bildirimde isim vermediği sınıflardır. Anomim sınıf bildiriminin genel biçimi
    aşağıdaki gibidir:
        new <UDT ismi>([parametreler]) {
            //...
        };
    Örneğin:
        new Sample() {
            //...
        }
    Burada Sample sınıfından türetilmiş olan bir sınıf bildirilmiştir ve o sınıf türünden nesne yaratılmıştır. Bu durumda
    her anonim sınıf bildirimi ayrı bir sınıf bildirimi anlamındadır. Derleyici yine anonim sınıflar için byte code'u
    üretirken $ karakteri kullanarak isimlendirme yapar. Aşağıdaki demo örneği çalılştırıp dinamik türleri gözlemleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample s1 = new Sample() {
            //...
        };

        Sample s2 = new Sample() {
            //...
        };

        Console.writeLine(s1.getClass().getName());
        Console.writeLine(s2.getClass().getName());
    }
}

class Sample {
    //...
}