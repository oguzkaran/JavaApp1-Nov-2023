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

    
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        Sample.foo();
    }
}


class Sample {
    public static void foo()
    {
        foo();
    }
}
