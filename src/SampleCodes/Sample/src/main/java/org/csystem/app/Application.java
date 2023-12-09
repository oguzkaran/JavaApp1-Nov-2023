/*----------------------------------------------------------------------------------------------------------------------
    Recursion, Recursive algoritmalar ve Recursive Metotlar:
    Bir olgunun kendisine çok benzer bir olguyu içermesi durumuna recursion denir. Örneğin bir dizin (directory) ağacını
    dolaşmak için dizin içerisindeki bir girişin bir dizin belirtmesi duurmunda tekrar o dizin için de aynı işlemlerin
    yapılması gerekir. Yani karşımıza aynı durum çıkmıştır. Bu durumda bu algoritma recursion içermektedir.

    Recursion içeren algoritmalara recursive algoritmalar denir.

    Algoritmik bakımdan recursion içeren problemler 3(üç) gruba ayrılmaktadır:
    1. Hem recursive hem de recursive olmayan şekilde yazılabilen algoritmalar
    2. Yalnızca recursive olarak yazılabilen algoritmalar
    3. Yalnızca recursive olmadan yazılabilen algoritmalar

    Birinci gruptaki algoritmaların hangisinin daha verimli olacağı değişebilmektedir. Programcının buna doğru olarak
    karar vermesi gerekebilir

    Yazılım dünyasında karşılaştığımız recursive algoritmalar tipik olarak şunlardır:
    - Dizin ağacının dolaşılması
    - Ağaçlar (trees) ve graflar (graphs) gibi veri yapılarının dolaşılması ve bu veri yapılarında arama yapılması
    - Bazı sıralama (sort) algoritmaları (quick sort, merge sort, heap sort vb)
    - Parsing algoritmaları
    - Özel bazı problemler (Satrançta 8 vezir problemi vb.)
    - Matematiksel bazı algoritmalar
    - Bazı optimizasyon problemleri
    - ...

     Anımsanacağı gibi, bir metodun parametre değişkenleri ve yerel değişkenleri stack'de yaratılırlar. Ancak sisteme
     bağlı olarak (işlemci, işletim vb.) aşağı seviye metot çağrıldığında başka bilgiler de (örneğin saklanmak üzere)
     stack'e atılabilirler. Bu işlem çok fazla yapıldığında artık stack alanı yetmez duruma gelir (stack overflow).
     Genelde bir uygulama için stack alanı küçük olma eğilimindedir. Tabi bazı programlama ortamları, stack taşması
     gibi durumların yanlışlıkla yapılmasını mümkün olduğunca engeller. Örneğin Java'da Stack'de dizi yaratılamaz. Bu
     durumdar çok fazla sayıda elemanı olan bir diziden dolayı stack overflow olma ihtimali yoktur.

     Bir metot nasıl başka başka bir metodu çağırabiliyorsa kendisini de çağırabilir. Aslında bir metodun kendisini
     çağırması ile başka bir metodu çağırması arasında teknik olarak bir fark yoktur. Recursive algoritmalar kendi kendini
     çağıran metotlar ile gerçekleştirilir:

         public static void foo(int x)
         {
            int a;

            //...

            foo(x);
         }
    Burada foo metodu çağrıoldığında foo'nun x parametre değişkeni ve a yerel değişkeni stack'de yaratılır (push). foo
    metodu sonlandığında akış çağrılan noktadan devam eder. Bu metot sonlandığında x ve a stack'den atılacaktır (pop). Bu
    durumda metot kendi kendi kendisini çağırdığında o çağrı için yeniden parametre değişkeni olan x ve yerel değişken
    a stack'e atılacaktır (push).

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample.foo(3);
    }
}


class Sample {
    public static void foo(int x)
    {
        // Giriş:3
        //Giriş:2
        //Giriş:1
        //Giriş:0
        //Çıkış:1
        //Çıkış:2
        //Çıkış:3

        Console.writeLine("Giriş:%d", x);

        if (x == 0)
            return;

        foo(x - 1);
        Console.writeLine("Çıkış:%d", x);
    }
}
