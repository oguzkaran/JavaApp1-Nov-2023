/*----------------------------------------------------------------------------------------------------------------------
    for-each döngü deyimi aslında yapay bir döngü deyimidir. Programcı for-each döngü deyimini yazdığında derleyici
    tarafından bir kod üretilir. Bir sınıfın for-each döngü deyimi ile dolaşılabilmesi için (iterable) yine Java 5 ile
    eklenen Iterable<T> arayüzünü desteklemesi gerekir. Aksi durumda error oluşur.

    Anahtar Notlar: Bir veri yapısına ait elemanların, veri  yapısı içerisinde nasıl tutulduktan bağımsız olarak
    dolaşılabilmesi için kullanılan tasarım kalıbına (degin pattern) "iterator" denir. Yani aslında veriler bir iterator
    yardımıyla tutulur. Iterator ile her adımda mantıksal bir sonraki veriye erişilir. Böylelikle programcı verilerin
    nasıl tutulduğundan bağımsız dolaşılan kodlar yazabilir.

    Anahtar Notlar: Diziler sentaks olarak for-each döngü deyimi ile kullanılabilir. Bu anlamda diziler Iterable<T>
    arayüzünü destekleyen türler değildir.

    Iterable arayüzünün iterator isimli bir tane abstract metodu vardır. Bu metot Iterator<T> arayüz referansına geri
    döner. Bu arayüzün Java 8'den önce 3 tane, Java 8 ile birlikte 2 tane abstract metodu bulunmaktadır. Java 8'den önce
    remove isimli metot da abstract bir metottu, Java 8 ile birlikte default metot kavramı eklendiği için bu metot
    UnsupportedOperationException fırlatacak şekilde default metot yapılmıştır. Duruma göre programcı override edebilir.
    Java 8 ile eklenen ve yine default olan forEachRemaining metodu ileride ele alınacaktır. Java Language Specification'a
    göre bir türe ilişkin referans ile (dizi türü hariç) for-each döngü deyimi kullanıldığında aşağıdaki metotların
    çağrılacağı şekilde yaklaşık bir kod üretilir. Demo örnekteki for-each döngü deyimi için:

        Iterator<String> iter = texts.iterator();
        String s;

        while (iter.hasNext()) {
            s = iter.next();
            Console.writeLine(s);
        }

    Şüphesiz bu döngü çeşitli şekillerde de yazılabilir. Yine şüphesiz programcının böyle bir döngü için for-each
    kullanması gerekir ancak for-each'in bu yapısını da bilmesi gerekir.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var texts = new ArrayList<String>();
        var random = new Random();
        int n = random.nextInt(Console.readInt("Input min value:"), Console.readInt("Input bound value:"));

        while (n-- > 0)
            texts.add(StringUtil.generateRandomTextEN(random, random.nextInt(5, 11)));

        for (var s : texts)
            Console.writeLine(s);

        Console.writeLine("--------------------------------");

        Iterator<String> iter = texts.iterator();
        String s;

        while (iter.hasNext()) {
            s = iter.next();
            Console.writeLine(s);
        }
    }
}

