/*----------------------------------------------------------------------------------------------------------------------
    Genel olarak bir kütüphane maven içerisinden aşağıdaki durumlardan biri ya da birkaçından kullanılabilir:
    1. Uygulama geliştirilirken kullanılan host makinenin içerisindeki maven local repository'den kullanılabilir.
    Bu repository maven programı geçerli veya geçersiz ilk kez çalıştırıldığında genel olarak .m2 isimli bir
    directory biçiminde yartılır. Bu directory'nin aslında default ismi ve default yeri değiştirilebilir. Ancak pratikte
    özel durumlar dışında ismi ve yeri değiştirilmez. mvn programı install seçeneği ile doğrudan çalıştırıldığında
    konfigürasyona göre ilgili kütüphane maven local repository'ye yüklenir.

    2. Apache firmasının sunduğu Maven Cnetral denilen bir server'dan kullanılabilir. Bu durumda ilgili kütüphanenin
    bu server içerisinde install edilmesi gerekir. Maven central kullanımı ileride ele alınacaktır.

    3. Özel olarak configure edilmiş ve kütüphanelerin uygun şekilde mvn tarafından install edildiği remote repository'lerden
    kullanılabilir. Bu tarz bir repository oluşturmanın Java ve maven dışında da ayrıntıları vardır. Burada bu tarz
    bir server'ı oluştrma ele alınmayacaktır. Github maven remote repsository oluşturmayı destekler. Burada github üzerinde
    remote repository oluşturmayı ele alacağız ve kurs boyunca genel kütüphanelerimizi ... repository'leri içerisine
    atıp oradan kullanacağız. Remote repository'ler <repositories> elemanı altında pom dosyasında belirtilir

    4. Projenin içerisinden ancak başka bir path'den kullanılabilir. Genelde bu kullanımda kütüphane, proje içerisinde
    bir directory'ye konuşlandırılır ve dependency olarak <systemPath> içerisinde belirtilerek projeye dahil edilir. Bu
    kullanımda kütüphanenin update edilmesi gibi durumlarını yönetmek zor olabilmektedir. Bu sebeple, bu kullanımda
    mvn uyarı vermektedir.

    Maven bir kütüphanenin dependency'sini gördüğünde eğer 4. kullanım durumu yoksa aramayı belli bir sırada yapar.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Console.writeLine("Hello!...");
    }
}
