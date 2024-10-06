### C ve Sistem Programcıları Derneği
### Java ile Uygulama Geliştirme 1
### Eğitmen: Oğuz KARAN
#### Düzenleyen: Bartu Çankaya
##### Maven ile Kütüphane Kullanımı

>*Genel olarak bir kütüphane maven içerisinden aşağıdaki durumlardan biri ya da birkaçından kullanılabilir:*
>1. Uygulama geliştirilirken kullanılan host makinenin içerisindeki maven local repository'den kullanılabilir. Bu repository maven programı geçerli veya geçersiz ilk kez çalıştırıldığında genel olarak `.m2` isimli bir directory biçiminde yartılır. Bu directory'nin aslında default ismi ve default yeri değiştirilebilir. Ancak pratikte özel durumlar dışında ismi ve yeri değiştirilmez. mvn programı install seçeneği ile doğrudan çalıştırıldığında konfigürasyona göre ilgili kütüphane maven local repository'ye yüklenir.
>2. Projenin içerisinden ancak başka bir path'den kullanılabilir. Genelde bu kullanımda kütüphane, proje içerisinde bir directory'ye konuşlandırılır ve dependency olarak `<systemPath>` içerisinde belirtilerek projeye dahil edilir. Bu kullanımda kütüphanenin update edilmesi gibi durumlarını yönetmek zor olabilmektedir. Bu sebeple, bu kullanımda mvn uyarı vermektedir.
>3. Özel olarak configure edilmiş ve kütüphanelerin uygun şekilde mvn tarafından install edildiği remote repository'lerden kullanılabilir. Bu tarz bir repository oluşturmanın Java ve maven dışında da ayrıntıları vardır. Burada bu tarz bir server'ı oluştrma ele alınmayacaktır. Github maven remote repository oluşturmayı destekler. Burada github üzerinde remote repository oluşturmayı ele alacağız ve kurs boyunca genel kütüphanelerimizi ... repository'leri içerisine atıp oradan kullanacağız. Remote repository'ler `<repositories>` elemanı altında pom dosyasında belirtilir
>4. Apache firmasının sunduğu Maven Central denilen bir server'dan kullanılabilir. Bu durumda ilgili kütüphanenin bu server içerisinde install edilmesi gerekir. Maven central kullanımı ileride ele alınacaktır.
>
>*Maven bir kütüphanenin dependency'sini gördüğünde eğer 2. kullanım durumu yoksa aramayı belli bir sırada yapar. Bu sıra şu şekildedir:*
>- maven local repository
>- maven central
>- maven remote repositories
>
>*Eğer maven central'da veya maven remote repository'de bulursa yine maven local repository'ye indirir. Yani sonuçta maven 2. kullanım hariç kütüphaneyi maven local repository'den kullanır.*

##### Yazılımda Test Süreçleri

>Yazılımda test süreçleri ürün geliştirmenin önemli bir aşamasını oluşturmaktadır. Bazı yazılımlarda, ürünün herşeyiyle doğru olması kritik öneme sahip olabilmektedir. Bazı yazılımlarda hata toleransları olabilir. Gerektiğinde düzeltilebilir.*
>
>*Eskiden yazılım geliştirmede test süreçleri lüks olarak değerlendiriliyordu. Bu nedenle yalnızca büyük firmalar test bölümleri barındırıyorlardı. Ancak günümüzde yazılımda kalite (software quality) bilinci daha fazla artmış ve test süreçleri daha bilinir ve kullanılır hale gelmiştir.*
>
>*Yazılımda test süreçleri için çeşitli stratejiler kullanılabilmektedir. Test işlemi en aşağı düzeyde programcının kendi yazdığı kodları test etmesi ile başlar. Buna "birim testi (unit testing)" denir. Programcı genel olarak, yazmış olduğu bir metodun doğru çalışıp çalışmadığını test eder (duruma göre "etmelidir"). İşte burada bir metot bir "birim (unit)" olarak düşünülür. Bir yazılımda aslında parçalar bir araya getirilir. Yani metotlar çağrılarak yazılım geliştirilir. Bu bir araya getirme işlemi sonucunda genellikle parçalar yeniden test edilir. Buna da "entegrasyon testi (integration testing)" denilmektedir. Yazılımın önemli parçalarına "modül (module)" denir. Modüller de ayrı ayrı test edilebilir. Buna da "modül testi (module testing)" denir. Nihayet ürün oluşturulur ve bir bütün olarak test edilir. Genellikle bu testlere "kabul testleri (acceptance testing)" denir. Ürün bir bütün olarak önce kurum içerisinde test bölümleri tarafından test edilir. Genellikle bu testlere "alfa testi (alpha testing)" denir. Sonra ürün seçilmiş bazı son kullanıcılara dağıtılır ve gerçek hayat testine sokulur. Buna genellikle "beta testi (beta testing)" denir.*
>
>*Birim testi için pratikte şu 3 yaklaşımdan biri uygulanır:*
>- Hiç birim testi yapmamak: Bu durum yazılım geliştirmede tavsiye edilmese de bir takım özel sebeplerden dolayı firmalar tarafından uygulanabilmektedir. Örneğin geliştirici ekibin sayı olarak azlığı, projenin deadline'ının kısa olması, rakip firmalardan önce ürünü çıkarma kaygısı vb. durumlarda karşılaşılabilmektedir. Buradaki yaklaşım programcının hiç test yapmaması değil, programcıdan bir test beklentisi olmaması ya da özellikle test yapmasının istenmemesi gibi düşünülebilir. Şüphesiz programcı geliştirme sürecinde belirli ölçüde test yapacaktır.
>- Katı katıya birim testi yapmak: Bu durumda neredeyse her birim test edilir. Örneğin bir metodun basit ya da karmaşık olmasına bakılmaksızın birim testi yapılır. Bu durumda zaman kaybı olmaması için birim testi yapan programcıların ayrı olması ideal bir durumdur. Şüphesiz herhangi bir zaman kısıtı yoksa ya da zaman çok uzunsa da uygulanabilir.
>- Gereken birimler için birim testi yapmak: Aslında görünürde en ideal durum budur. Görece basit birimler test edilmez ya da detaylı olarak test edilmez. Bu durumda hangi birimlerin test edileceğinin, hangi birimlerin belirli ölçüde test edileceğinin, hangi birimlerin test edilmeyeceğinin belirlenmesi önemlidir. Bu da geliştiriciler ve yöneticiler açısından tecrübe gerektirebilir.
>
>*Birim testleri genel olarak iki şekilde yapılır: manuel birim testleri, bazı araçlar ile otomatik olarak yapılan birim testleri. Pratikte duruma göre ikisi de tercih edilebilse de otomatik araçlar ile yapılan testler belirli ölçüde testi yapan programcının gereksiz kodları yazmasını engellediğinden daha çok tercih edilir. Hatta bazı firmalar kendi birim testi araçlarını da yazarlar.*
>
>*Java'da temel birim testi aracı "JUnit" olsa da Spring gibi popüler framework'lere ait olan ya da olmayan bir çok farklı araç da söz konusudur. Birim testleri IDE programlar ve build araçları ile daha kullanışlı hale gelir. Aslında bu araçların temel amacı birim testini yapan programcının test işlemini mümkün olduğunca otomatize etmektir. Bu araçlar ile çoğıu durumda her zaman yazılması gereken kodlar programcıya bırakılmaz. Bu durumda programcı için önemli olan yani odaklanması gereken test senaryolarını belirlemek ve yazmaktır. Bu senaryolar için her zaman genel olan durumlar söylenemez. Test edilecek birimin ne olduğuna göre, nasıl test edileceğine göre vb. durumlar için değişiklik gösterebilir.*
>
>*Birim test araçlarının çoğunda kullanılan genel bazı terimler vardır:* 
>
>       setup, teardown, input, expected, actual vb.

**_Anahtar Notlar:_** Test işlemlerinde karşılaştığımız önemli iki terim vardır: Verification & Validation (V&V). Verification, yazılmış olan kodun doğru çalışmasıdır. Validation kodun doğru işi yapmasıdır.

### Kodun Çalışma Süresinin Ölçülmesi

>*Bazen program içerisinde bir kod parçasının (code snippet) ne kadar sürede tamamlandığını hesaplamak isteyebiliriz. Bu işlem, kod parçasının başında o an ki zaman bilgisi alınıp, kodun bitiminde de tekrar zaman bilgisi alınarak hesaplanabilir. Bu zaman, takvim zamanı (calendar time) çok hassas işlemlerde kullanılaamaz. Bu durumda daha hassas ölçümler yapabilen yine zamana bağlı olarak hesaplanabilen değerler kullanılmalıdır. Bu işlemi yapmanın Java'da birden fazla yolu bulunmaktadır. Ayrıca 3. parti olarak yazılmış kütüphanelerin çeşitli sınıfları da kullanılabilmektedir. Hatta programcı isterse kendisi de böyle bir işlem için sınıf ya da sınıflar yazabilir. Bu 3. parti olarak yazılmış kütüphaneler içerisinde popüler olarak kullanılan iki tanesinde StopWatch isimli sınıflar bu işlem için tasarlanmışlardır. Bu popüler kütüphaneler tipik olarak Google guave ve Apache commons kütüphaneleridir. Aslında bu kütüphanelerde pek çok yardımcı sınıf ve metot da bulunmaktadır*

>*System sınıfının static currentTimeMillis metodu 01.01.1970 00:00:00 UTC (geceyarısı) zamanından (epoch time) çağrıldığı tarih zamana kadar geçen milisaniye sayısına geri döner. Bu durumda süresi ölçülecek kodun başındave sonunda bu metot çağrılır ve geri döndürdüğü değerlerin farkı alınır ve istenen zaman biriminde hesaplanabilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

class Application {
    public static void run(String[] args)
    {
        long start = System.currentTimeMillis();

        Console.writeLine(NumberUtil.isPrime(569_785_970_6174_583_067L) ? "Asal" : "Asal değil");

        long end = System.currentTimeMillis();

        Console.writeLine("Duration:%f seconds", (end - start) / 1000.);
    }
}
```

>*System sınıfının static nanoTime metodu currentTimeMillis metodundan görece daha hassas olacak şekilde bir bilgi döndürür. Bu bilgi nano-saniye mertebesindedir. Hassas ölçümlerde bu metodun kullanılması tavsiye edilir. Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

class Application {
    public static void run(String[] args)
    {
        long start = System.nanoTime();

        Console.writeLine(NumberUtil.isPrime(569_785_970_6174_583_067L) ? "Asal" : "Asal değil");

        long end = System.nanoTime();

        Console.writeLine("Duration:%f seconds", (end - start) / 1_000_000_000.);
    }
}
```

>*Bu metotlar istenirse Random sınıfına tohum değeri olarak da verilebilir. Anımsanacağı gibi Random sınıfının default ctor'u ile nesne yaratıldığında zaten tohum değeri her nesne için mümkün olduğunca farklı olacak şekilde belirlenir. Hatta bir çok implementasyonda Random sınıfı nanoTime metodunu da çağırarak bu tohum değerini belirler. Şüphesiz bu şekilde yapmak zorunda değildir. Ancak programcı isterse setSeed metoduna tohum değeri olarak nanoTime metodunun geri dönüş değerini verebilir. Örnek durumu göstermek için yazılmıştır*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        long seedVal = Console.readLong("Tohum değerini giriniz:");
        Random random = new Random(seedVal);

        for (int i = 0; i < 10; ++i)
            Console.write("%02d ", random.nextInt(100));

        Console.writeLine();

        random.setSeed(System.nanoTime());

        for (int i = 0; i < 10; ++i)
            Console.write("%02d ", random.nextInt(100));

        Console.writeLine();
    }
}
```

>*Google Guava kütüphanesinin* `StopWatch` *sınıfının bir kullanımı*
>
>*Maven dependency:*

    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>32.1.3-jre</version>
    </dependency>

```java
package org.csystem.app;

import com.google.common.base.Stopwatch;
import org.csystem.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.concurrent.TimeUnit;

class Application {
    public static void run(String[] args)
    {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Console.writeLine(NumberUtil.isPrime(569_785_970_6174_583_067L) ? "Asal" : "Asal değil");

        stopwatch.stop();

        Console.writeLine("Duration:%f seconds", stopwatch.elapsed(TimeUnit.MILLISECONDS) / 1000.);
    }
}
```

>*Apache commons kütüphanesinde* `StopWatch` *sınıfının bir kullanımı:*
>
>*Maven dependency:*

    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.14.0</version>
    </dependency>

```java
package org.csystem.app;

import org.apache.commons.lang3.time.StopWatch;
import org.csystem.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

class Application {
    public static void run(String[] args)
    {
        StopWatch stopwatch = new StopWatch();

        stopwatch.start();
        Console.writeLine(NumberUtil.isPrime(569_785_970_6174_583_067L) ? "Asal" : "Asal değil");

        stopwatch.stop();

        Console.writeLine("Duration:%f seconds", stopwatch.getTime() / 1000.);
    }
}
```

##### var Değişkenler
>var sözcüğü Java 10 ile birikte dile eklenmiştir. var sonradan eklenen bir sözcük olduğundan kullanıldığı yere göre değişken ismi veya tür belirten bir sözcük olarak derleyici tarafından ele alınır. Bu tarz sözcüklere programlamada **contextual keyword** de denilmektedir. var yalnızca yerel değişkenlerde ve Java 11 ile birlikte ileride ele alacağımız lambda ifadelerinde kullanılabilmektedir.*

>*var değişkenlere ilk değer verilmesi (initialization) zorunludur. Derleyici ilk değer olarak verilen ifadenin türüne göre değişkeninin türünü tespit eder **(type inference/deduction)**. var değişkenlerin çalışma zamanında türleri değişmez. Java'da bir değişkenin türü hiç bir zaman değişmez*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        int x = 10;
        var a = 10;
        var b = 5.6;
        var c = a++;

        Console.writeLine("a = %d, b = %f, c = %d, x = %d", a, b, c, x);
    }
}
```

>*var değişkenlere ilk değer verilmesi zorunludur. Aksi durumda error oluşur*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var a; //error

        a = 10;
    }
}
```

>*var değişkenler for döngülerinde de kullanılabilmektedir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var count = Console.readInt("Bir sayı giriniz:");

        for (var i = 0; i < count; ++i)
            Console.write("%02d ", random.nextInt(100));

        Console.writeLine();
    }
}
```

>*var değişkenler metot parametre değişkenlerinde ve sınıf veri elemanlarında kullanılamaz*

```java
package org.csystem.app;

class Sample {
    public var x  = 10; //error

    public static void create(var a) //error
    {
        //...
        a++;
    }
}
```

>*var değişkenler virgül ile ayrılarak bildirilemez. Bu durumda ayrı ayrı bildirilmeleri gerekir*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var a = 10;
        var b = 20;
        var c = 23;

        //...
    }
}
```


>*var değişkenler mümkün olduğunca kullanılmalıdır. Bu anlamda bazı programcılar temel türler dışında mümkün olduğunca kullanmayı tercih edeler. Biz, tür ne olursa kullanılabildiği her yerde kullanmayı tercih edeceğiz (always applicable).*

##### BigDecimal ve BigInteger sınıfları

>*Anımsanacağı gibi* `IEEE754` *formatında yuvarlama hataları (rounding errors) söz konusu olabilmektedir. Aşağıdaki örneği çeşitli değerler ile çalıştırıp sonucu gözlemleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readDouble("Birinci sayıyı giriniz:");
            var b = Console.readDouble("İkinci sayıyı giriniz:");
            var c = Console.readDouble("Üçüncü sayıyı giriniz:");
            var d = a + b;

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c == d)
                break;
        }
    }
}
```

>*Yukarıdaki gibi bir durumda programcı eşitlik karşılaştırmasını (eğer gerçek sayı türü) genelde doğrudan yapmaz. Bu durumda farklı çözümler tasarlanabilir. Bu durumda örneğin programcı eşitlik karşılaştırması yapacağı sayıların farkının mutlak değerini, belirlediği çok küçük bir değerden (delta) küçük olmasına göre işlem yapabilir. Şüphesiz pek çok yaklaşım uygulanabilir. JUnit'de gerçek sayı türleri için eşitlik karşılaştırması yapan metotlar (Örneğin assertEquals) ayrıca bir delta değeri de almaktadır. Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readDouble("Birinci sayıyı giriniz:");
            var b = Console.readDouble("İkinci sayıyı giriniz:");
            var c = Console.readDouble("Üçüncü sayıyı giriniz:");
            var d = a + b;

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (Math.abs(c - d) <= 0.000001)
                break;
        }
    }
}
```

>`java.math` *paketi içerisinde yuvarlama işleminin belirlenebildiği, bu anlamda yuvarlama olmamasının da sağlanabildiği aynı zamanda double sınırları dışında (çok küçük veya çok büyük) gerçek sayılarla da işlem yapılmasını sağlayan* `BigDecimal` *isimli bir sınıf JavaSE'de bulunmaktadır. Bu sınıf ile örneğin yuvarlama hatası olmasın biçimindeki bir işlem yüzlerce makine komutuyla yapılmaktadır. Dolayısıyla programcının bu sınıfı kullanımda dikkatli olması gerekir. Bu da şüphesiz senaryoya bağlıdır. Yüzlerce makine komutu ile yapmasının maliyetinin olmadığı parasal ve finansal uygulamalarda tercih edilebilir.* `BigDecimal` *sınıfı immutable bir sınıftır*

>`BigDecimal` *sınıfının String parametreli ctor'u ile alınan bir yazı içsel olarak sayıya dönüştürülerek işlem yapılır. Sınıfın pek çok ctor'u bulunmaktadır. String parametreli ctor'lar yazıyı sayıya çeviremezlerse exception oluşur. Sınıfın sayılarla işlem yapan pek çok metodu vardır. Örneğin* `add` *metodu iki tane* `BigDecimal`*'ı toplamak için kullanılır. Şüphesiz bu işlemleri belirlenen yuvarlama yöntemine göre yaparlar.* `BigDecimal` *sınıfının equals isimli metodu ile eşitlik karşılaştırması yapılabilir. Burada eşitlik karşılaştırması da yine yuvarlama yöntemine göre yapılır. Aşağıdaki örnekteki toplama işleminde yuvarlama hatası oluşmaz ancak işlemin yüzlerce makine komutuyla yapıldığı unutulmamalıdır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigDecimal;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = new BigDecimal(Console.read("Birinci sayıyı giriniz:"));
            var b = new BigDecimal(Console.read("İkinci sayıyı giriniz:"));
            var c = new BigDecimal(Console.read("Üçüncü sayıyı giriniz:"));
            var d = a.add(b);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>`BigDecimal` *sınıfının String parametreli ctor'u ile alınan bir yazı içsel olarak sayıya dönüştürülerek işlem yapılır. Sınıfın pek çok ctor'u bulunmaktadır. String parametreli ctor'lar sayıyı yazıya çeviremezlerse exception oluşur. Sınıfın sayılarla işlem yapan pek çok metodu vardır. Örneğin* `add` *metodu iki tane* `BigDecimal`*'ı toplamak için kullanılır. Şüphesiz bu işlemleri belirlenen yuvarlama yöntemine göre yaparlar.* `BigDecimal` *sınıfının equals isimli metodu ile eşitlik karşılaştırması yapılabilir. Burada eşitlik karşılaştırması da yine yuvarlama yöntemine göre yapılır. Aşağıdaki örnekteki toplama işleminde yuvarlama hatası oluşmaz ancak işlemin yüzlerce makine komutuyla yapıldığı unutulmamalıdır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigDecimal;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = new BigDecimal(Console.read("Birinci sayıyı giriniz:"));
            var b = new BigDecimal(Console.read("İkinci sayıyı giriniz:"));
            var c = new BigDecimal(Console.read("Üçüncü sayıyı giriniz:"));
            var d = a.add(b);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>`Console` sınıfının `readBigDecimal` metotları

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readBigDecimal("Birinci sayıyı giriniz:", "Hatalı giriş !...");
            var b = Console.readBigDecimal("İkinci sayıyı giriniz:", "Hatalı giriş !...");
            var c = Console.readBigDecimal("Üçüncü sayıyı giriniz:", "Hatalı giriş !...");
            var d = a.add(b);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>*Bölme işlemi yapan metotlar önemlidir. divide işleminde sayının ondalık kısmının ne kadar olacağı ve geri kalanların nasıl yuvarlanacağı belirlenmelidir. Bu durumda tek parametreli divide metodunda elde edilen değerin ondalık kısmı Matematiksel olarak sonsuz tane ise exception oluşur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readBigDecimal("Birinci sayıyı giriniz:", "Hatalı giriş !...");
            var b = Console.readBigDecimal("İkinci sayıyı giriniz:", "Hatalı giriş !...");
            var c = Console.readBigDecimal("Üçüncü sayıyı giriniz:", "Hatalı giriş !...");
            var d = a.divide(b);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>*divide metodunun aşağıda kullanılan overload'u noktadan sonra kaç basamak elde edileceğini ve yuvarlama yöntemini de parametre olarak almaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.RoundingMode;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readBigDecimal("Birinci sayıyı giriniz:", "Hatalı giriş !...");
            var b = Console.readBigDecimal("İkinci sayıyı giriniz:", "Hatalı giriş !...");
            var c = Console.readBigDecimal("Üçüncü sayıyı giriniz:", "Hatalı giriş !...");
            var d = a.divide(b, 10, RoundingMode.HALF_UP);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>*Bir* `BigDecimal` *nesnesi scale vermeden yaratıldığında default scale'ı sıfır olarak ele alınır. Bu durumda aşağıdaki örnekte elde edilen değerde noktadan sonraki kısım olmayacaktır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.RoundingMode;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readBigDecimal("Birinci sayıyı giriniz:", "Hatalı giriş !...");
            var b = Console.readBigDecimal("İkinci sayıyı giriniz:", "Hatalı giriş !...");
            var c = Console.readBigDecimal("Üçüncü sayıyı giriniz:", "Hatalı giriş !...");
            var d = a.divide(b, RoundingMode.HALF_UP);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>`BigDecimal` *sınıfının* `setScale` *metodu ile scale ve rounding mode bilgileri değiştirilmiş aynı değerde yeni bir BigDecimal nesnesi elde edilebilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.RoundingMode;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readBigDecimal("Birinci sayıyı giriniz:", "Hatalı giriş !...");
            var b = Console.readBigDecimal("İkinci sayıyı giriniz:", "Hatalı giriş !...");
            var c = Console.readBigDecimal("Üçüncü sayıyı giriniz:", "Hatalı giriş !...");

            a = a.setScale(10, RoundingMode.HALF_UP);
            var d = a.divide(b, RoundingMode.HALF_UP);

            Console.writeLine("a = %.20f", a);
            Console.writeLine("b = %.20f", b);
            Console.writeLine("c = %.20f", c);
            Console.writeLine("d = %.20f", d);

            if (c.equals(d))
                break;
        }
    }
}
```

>*BigDecimal sınıfının yukarıda anlatılanlar dışında da pek çok metodu bulunmaktadır. Bu metotlar kullanıldıkça anlaşılacaktır*

>`long` *sınırları dışında kalan (çok büyük ya da çok küçük) tamsayılar ile çalışmak için tipik olarak* `java.math` *paketi içerisinde bildirilmiş* `BigInteger` *sınıfı kullanılmaktadır. BigInteger sınıfı* `(-2 ^ Integer.MAX_VALUE, 2 ^ Integer.MAX_VALUE)` *aralığında tamsayı değerlerini tutabilmektedir. BigInteger sınıfının da yine pek çok metodu ve ctor'u bulunmaktadır. Yine BigInteger ile işlemlerin de yapıldığı metotlar da vardır. BigInteger sınıfı da **immutable'dır**. Yine eşitlik karşılaştırması için* `equals` *metodu kullanılabilmektedir*

>`Console` sınfının `readBigInteger` metotları

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {

        while (true) {
            var a = Console.readBigInteger("Input first number:", "Invalid value!...");
            var b = Console.readBigInteger("Input second number:", "Invalid value!...");
            var c = Console.readBigInteger("Input third number:", "Invalid value!...");
            var d = a.add(b);
            if (c.equals(d))
                break;

            Console.writeLine("a = %s", a);
            Console.writeLine("b = %s", b);
            Console.writeLine("c = %s", c);
            Console.writeLine("d = %s", d);
        }
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigInteger;

class Application {
    public static void run(String[] args)
    {

        while (true) {
            var a = new BigInteger(Console.read("Input first number"));
            var b = new BigInteger(Console.read("Input second number"));
            var c = new BigInteger(Console.read("Input third number"));
            var d = a.add(b);
            if (c.equals(d))
                break;

            Console.writeLine("a = %s", a);
            Console.writeLine("b = %s", b);
            Console.writeLine("c = %s", c);
            Console.writeLine("d = %s", d);
        }
    }
}
```

>`BigInteger` *sınıfının* `Random` *parametreli ctor'ları ile rasgele bir BigInteger sayı üretilebilir. int ve Random parametreli ctor'u birinci parametresi* `numBits` *için* `[0, 2 ^ numbits - 1]` *aralığında düzgün dağılım **(uniformly distributed)** ile rasgele sayı üretir. Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigInteger;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();

        for (var i = 0; i < 10; ++i) {
            var a = new BigInteger(128, random);

            if (random.nextBoolean())
                a = a.negate();

            Console.writeLine(a);
        }
    }
}
```

>`BigInteger` ve `BigDecimal` *sınıfılarının* `compareTo` *metotları aşağıdaki biçimde çalışırlar:*
>
>       result = a.compareTo(b)
>için
>
>       result > 0 <=> a sayısı b'den büyüktür
>       result == 0 <=> a sayısı b'ye eşittir
>       result < 0 <=> a sayısı b'den küçüktür
>*şeklindedir.*

**_Anahtar Notlar:_** `compareTo` metodu Java dünyasında karşılaştırma gereken durumlarda kullanılmaktadır. Bu anlamda sıralama gibi karşılaştırma gereken işlemlerde ilgili sınıflara yazılır. Bunun detayı ileride ele alınacaktır. Genel olarak `compareTo` metotlarının geri dönüş değerinin sayısal değerinin önemi olmaz, değerin işaretinin önemi yukarıda anlatıldığı gibi ele alınır

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var a = Console.readBigInteger("Input first number:");
        var b = Console.readBigInteger("Input second number:");
        var c = Console.readBigDecimal("Input first number:");
        var d = Console.readBigDecimal("Input second number:");

        Console.writeLine(a.compareTo(b));
        Console.writeLine(c.compareTo(d));
    }
}
```

>`BigDecimal` ve `BigInteger` *sınıflarının bazı veri elemanları ile bazı değerler için yaratılmış nesnelerin referansları elde edilebilir. Örneğin,* `ONE`, `TEN`, `ZERO` *veri elemanları her iki sınıfta da bulunmaktadır. BigInteger sınıfına Java 9 ile birlikte* `TWO` *veri elemanı da eklenmiştir*

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigInteger;

class Application {
    public static void run(String[] args)
    {
        var count = Console.readBigInteger("Input count:");

        for (var i = BigInteger.ZERO; i.compareTo(count) < 0; i = i.add(BigInteger.ONE))
            Console.writeLine(i);
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.math.BigDecimal;

class Application {
    public static void run(String[] args)
    {
        var bound = Console.readBigDecimal("Input bound:");
        var inc = Console.readBigDecimal("Input increment value:");

        for (var i = BigDecimal.ZERO; i.compareTo(bound) < 0; i = i.add(inc))
            Console.writeLine(i);
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
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
```

>**_Sınıf Çalışması:_** `org-csystem-util` kütüphanesinin **12.0.0** sürümüne aşağıdaki `factorial` metodunu ve birim testi kodlarını yazınız:
>
>       public static BigInteger factorialBigInteger(int n);
>Çözüm için `org-csystem-util` kütüphanesini inceleyiniz

>**_Sınıf Çalışması:_** `org-csystem-util` kütüphanesinin **12.0.0** sürümüne aşağıdaki aşağıdaki `isPrime` metodunu ve birim test kodlarını ekleyiniz
>
>       public static boolean isPrime(BigInteger val);

>*Aşağıdaki örnekte aynı sayılar için* `isPrime` *metotlarının performanslarını gözlemleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.apache.commons.lang3.time.StopWatch;
import org.csystem.util.numeric.NumberUtil;

import java.math.BigInteger;

class Application {
    public static void run(String[] args)
    {
        var stopWatch = new StopWatch();

        stopWatch.start();
        Console.writeLine(NumberUtil.isPrime(710_584_055_392_819_667L));
        stopWatch.stop();
        Console.writeLine("%f seconds", stopWatch.getNanoTime() / 1_000_000_000.);

        stopWatch.reset();

        stopWatch.start();
        Console.writeLine(NumberUtil.isPrime(BigInteger.valueOf(710_584_055_392_819_667L)));
        stopWatch.stop();
        Console.writeLine("%f seconds", stopWatch.getNanoTime() / 1_000_000_000.);
    }
}
```
##### Linux işletim sisteminin dizin yapısı
>*Linux Foundation grubu* `UNIX` *sistemlerindeki dizin yapısını standardize etmeye çalışmıştır. Bu standarda "File System Hierarchy Standard" denir. Buna göre bazı dizinler ve anlamları şunlardır:*
>
>`/bin:` *Burada kabuk (shell) komutlarına ilişkin executable dosyalar ve çeşitli utility programlar bulunur.*
>
>`/sbin:` *Burada sisteme ilişkin aşağı seviyeli executable dosyalar ve çeşitli utility programlar bulunur. Örneğin sistemin boot edilmesi için gereken dosyalar buradadır. Genel olarak* `/sbin` *içerisindeki dosyalar normal kullanıcılar için değil sistem yöneticileri yani root kullanıcısı içindir.*
>
>`/boot:` *Bu dizinde **"boot loader"** ve bazı çekirdeğe **(kernel)** ilişkin dosyalar buklunur. Linux dağıtımlarında* `lilo`, `grub` *gibi bazı popüler boot loader'lar kullanılır.*
>
>`/lib:` *Burada* `/bin` ve `/sbin` *içerisinde bulunan programların kullandığı kütüphaneler bulunur.*
>
>`/dev:` *Burada aygıt sürücülere **(device driver)** ilişkin dosyalar bulunmaktadır.*
>
>`/etc:` *Bu dizin "etcetera" sözcüğünün kısaltmasından oluşturulmuştur. İlk zamanlarda bu dizin diğer dizinlerde olmayacak şeyleri içeriyordu. Sonraki yıllarda burada olanlar da gittikçe belirgin olmaya başlamıştır. Bu dizinde genel oalrak çeşitli konfigürasyon bilgileri tutulur. Bu nedenle **etc** ismi artık **"editable text configuration"** kısaltması olarak kullanılmaktadır*
>
>`/home:` *Burada kullanıcılar için ayrılan dizinler tutulur. Normal olarak her kullanıcın kullanıcı ismine ilişkin bir dizini vardır*
>
>`/mnt:` *Kullanıcıların mount işlemi için kullabilecekleri genel bir dizindir.*
>
>`/root:` *Bu dizin root kullanıcısı için home dizini görevindedir.*
>
>`/media:` *Bu dizin çıkarılabilir aygıtların (CDROM, Flash EPROM vb.) mount edildiği dizindir*
>
>`/usr:` *Burada kullanıcıların yerleştirdiği ya da install ettiği tüm yazılımlara ilişkin executable dosyalar, kütüphaneler ve bazı geliştirme araçları için gereken dosyalar bulunur.* `/usr/bin` *dizininde genel olarak dağıtıma ilişkin utilty programlar bulunur.* `/usr/local` *lokal makinedeki programlar için düşünülmüştür.*
>
>`/var:` *Bu dizin log dosyaları gibi sistemin çalışması sırasında sürekli güncellenen dosyaların tutulduğu bir dizindir. Bu dizinin de pek çok alt dizini vardır*
>
>`/sys:` *Aygıt sürücülerin ve çekirdeğe ilişkin bazı dosyaların bulunduğu dizindir*
>
>`/tmp:` *Geçici dosyalar için bulundurulan bir dizindir. Genellikle sistem kapatılırken silinmektedir*

##### Sisteme Giriş (login)
>`UNIX` *sistemlerinde her kullanıcıya bir username ve bir passowrd verilir. Bir kullanıcı username ve password ile sisteme giriş yapar. Sisteme giriş yapmak genellikle 3 yoldan yapılabilir:*
>1. **Text tabanlı bir terminal program ile:** Eğer sistemde bir Graphical User Interface (GUI) (tipik olarak Xwindow) yoksa bu yoldan giriş yapılır. Genellikle sunucu (server) sistemlere bu şekilde erişilr.
>2. **GUI ile:** Eğer sistemde bir GUI varsa bunlarla giriş yapılabilir.
>3. **Uzak bağlantı (remote) yoluyla:** Uzak bağlantı yoluyla erişim tipik olarak `ssh` ve `telnet` gibi bir protokolle text tabanlı olarak. VNC gibi protokol ile de GUI olarak yapılabilmektedir. Örneğin ssh ile bağlatı şu şekilde yapılabilir:
>
>        ssh oguz@192.168.1.123
>Burada tipik olarak `oguz` kullanıcı ismi ve `@` işaretinden sonra yazılan bilgi ise uzak makinenin adres bilgisidir.

##### UNIX/Linux Sistemlerinde Yeni Kullanıcıların ve Grupların Yaratılması:
>*UNIX sistemlerinin çoğunda kullanıcılara ilişkin bilgiler text dosalarda tutulur. Bu text dosyanın her satırı bir kullanıcıya ilişkin bilgilerden oluşur.* `Linux` ve `BSD` *sistemlerinde* `/etc/passwd` *dosyası kullanıcı bilgilerini tutan bir dosyadır. Her kullanıcının bilgisi burada tutulur. Bu dosya normal kullanıcılar için **read only** durumdadır. Yani bu dosyanın içeriğini normal kullanıcılar görüntüleyebilir ancak dosyada değişiklik yapamaz. Bir kullanıcıya ilişkin bilgiler* `:` *ile ayrılır ve toplam 7 tane sütun bulunur:*
>
>       csystem:x:1000:1000:JavaApp1-Mar-2023 Group,,,:/home/csystem:/bin/bash
>Buradaki 7 sütunun anlamları kabaca şu şekildedir:
>1. Kullancı ismi
>2. Kullanıcının password'üne ilişkin encrypted bir bilgidir. Eskiden kullanıcılar şifrelenmiş parola bilgileri bu dosyada saklanırdı. Bu anlamda şifrelenmiş bilgilerin şifrelemesi tek yönlü (one way) yapıldığı için bu bilginin elde edilmesinde bir sakınca görülmemiştir. Zamanla bu bilginin de görülmesi istenmediğinden `/etc/passwd` dosyasında `x` olarak yazılmaya başlandı. Bu bilgi ayrı bir dosyada saklanır duruma geldi. Bu bilgi tipik olarak `/etc/shodow` dosyası içerisinde saklanır ve bu dosyanın içeriği normal kullanıcılar tarafından okunamaz ve değiştirilemez.
>3. Kullanıcı id'si her kullanıcı ismine karşılık verilir. İki kullanıcının id'si aynı olamaz. Tipik olarak `root` kullanıcısının id bilgisi sıfırdır.
>4. Grup id'si her gruba karşılık verilir. Kullanıcıların ait olduğu grupların bilgileri de `/etc/group` dosyasında tutulur. Her yeni kullanıcı için default olarak ayrı bir grup oluşturulur
>5. Kullanıcıya ilişkin bilgiler bulunur. Bilgiler virgül ile ayrılır ve 4 bölüm vardır. Bilgiler boş geçilebilir ancak virgüller yine bulunur.
>6. Kullanıcı dizinine ilişkin yol ifadesi belirtilir
>7. Kullanıcının sisteme ilk giriş yaptığında çalıştırılacak terminal program belirtilir. Buradaki program default olarak çalıştırılır. Linux sistemlerinde default olarak `bash` (Bourne Again Shell) kullanılır.
>
>*Öyleyse kullanıcı eklemek için tipik olarak* `/etc/passwd` *dosyasına uygun satırı eklemek gerekir. Tabi bu durumda kullanıcı dizini, password ve grup id gibi bilgilerin de oluşturulması gerekir. Bu işlemleri manuel olarak yapmak oldukça zahmetli olabilmektedir. Bu sebeple adduser isimli bir komut vardır. Ancak bu komut pek user friendly değildir. Bu sebeple daha user friendly olan useradd isimli ayrı bir komut vardır. User oluşturabilmek için root yetkisine sahip olmak gerekir. root yetkisine sahip olan bir user'a* `sudoer` *denir. sudoer olan bir user ile login olunduğunda* `sudo` *(super user do) isimli komut ile root şifresi de girilerek root yetkisi elde edilebilir. Eğer user sudoer değilse kesinlikle root yetkisine sahip işlemleri yapamaz.*
>
>**_Anahtar Notlar:_** Bazı lightweight sistemlerde kurulum sırasında root kullanıcısına ilişkin bilgiler sorulmaz. Tipik olarak `Ubuntu` ve `Mint` dağıtımları bu şekildedir. Bu sistemler kurulurken belirlenen ilk user sudoer yapılır ve parolası aynı zamanda root kullanıcısının da parolası olur.
>
>*Benzer şekilde bu sistemlerde grup da oluşturlabilir. Bunun için de addgroup ve daha user friendly olan groupadd programları kullanılabilir. Grup oluşturma ve kullanıcıların gruplara eklenmesi gibi kavramlar projeler içerisinde kullanılacaktır.
##### İşletim Sistemlerinin Dosya Sistemleri
>*İçerisinde bilgilerin bulunduğu ikincil belleklerdeki (secondary memory) alanlara dosya (file) denir. Bu bilgiler sektör (sector) denilen okunabilen ve yazılabilen en küçük birimlerde tutulur. İşletim sistemleri bu organize edilmiş bilgileri dışarıya dosya kavramı gösterirler. Aslında dosya mantıksal bir kavramdır. İşletim sistemlerinin bu orgaznisyonu yapan alt birimine dosya sistemi (file system) denir. Dosya sistemi Unix/Linux sistemlerinin adeta kalbi biçimindedir. Bu sistemlerde pek çok kavram dosya gib ele alınır. Örneğin klasik dosyalar, diziler (directories), borular (pipes), soketler (sockets) vb.*
>
>*Bu sistemlerde bir çeşit `polymorphism` uygulanmıştır. Örneğin bir dosyaya yazma yaptığımızda dosyanın türüne göre yazma işlemi gerçekleşir. Yani gerçek anlamda bir dosyaya yazma olmayabilir. Polymorphic yaklaşım dolayısıyla Linux sistemlerinin dosya sistemine **"Sanal dosya sistemi (virtual file system)"** de denilmektedir.*
>
##### Unix/Linux Sistemlerinde Dosya Erişim Hakları

>Bir dosya ile işlem yapmak genel olarak iki gruba ayrılır:
>   - Dosyanın **_bütünü_** üzerinde işlem yapmak
>   - Dosya **_içerisindeki veriler_** ile işlem yapmak.
>
>*Şüphesiz dosya içerisinde veriler ile işlem yapmak için dosyanın norma bir dosya (regular file) olması gerekir. Dosya işlemleri gayrıca 3 gruba ayrılır: Yazma (write), okuma (read), hem okuma hem yazma (read-write).*
>
>*Dosyaya erişim uygulamalar tarafından yapılır. Örneğin bir bir dosyanın içeriğini cat programı ile stdout'a göndermek istediğimizde* `cat` *programı dosyayı okumak için açar ve okuma işlemlerini yapar. Bu durumda cat programının bu dosyayı okumak için yetkisinin olması gerekir. İşletim sistemlerinde çalışan bir programa process denir. Unix/Linux sistemlerinde process'lere ilişkin bilgiler* `ps` *isimli bir komut (program) ile elde edilebilir.*
>
>*Örneğin* `ps -ef` *biçiminde bir çalıştırma ile process'lere detay bilgiler elde edilebilir. Bu sistemlerde her process'lerin id değerleri vardır. Bu id değeri sistem genelinde tekil (unique) olarak belirlenir. Process için ayrıca çalışma zamanında değişebilen (detayları önemsiz) effective user id ve effective group id denilen id değerleri de vardır. Bunun dışında real user id ve real group id denilen id değerleri de bulunur. Bu sistemlerde bir dosyanın da bir user id'si ve bir group id'si bulunur. Dosyalar için effective veya gerçek id gibi kavramlar yoktur. Bir dosyanın erişim hakları, user ve group id vb bilgileri* `ls` *isimli bir program ile elde dilebilir.*
>
>*Örneğin* `ls -l` *biçiminde çalıştırıldığında bulununan directory'nin içerisindeki tüm dosyalar çeşitli bilgileri ile birlikte listelenir:*
>
>       -rw-rw-r--  1 oguz oguz 169251172 Feb 20  2023 jdk-11.0.17_linux-x64_bin.tar.gz
>       -rw-rw-r--  1 oguz oguz 188057617 Feb 20  2023 jdk-15.0.2_linux-x64_bin.tar.gz
>       -rw-rw-r--  1 oguz oguz 181577323 Feb 20  2023 jdk-17.0.6_linux-x64_bin.tar.gz
>       -rw-------  1 oguz oguz       207 Apr 12  2023 nohup.out
>       drwxrwxr-x 11 oguz oguz      4096 Nov 11 13:55 study
>
>*Burada* `3.` ve `4.` *sütunlar sırasıyla dosyanın user id'si ve grup id'sini belirtir. Aslında burada id'lerin değeri doğrudan yazmaz. Her kullanıcı ismine grup ismine karşılık bir id değeri verildiğini anımsayınız. Dosyanın erişim hakları aslında dosya ile hangi işlemlerin yapılıp yapılmayacağını belirtir. Bu anlamda yukarıdaki birinci sütunda dosyanın türü ve erişim hakları bilgisi bulunur. En soldaki bilgi dosyanın türünü belirtir. Bu bilgi `-` ise dosya normal bir dosyadır `(regular file)`. `d` ise bir `dizin` belirtir, `p` ile bir `pipe` belirtir, `s` ise bir `soket` belirtir, `c` ise bir `chracter device` belirtir, `b` ise bir `block device` belirtir, `l` ise `symbolic link` belirtir. Burada normal dosya ve dizinler için erişim haklarını inceleyeceğiz. Birinci sütundaki dosya türünden sonra gelen 9 karakter üçerli üç gruba ayrılır. Bu üçerli gruplar* `rwx` *biçiminde oluşturulur. Eğer dosya için okuma hakkı varsa `r`, yazma hakkı varsa `w` ve çalıştırma (execute) hakkı varsa `x` yazılır. Hakkın olmaması durumunda `-` ile belirtilir. Buradaki ilk üçlü sahiplik `owner`, ikinci üçlü grup `group`, üçüncü üçlü ise diğerinin `others` haklarını temsil eder. Normal bir dosya için okuma hakkı dosyanın verilerini okuma hakkı anlamındadır. Örneğin `cat` process'inin bir dosyanın içeriğine erişmesi için `rs` hakkına sahip olması gerekir. Dosya bir dizinse `r` hakkı o dizin içerisindeki dosya bilgilerinin elde edilmesi anlamındadır. Örneğin `ls` programının ilgili dizindeki dosya bilgilerini elde etmesi için o dizinin `ls` için `r` hakkı olması gerekir. Normal bir dosya için `w` hakkı dosyanın verileri üzerinde değişiklik yapma hakkıdır. Örneğin bir process'in bir dosyaya veri eklemesi için `w` hakkı olması gerekir. Bir dizin için `w` hakkı o dizin içerisinde olan bir dosyanın silinmesi veya yeni bir dosya eklenebilmesi hakkıdır. Normal bir dosya için `x` hakkı o dosyanın çalıştırılabilmesi (execute) hakkıdır. İşletim sistemi düzeyinde bir programın çalıştırılabilmesi için `x` hakkına sahip olması gerekir. Şüphesiz Java ile yazılmış ve jar olarak üretilmiş bir programın bu hakka sahip olması gerekmez. Çünkü Java uygulamaları JVM tarafından çalıştırılır. Ancak java programının `x` hakkına sahip olması gerekir. Zaten `JRE` veya `JDK` bu sistemlerde install edildiğinde bu programlar da `x` hakkına sahip olacak şekilde dosya olarak yazılır. Bir dizin için `x` hakkı aslında bir geçiş hakkıdır. Bir dosyaya erişirken kullanılan yol ifadesinde bulunan dizinlerin x hakkı yoksa o dizinlerden geçilemez. Örneğin yol ifadesi* `/a/b/c/test.txt` *biçimindeyse burada* `test.txt` *dosyasına erişmek için root, a, b ve c dizinlerinin x hakkına sahip olması gerekir. Aksi durumda erişilemez. Bu durumda bir dizin için r ve w hakkı olmayabilir. Ancak o dizinden geçiş yapılabilir.*
>
>*Bu erişimler bir process için şu şekilde bakılarak elde edilir. Bir process bir dosyaya erişmek istediğinde process'in effective user id'si erişmek istediği dosyanın user id'si aynı ise dosyanın sahiplik hakları söz konusu olur, değilse process'in effective group id'si erişmek istediği dosyanın group id'si ile aynı ise grup hakları söz konusu olur, değilse diğerleri için olan hakları söz konusu olur.*
>
>*Bir dosyanın erişim hakları tipik olarak* `chmod` *isimli bir program kullanılarak değiştirilebilir. Bu program kullanıcıya ait olmayan bir dosya için* `root` *olarak çalıştırılmalıdır.* `chmod` *komutu oldukça kapsamlıdır.* `+w`, `+r`, `+x` *seçenekleri ile ilgili erişim hakları tüm 3'erli gruplara verilebilir. Benzer şekilde* `-w`, `-r`, `-x` *seçeneği ile haklar alınabilir. Bu komutun önemli bir kullanımı da ilgili hakların octal sistemde değer verilerek kullanılmasıdır. Her bir 3'lü octal sistemde bir değer ile belirlenir.* `chmod` *komutuna sıfır ile birlikte 3 tane octal digit yazılarak erişim hakları belirlenir. Örneğin* `test` *isimli dosyanın erişim hakkının* `rwxr-x--x` *şekilde olması için* `chmod` *komutu şu şekilde kullanılabilir:*
>
>       chmod 0751 test
>       
>**_Anahtar Notlar:_** Burada anlatılanların dışında pek çok detay bulunmaktadır. Java programcısı açısından gerekenler genel olarak anlatılmıştır.
>
>*Bir program çalıştırıldığında 3 tane dosya açılır:*
>
>       Standard input  (stdin)
>       Standard output (stdout)
>       Standard Error  (stderr)
>*Java'da stdin, stdout ve stderr sırasıyla* `System` *sınıfının `in`, `out` ve `err` static referans veri elemanları ile temsil edilir. `in`, `InputStream` türünden, `out` ve `err`'de `PrinStream` türünden referans veri elemanlarıdır. Masaüstü işletim sistemlerinin hemen hepsinde default olarak `stdin` klavyeye, `stdout` ekrana, `stderr`'de `stout`'a yönlendirilmiştir. Bu dosyalar ayrıca program çalıştırılırken de yeniden yönlendirilebilir **(redirection)**. Hatta programlama yöntemiyle de yönlendirme yapılabilmektedir. Programlama yöntemiyle yapılabilen yönlendirme burada ele alınmayacaktır. Bu dosyalar yönlendirildiğinde artık bu dosyalar ile işlem yapan metotlar ona göre ilgili işlemini yaparlar. Örneğin `stdout` başka bir dosyaya yönlendirildiğinde* `System.out.println` *metodu yazma işlemini yönlendirilen dosyaya yapar. Program çalıştırılırken yönlendirme işlemi **stdout** ve **stderr** için `>` ile, **stdin** için ise `<` karaketeri kullanılarak yapılır. Bu kullanım biçimi modern işletim sistemlerinin bir çoğunda aynıdır. **stdout** için `>` biçiminde kullanılır. Bazı sistemlerde desteklenmeyebileceğinden, **stderr** için de `2>` biçiminde kullanılmalıdır. Örneğin bir program aşağıdaki gibi çalıştırılmış olsun:*
>
>       java -jar .\SampleApp-11.0.0.jar < input.txt > output.txt 2> error.txt
>*Burada `stdin` `input.txt` dosyasına, `stdout` `output.txt` dosyasına, `stderr` de `error.txt` dosyasına yönlendirilmiştir.*
>
>**Peki stderr neden kullanılır?** 
>
>*Tipik olarak uygulamalar çeşitli hata durumlarına karşılık mesajlarını `stderr`'ye basma eğilimindedir. Bu bir convention olarak düşünülebilir. Bu durumda örneğin, program çalıştırıldığınde hatalı durumlara ilişkin mesajların takibi de yapılabilir.*
>
>*Bir programın `stdout`'u başka bir programın `stdin`'i olabilir. Bu işlem bir çok işletim sisteminde aşağıdaki gibi boru `pipe` haberleşmesi kullanılarak yapılabilir:*
>
>       java -jar RandomNumberGenerator-11.0.0.jar | java -jar NumberReader-11.0.0.jar
>Burada `RandomNumberGenerator-11.0.0.jar` uygulamasının `stdout`'u, `NumberReader-11.0.0.jar` uygulamasının `stdin`'ine yönlendirilmiştir. 
>
>`004-DemoRedirectionApplications` içerisindeki demo uygulamaları inceleyiniz

##### Recursion, Recursive algoritmalar ve Recursive Metotlar:
>
>*Bir olgunun kendisine çok benzer bir olguyu içermesi durumuna `recursion` denir. Örneğin bir dizin (directory) ağacını dolaşmak için dizin içerisindeki bir girişin bir dizin belirtmesi duurmunda tekrar o dizin için de aynı işlemlerin yapılması gerekir. Yani karşımıza aynı durum çıkmıştır. Bu durumda bu algoritma recursion içermektedir.*
>
>*Recursion içeren algoritmalara `recursive` algoritmalar denir.*
>
>*Algoritmik bakımdan recursion içeren problemler 3 (üç) gruba ayrılmaktadır:*
>1. Hem recursive hem de recursive olmayan şekilde yazılabilen algoritmalar
>2. Yalnızca recursive olarak yazılabilen algoritmalar
>3. Yalnızca recursive olmadan yazılabilen algoritmalar
>
>*Birinci gruptaki algoritmaların hangisinin daha verimli olacağı değişebilmektedir. Programcının buna doğru olarak karar vermesi gerekebilir*
>
>*Yazılım dünyasında karşılaştığımız `recursive` algoritmalar tipik olarak şunlardır:*
>- Dizin ağacının dolaşılması
>- Ağaçlar (trees) ve graflar (graphs) gibi veri yapılarının dolaşılması ve bu veri yapılarında arama yapılması
>- Bazı sıralama (sort) algoritmaları (quick sort, merge sort, heap sort vb)
>- Parsing algoritmaları
>- Özel bazı problemler (Satrançta 8 vezir problemi vb.)
>- Matematiksel bazı algoritmalar
>- Bazı optimizasyon problemleri
>- ...
>
>*Anımsanacağı gibi, bir metodun parametre değişkenleri ve yerel değişkenleri stack'de yaratılırlar. Ancak sisteme bağlı olarak (işlemci, işletim vb.) aşağı seviye metot çağrıldığında başka bilgiler de (örneğin saklanmak üzere) stack'e atılabilirler. Bu işlem çok fazla yapıldığında artık stack alanı yetmez duruma gelir **(stack overflow)**. Genelde bir uygulama için stack alanı küçük olma eğilimindedir. Tabi bazı programlama ortamları, stack taşması gibi durumların yanlışlıkla yapılmasını mümkün olduğunca engeller. Örneğin Java'da Stack'de dizi yaratılamaz. Bu durumda çok fazla sayıda elemanı olan bir diziden dolayı stack overflow olma ihtimali yoktur.*
>
>*Bir metot nasıl başka başka bir metodu çağırabiliyorsa kendisini de çağırabilir. Aslında bir metodun kendisini çağırması ile başka bir metodu çağırması arasında teknik olarak bir fark yoktur. Recursive algoritmalar kendi kendini çağıran metotlar ile gerçekleştirilir:*
>
>        public static void foo(int x)
>        {
>        int a;
>
>        //...
>
>        foo(x);
>        }
>*Burada `foo` metodu çağrıldığında `foo`'nun `x` parametre değişkeni ve `a` yerel değişkeni stack'de yaratılır **(push)**. `foo` metodu sonlandığında akış çağrılan noktadan devam eder. Bu metot sonlandığında x ve a stack'den atılacaktır **(pop)**. Bu durumda metot kendi kendi kendisini çağırdığında o çağrı için yeniden parametre değişkeni olan x ve yerel değişken a stack'e atılacaktır **(push).***
>
>*Metodun kendisini çağırması durumunda belli bir noktaya kadar çağrı durdurulmazsa **sonsuz döngü** oluşacak ve belli bir zaman sonra stack taşması oluşacaktır. Öyleyse kendini çağıran bir metodun belli bir noktadan sonra kendi kendisini çağırmaktan vazgeçmesi gerekir.*

**_Anahtar Notlar:_** Recursion içermeyen çözümlere iterative çözümler de denilmektedir. Recursion hem karışık hem de debug etmesi zor bir yöntemdir. Bu nedenle programcılar gerekmediği sürece recursive çözümlere başvurmamalıdır.

>*Aşağıdaki örnekte metodun kendi kendisini çağırması sürekli yapıldığından stack taşması dolayısıyla exception oluşur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

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
```

>*Aşağıdaki örnekte metot kendisini hep 1 eksik değer ile çağırmaktadır. parametre değişkeni olan x değeri sıfır olduğunda artık kendini çağırma işlemi sonlanmış ve çıkış başlamıştır. Dikkat edilirse örnekte bir tek `foo(0)` çağrısı return deyiminden dolayı **sonlanır**. Diğer çağrılar, bekledikleri çağrı sonlandığı için sonlanacaktır. Bu durumda yalnızca `foo(0)` çağrısı için akış `**` ile belirtilen noktaya gelecektir. `foo(1)`, `foo(2)` ve `foo(3)` çağrıları için akış* `***` *ile belirtilen noktaya gelip sonlanacaktır. Örneğin ekran çıktısı şu şekildedir:*
>
>        Giriş:3
>        Giriş:2
>        Giriş:1
>        Giriş:0
>        Çıkış:1
>        Çıkış:2
>        Çıkış:3
>`foo(0)` *çağrısı* `return` *deyimi ile sonlandığından **"Çıkış:0"** yazısının basılmadığına dikkat ediniz*

```java
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
        Console.writeLine("Giriş:%d", x);

        if (x == 0)
            return; //**

        foo(x - 1);
        Console.writeLine("Çıkış:%d", x); //***
    }
}
```

>**_Anahtar Notlar:_** Bazı recursion algoritmalarında metot doğrudan çağrılarak yazılamaz. Bu durumda kendini çağıran bir metot yazılarak ilgili metotta sarmalanarak **(wrapping)** çağrılır

>*Aşağıdaki projede, recursion ile ve recursion olmadan yapılabilen bazı tipik problemlerin çözümleri* `Util` ve `RecursionUtil` *isimli iki sınıfta ele alınmıştır. Projeyi inceleyiniz:*
>
>       ~/src/SampleCodes/CodingChallenges
>*İleride başka recursion içeren problemler de çözeceğiz Örneklerin çoğunda recursion olmadan çözüm daha iyidir. Bu durum ayrı ayrı aşağıda belirtilmiştir:*
>
>       factorial               -> recursion olmayan görece daha efektif
>       gcd                     -> recursion olmayan görece daha efektif
>       reverse                 -> recursion olmayan görece daha efektif
>       writeReverse            -> recursion olmayan görece daha efektif
>       fibonacciNumber         -> recursion olmayan görece daha efektif
>       writeNumber             -> recursion olan görece daha efektif
>       writeCollatz            -> recursion olmayan görece daha efektif
>       findStandardDeviation   -> recursion olmayan görece daha efektif
>
>**_Soru:_** Yalnızca bir tane karakteri ekrana yazan S`ystem.out.write` metodunu kullanarak ve bir dizi kullanarak int türden bir sayıyı ekrana **(stdout)** bastıran `writeNumber` isimli metodu hem recursive hem de recursive olmayan şekilde yazınız.
>
>**_Not:_** Burada bitsel operatörler kullanılmadan yapılmıştır. İleride bitsel operatörler kullanılarak da yapılacaktır
>
>**_Soru:_** Parametresi ile aldığı int türden bir dizinin standart sapması ile birlikte ortalamasını da `StandardDeviationInfo` isimli sınıf olarak döndüren `findStandardDeviation` isimli metodu hem recursive hem de recursive olmayacak şekilde yazınız.

>*Anımsanacağı gibi* `String` *sınıfı `immutable` bir sınıftır. Bazı durumlarda immutable olması avantaj olurken bazı durumlarda ise dezavantaj oluşturur. Genel olarak yazı üzerinde değişiklik yapan algoritmalarda immutable olması dolayısıyla nesne yaratılması maliyeti söz konusu olur. Bu durumda programcı isterse* `char` *türden bir dizi kullanabileceği gibi* `StringBuilder` *sınıfını da kullanabilir. StringBuilder sınıfı String sınıfına yardımcı bir sınıftır. Bu sınıf ne String sınıfına bir alternatiftir ne de String sınıfının mutable olan karşılığıdır. Tamamem String'in immutable olmasının dezavantaj oluşturduğu durumda kullanılması için tasarlanmıştır. Aşağıdaki örnekte* `changeCaseBetter` ve `changeCaseBest` *metotlarının performans açısından bir farkları yoktur. Ancak* `changeCaseSlower` *metodu görece yavaştır. Hatta karakter sayısı arttıkça ve metot çok fazla çağrıldıkça yavaşlık hissedilir duruma gelir.*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Console.writeLine(Util.changeCaseSlower("AnKaRa"));
        Console.writeLine(Util.changeCaseBetter("AnKaRa"));
        Console.writeLine(Util.changeCaseBest("AnKaRa"));
    }
}


class Util {
    public static String changeCaseBest(String s)
    {
        var sb = new StringBuilder(s);
        var len = s.length();

        for (int i = 0; i < len; ++i) {
            var ch = sb.charAt(i);

            sb.setCharAt(i, Character.isUpperCase(ch) ? Character.toLowerCase(ch) :  Character.toUpperCase(ch));
        }

        return sb.toString();
    }
    public static String changeCaseBetter(String s)
    {
        var chars = s.toCharArray();

        for (int i = 0; i < chars.length; ++i)
             chars[i] = Character.isUpperCase(chars[i]) ? Character.toLowerCase(chars[i]) :  Character.toUpperCase(chars[i]);

        return String.valueOf(chars);
    }

    public static String changeCaseSlower(String s)
    {
        var str = "";
        var len = s.length();

        for (int i = 0; i < len; ++i) {
            var ch = s.charAt(i);

            str += Character.isUpperCase(ch) ? Character.toLowerCase(ch) :  Character.toUpperCase(ch);
        }

        return str;
    }
}
```

>*Aşağıdaki metotları inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Console.writeLine(Util.squeezeSlow("ankara", "adana")); //kr
        Console.writeLine(Util.squeezeFast("ankara", "adana")); //kr
    }
}


class Util {
    public static String squeezeFast(String s1, String s2)
    {
        StringBuilder sb = new StringBuilder();

        for (var i = 0; i < s1.length(); ++i) {
            var ch = s1.charAt(i);

            if (!s2.contains(String.valueOf(ch)))
                sb.append(ch);
        }

        return sb.toString();
    }
    public static String squeezeSlow(String s1, String s2)
    {
        String str = "";

        for (var i = 0; i < s1.length(); ++i) {
            var ch = s1.charAt(i);

            if (!s2.contains(String.valueOf(ch)))
                str += ch;
        }

        return str;
    }
}
```
##### Değişken Sayıda Argüman Alan Metotlar

>*Java'da değişken sayıda argüman alabilen metotlar yazılabilir. Bu metotlara **variable length argument methods** ya da kısaca **vararg methods** *denir. Bir metodun vararg parametresi **ellipsis** `...` atomu kullanılarak yazılır. Ellipsis parametresi metot açısından (yani metodun kodları açısından) bir dizi referansıdır. Çağıran açısından vararg parametreye ya aynı türden bir dizi referansı ya da istenilen sayıda **aynı türden** argüman geçilebilir. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        int [] a = {10, 20, 30};

        Console.writeLine(Util.sum(10, 20, 30));
        Console.writeLine(Util.sum(10, 20));
        Console.writeLine(Util.sum(10, 20, 30, 40));
        Console.writeLine(Util.sum(a));
        Console.writeLine(Util.sum());
    }
}

class Util {
    public static int sum(int...a)
    {
        var total = 0;

        for (var val : a)
            total += val;

        return total;
    }
}
```

>Aynı türden hem dizi parametreli hem de vararg parametreli bir metot overload edilemez. Yani aşağıdaki iki metot da aynı imzaya *(signature)* sahiptir.*

```java
package org.csystem.app;

class Util {
    public static int sum(int...a)
    {
        var total = 0;

        for (var val : a)
            total += val;

        return total;
    }

    public static int sum(int [] a)
    {
        var total = 0;

        for (var val : a)
            total += val;

        return total;
    }
}
```

>`vararg` *parametre metodun son parametresi olmak zorundadır. Aksi durumda error oluşur. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Console.writeLine(Util.sum(10));
        Console.writeLine(Util.sum(10, 20, 30));
    }
}

class Util {
    public static int sum(int start, int... a)
    {
        var total = start;

        for (var val : a)
            total += val;

        return total;
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.array.ArrayUtil;

class Application {
    public static void run(String[] args)
    {
        int [] a = {1, 2, 3};
        int [] b = {4, 5, 6};
        ArrayUtil.print(Util.merge(a, b));
        ArrayUtil.print(Util.merge(a, 10, 30, 40));
    }
}

class Util {
    public static int [] merge(int[]a, int...b)
    {
        var result = new int[a.length + b.length];

        for (var i = 0; i < a.length; ++i)
            result[i] = a[i];

        for (var i = 0; i < b.length; ++i)
            result[a.length + i] = b[i];

        return result;
    }
}
```

>*Aşağıdaki demo örnekte* `int` *parametreli* `foo` *çağrılır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample.foo(10);
    }
}

class Sample {
    public static void foo(int a)
    {
        Console.writeLine("foo, int");
    }

    public static void foo(int...a)
    {
        Console.writeLine("foo, int...");
    }
}
```

>*Aşağıdaki örnekte,* `int -> long` *dönüşümü* `int -> int...` *dönüşümünden daha kaliteli olduğundan* `long` *parametreli* `foo` *çağrılır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample.foo(10);
    }
}

class Sample {
    public static void foo(long a)
    {
        Console.writeLine("foo, long");
    }

    public static void foo(int...a)
    {
        Console.writeLine("foo, int...");
    }
}
```

>*Aşağıdaki örnekte,* `int -> int...` *dönüşümü* `int -> long...` *dönüşümünden daha kaliteli olduğundan* `int...` *parametreli* `foo` *çağrılır*

```java
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
```

>*Aşağıdaki örnekte `int[]` parametreli `foo` metodu applicable değildir. Dolayısıyla* `long...` *parametreli* `foo` *çağrılır*

```java
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

    public static void foo(int[]a)
    {
        Console.writeLine("foo, int...");
    }
}
```

>`Java 8` *ile birlikte* `Integer` ve `Long` sarmalayan sınıflarına (wrapper classes) işaretsiz olarak işlemler yapan bazı metotlar eklenmiştir. Örneğin bu sınfların* `compare` *static metotları karşılaştırma işlemini işaretli olarak yaparken,* `compareUnsigned` *metotları karşılaştırma işlemini işaretsiz olarak yapar. Yani işaretsiz olarak işlem yapan metotlar için işaret bitinin **(sign bit/the most significant bit)** önemi yoktur*
>
>**_Anahtar Notlar:_** Java dünyasında iki kavramın karşılaştırılması **(comparison)** için kullanılan metotlar şu şekilde bir convention'a göre çalışırlar:
>
>        c = compare(a, b)
>`a` ve `b` *değerlerini karşılaştırma yapan bir metot çağrısı olsun,* `c` *de bu çağrının sonucunda elde edilen* `int` *türden bir değer olsun:*
>
>       a < b   <=> c < 0
>       a > b   <=> c > 0
>       a == b  <=> c == 0
>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.kara.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        int a = 0x80000B00; // 0b10000000_00000000_00001011_00000000
        int b = 0x00000B00; // 0b00000000_00000000_00001011_00000000

        Console.writeLine("a = %d, b = %d", a, b);
        Console.writeLine(Integer.compare(a, b));
        Console.writeLine(Integer.compareUnsigned(a, b));
    }
}
```

>`Integer` ve `Long` *sınıflarına* `parseUnsignedXXX` *metotları da eklenmiştir. Bu metotlar yazıya ilişkin sayı negatif ise **exception** fırlatırlar. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import java.util.Scanner;

class Application {
    public static void run(String[] args)
    {
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        int a = Integer.parseUnsignedInt(kb.nextLine());

        System.out.printf("a = %d%n", a);
    }
}
```

>`Integer` ve `Long` *sınıflarının* `toUnsignedString` *metotları sayının işaretsiz karşılığını yazı olarak döndürür. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        int a = 0x80000B00; // 0b10000000_00000000_00001011_00000000
        int b = 0x00000B00; // 0b00000000_00000000_00001011_00000000

        Console.writeLine("a = %d, b = %d", a, b);
        var strA = Integer.toString(a);
        var strAUInt = Integer.toUnsignedString(a);
        var strB = Integer.toString(b);
        var strBUInt = Integer.toUnsignedString(b);

        Console.writeLine(strA);
        Console.writeLine(strAUInt);
        Console.writeLine(strB);
        Console.writeLine(strBUInt);
    }
}
```

>`toUnsignedString` *metotlarının **radix** (sayı sistemi) parametreli overload'ları istenilen bir sistemde yazı karşılığı elde etmek için kullanılır. Radix parametreli static* `toString` *metotları 2'lik sistemde negatif bir sayının yazı karşılığını ikiye tümlenmiş değerinin başında `-` olacak şekilde verir. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        int a = 0x80000B00; // 0b10000000_00000000_00001011_00000000
        int b = 0x00000B00; // 0b00000000_00000000_00001011_00000000

        Console.writeLine("a = %d, b = %d", a, b);
        var strA = Integer.toString(a, 2);
        var strAUInt = Integer.toUnsignedString(a, 2);
        var strB = Integer.toString(b, 2);
        var strBUInt = Integer.toUnsignedString(b, 2);

        Console.writeLine(strA);
        Console.writeLine(strAUInt);
        Console.writeLine(strB);
        Console.writeLine(strBUInt);
    }
}
```

>`Integer` ve `Long` *sınıflarının* binary, hexadecimal ve octal sistemler için* `toBinaryString`, `toHexString` ve `toOctalString` *isimli metotları vardır. Bu metotlar sayının soldan itibaren ilk sıfırdan farklı olan basamak değerinden başlayacak şekilde yazıya çevirme işlemini yapar. Bu değerler 2'ye tümleme aritmetiğine göre elde edilir*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input a number:");

            Console.writeLine("a = %d", a);
            Console.writeLine("a = 0b%s", Integer.toBinaryString(a));
            Console.writeLine("a = 0x%s", Integer.toHexString(a));
            Console.writeLine("a = 0%s", Integer.toOctalString(a));

            if (a == 0)
                break;
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readLong("Input a number:");

            Console.writeLine("a = %d", a);
            Console.writeLine("a = 0b%s", Long.toBinaryString(a));
            Console.writeLine("a = 0x%s", Long.toHexString(a));
            Console.writeLine("a = 0%s", Long.toOctalString(a));

            if (a == 0)
                break;
        }
    }
}
```

>`BitwiseUtil` *sınıfının* `toBinaryStr` *metotları öncelikle aşağıdaki gibi bitsel operatörler kullanılmadan yazılacaktır. İleride bu metotlar bitsel operatörler ile güncellenecektir:*
>
>        public static String toBinaryStr(int val)
>        {
>            var str = Integer.toBinaryString(val);
>            var size = Integer.SIZE - str.length();
>
>            return size == 0 ? str : String.format("%0" + size +"d", 0) + str;
>        }
>
>        public static String toBinaryStr(long val)
>        {
>            var str = Long.toBinaryString(val);
>            var size = Long.SIZE - str.length();
>
>            return size == 0 ? str : String.format("%0" + size +"d", 0) + str;
>        }

>*Java'daki bitsel operatörler **(bitwise operators)** öncelik sırasına şu şekildedir:*
>
>       `~`                                 -------> Bitwise not
>       `<<` `>>` `>>>`                     -------> Bitwise left shift, right shift, unsigned right shift
>       `&`                                 -------> Bitwise AND
>       `^`                                 -------> Bitwise XOR
>       `|`                                 -------> Bitwise OR
>       `>>=` `<<=` `>>>=` `&=` `^=` `|=`   -------> Bitwise compound assignment operators
>
>*Bitsel operatörlerin gerçek sayı türleri ile kullanımı error oluşturur. Özel bazı durumlar dışında* `boolean` *türü ile de kullanılamazlar. Yani bu operatörler genel olarak tamsayı türleri için kullanılabilirler*

>**_Anahtar Notlar:_** Bir bitin `1` yapılmasına `set`, `0` yapılmasına ise `reset/clear` denilmektedir.

>`~` *operatörü* `unary` ve `prefix` *durumda bir operatördür. Bu operatör operandına ilişkim ifadenin değeri olan tamsayının bitleri üzerinde 1'e tümleme (one's complement) işlemi yaparak değer üretir. Yani operandı olan tamsayının 1 olan bitlerini sıfır, sıfır olan bitlerini de 1 yapar.* `~` *operatörünün yan etkisi **(side effect)** yoktur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var val = Console.readInt("Input a number:");
            var result = ~val;

            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            Console.writeLine(BitwiseUtil.toBinaryStr(result));

            if (val == 0)
                break;
        }
    }
}
```

>*Tüm bitsel kaydırma operatörleri* `binary` ve `infix` *durumda operatörlerdir. Bu operatörlerin yan etkisi yoktur. Bu operatörler aritmetik operatörlerden düşük, karşılaştırma operatörlerinden yüksek önceliklidir. Bu operatörlerin ikinci operandlarının negatif olması durumu ayrıca incelenecektir.*

>`<<` *operatörü birinci operandına ilişkin tamsayının bitlerini, ikinci operandının değeri kadar pozisyon sola kaydırır ve elde edilen değeri üretir. Sınır dışında kalan bitler sıfır ile beslenir. Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        int val = 1;

        while (val != 0) {
            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            val <<= 1;
        }
    }
}
```

>*Sağa kaydırma işlemi için iki tane operatör vardır:* `>>`, `>>>`

>`>>` *operatörü birinci operandına ilişkin tamsayının bitlerini, ikinci operandının değeri kadar pozisyon sağa kaydırır ve elde edilen değeri üretir. Bu operatör taşan bitler için sayı negatifse 1, pozitif ise sıfır değeri ile besleme yapar*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var val = Console.readInt("Input a number:");
            var n = Console.readUInt("Input shift value:");

            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            Console.writeLine(BitwiseUtil.toBinaryStr(val >> n));

            if (val == 0)
                break;
        }
    }
}
```

>`>>>` *operatörü birinci operandına ilişkin tamsayının bitlerini, ikinci operandının değeri kadar pozisyon sağa kaydırır ve elde edilen değeri üretir. Bu operatör taşan bitler için sıfır ile besleme yapar*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var val = Console.readInt("Input a number:");
            var n = Console.readUInt("Input shift value:");

            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            Console.writeLine(BitwiseUtil.toBinaryStr(val >>> n));

            if (val == 0)
                break;
        }
    }
}
```

>*Bir tamsayıyı sola 1 kaydırmak o sayıyı 2 değeri ile çarpmak demektir, benzer şekilde sağa 1 kaydırmak ise sayıyı 2 değerine bölmek demektir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var val = Console.readInt("Input a number:");

            Console.writeLine("%s -> %d", BitwiseUtil.toBinaryStr(val), val);
            Console.writeLine("%s -> %d", BitwiseUtil.toBinaryStr(val << 1), val << 1);
            Console.writeLine("%s -> %d", BitwiseUtil.toBinaryStr(val >> 1), val >> 1);

            if (val == 0)
                break;
        }
    }
}
```

>**_Soru:_** En yüksek anlamlı biti **(the most significant bit)** 1, diğer tüm bitleri sıfır olan 32 bitlik bir sayıyı bitsel operatörleri ve sabitleri kullanarak elde ediniz.
>
>       Çözüm: ~(~0 >>> 1)

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        Console.writeLine(BitwiseUtil.toBinaryStr(~0));
        Console.writeLine(BitwiseUtil.toBinaryStr(~0 >>> 1));
        Console.writeLine(BitwiseUtil.toBinaryStr(~(~0 >>> 1)));
    }
}
```

>**_Soru:_** En yüksek anlamlı biti **(the most significant bit)** 1, diğer tüm bitleri sıfır olan 64 bitlik bir sayıyı bitsel operatörleri ve sabitleri kullanarak elde ediniz.
>
>       Çözüm: ~(~0L >>> 1)*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        Console.writeLine(BitwiseUtil.toBinaryStr(~0L));
        Console.writeLine(BitwiseUtil.toBinaryStr(~0L >>> 1));
        Console.writeLine(BitwiseUtil.toBinaryStr(~(~0L >>> 1)));
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        int val = ~(~0 >>> 1);

        while (val != 0) {
            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            val >>>= 1;
        }
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        long val = ~(~0L >>> 1);

        while (val != 0) {
            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            val >>>= 1;
        }
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        long val = ~(~0 >>> 1);

        while (val != 0) {
            Console.writeLine(BitwiseUtil.toBinaryStr(val));
            val >>>= 1;
        }
    }
}
```

>*Anımsanacağı gibi* `&` ve `|` *operatörleri* `boolean` *türü ile kullanıldığında kısa devre davranışı **(short circuit behavior)** olmayan* `&&` ve `||` *operatörleri gibi çalışırlar*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var result = Sample.foo() | Sample.bar() & Sample.tar();

        Console.writeLine("result = %b", result);
    }
}

class Sample {
    public static boolean foo()
    {
        Console.writeLine("foo");

        return true;
    }

    public static boolean bar()
    {
        Console.writeLine("bar");

        return false;
    }

    public static boolean tar()
    {
        Console.writeLine("tar");

        return true;
    }
}
```

>`&`, `^` ve `|` *operatörleri iki operandlı araek durumunda operatörlerdir. Operatörlerin yan etkisi yoktur. Bu operatörler iki tamsayının bitlerini karşılıklı olarak sırasıyla* `AND`, `XOR` ve `OR` *işlemine sokarlar ve elde edilen değeri üretirler. Bu operatörler **left associative**'dir.*

>`&` *operatöründe 1 etkisiz elemandır, sıfır ise yutan elemandır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input first number:");
            var b = Console.readInt("Input second number:");


            Console.writeLine(BitwiseUtil.toBinaryStr(a));
            Console.writeLine(BitwiseUtil.toBinaryStr(b));
            Console.writeLine(BitwiseUtil.toBinaryStr(a & b));

            if (a == 0 && b == 0)
                break;
        }
    }
}
```

>`|` *operatöründe sıfır etkisiz elemandır, 1 ise yutan elemandır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input first number:");
            var b = Console.readInt("Input second number:");


            Console.writeLine(BitwiseUtil.toBinaryStr(a));
            Console.writeLine(BitwiseUtil.toBinaryStr(b));
            Console.writeLine(BitwiseUtil.toBinaryStr(a | b));

            if (a == 0 && b == 0)
                break;
        }
    }
}
```

>`XOR` **(Exclusive OR)** *operatörünün doğruluk tablosu (truth table) aşağıdaki gibidir:*

| a | b | a ^ b |
|---|---|-------|
|1  | 1 |    0  |
|1  | 0 |    1  |
|0  | 1 |    1  |
|0  | 0 |    0  |

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input first number:");
            var b = Console.readInt("Input second number:");

            Console.writeLine(BitwiseUtil.toBinaryStr(a));
            Console.writeLine(BitwiseUtil.toBinaryStr(b));
            Console.writeLine(BitwiseUtil.toBinaryStr(a ^ b));

            if (a == 0 && b == 0)
                break;
        }
    }
}
```

>*Bir tamsayı arka arkaya aynı değerler ile `xor` işlemine sokulursa tamsayının kendisi elde edilir. Bu özelliği dolayısıyla bu operatör neredeyse tüm şifreleme algoritmalarında bir şekilde kullanılmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input first number:");
            var b = Console.readInt("Input second number:");

            Console.writeLine(BitwiseUtil.toBinaryStr(a));
            Console.writeLine(BitwiseUtil.toBinaryStr(b));
            Console.writeLine(BitwiseUtil.toBinaryStr(a ^ b ^ b));
            Console.writeLine(BitwiseUtil.toBinaryStr(b ^ a ^ a));

            if (a == 0 && b == 0)
                break;
        }
    }
}
```

>*Bir tamsayı kendisi ile `xor` işlemine sokulduğunda sıfır değeri elde edilir değil mi?*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = Console.readInt("Input a number:");

            Console.writeLine(BitwiseUtil.toBinaryStr(a));
            Console.writeLine(BitwiseUtil.toBinaryStr(a ^ a));

            if (a == 0)
                break;
        }
    }
}
```

>*Aşağıdaki örnekte çok basit bir şifreleme işlemi yapılmıştır. Şifreleme algoritmasına değil `xor`'un kullanımına odaklanınız*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var str = Console.read("Input a text:");
            var n = Console.readInt("Input a number:");
            var s = CryptoUtil.encryptDecrypt(str, n);

            Console.writeLine(s);
            Console.writeLine(CryptoUtil.encryptDecrypt(s, n));

            if (str.equals("quit"))
                break;
        }
    }
}

class CryptoUtil {
    public static String encryptDecrypt(String str, int n)
    {
        var sb = new StringBuilder();
        var len = str.length();

        for (var i = 0; i < len; ++i)
            sb.append((char)(str.charAt(i) ^ n));

        return sb.toString();
    }
}
```

>`^` *operatörü* `boolean` *türü ile de kullanılabilir. Şüphesiz kısa devre davranışı olmaz. Zaten `xor` işleminin kısa devre davranışı olması beklenmez*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var result = Sample.foo() ^ Sample.bar();

        Console.writeLine("result = %b", result);
    }
}

class Sample {
    public static boolean foo()
    {
        Console.writeLine("foo");

        return true;
    }

    public static boolean bar()
    {
        Console.writeLine("bar");

        return false;
    }
}
```

>*Özellikle aşağı seviyeli bazı kodlarda bir tamsayının bitleri üzerinde işlemler yapılması gerekli olabilmektedir. Buna **bitwise manipulation** denilmektedir.*
>
>**_Anahtar Notlar:_** Bir işlemin birden fazla algoritması söz konusu olduğunda, hızlı olan algoritmaya her zaman ihtiyaç olmayabilir. Bazı durumlarda programcılar kodun okunabilirliğini/algılanabilirliğini görece hızlı olmasına tercih edebilirler. Programcı böylesi durumlarda kararını verirken dikkatli olmalıdır. Yani "her zaman hızlı olsun" ya da "yavaş da olsa okunabilir olsun" yaklaşımları her zaman geçerli değildir. Bunun kararı algoritmaya ve kullanımına bağlıdır. Bu cümlelerde "hızlı algoritmalar okunabilir değildir" ya da "yavaş olan algoritmalar okunabilirdir" gibi anlamlar da şüphesiz çıkartılmamalıdır. Şüphesiz bu da algoritmaya bağlıdır.

>*Bir tamsayının bitleri üzerinde işlem yapılırken işleme göre başka bir sayı ile bitsel operatörler kullanılabilir. Bitsel işleme sokulan bu tarz sayılara genel olarak **"bit maskesi (bitmask)"** denir.*

>*Bitsel işlemlerde **"n.bit (n-th bit)"** dendiğinde genel olarak **sağdan** itibaren `n` numaralı indeksteki bit anlaşılır. Indeks değeri sıfırdan başlar*

>**Bir tamsayının belirli bir bitinin `set` edilmesi:**
>
>*Bir tamsayının belirli bir bitinin set edilmesi için sayı, ilgili biti 1 olan diğer tüm bitleri sıfır olan bir sayı (bitmask) ile* `|` *işlemine sokulur. Çünkü* `|` *işleminde 1 yutan, sıfır ise etkisiz elemandır.*
>
>*Aşağıdaki örnekte sayının* `4.` *biti* `1` *yapılmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        int a = 0x00_00_00_41;  //0100_0001
        int mask = 0x00_00_00_10; //0001_0000

        Console.writeLine(BitwiseUtil.toBitsStr(a));
        Console.writeLine(BitwiseUtil.toBitsStr(mask));

        a |= mask;
        Console.writeLine(BitwiseUtil.toBitsStr(a));
    }
}
```

>`BitwiseUtil` *sınıfının* `setBit` *metotlarını inceleyiniz*

>**Bir tamsayının belirli bir bitinin `reset/clear` edilmesi:**
>
>*Bir tamsayının belirli bir bitinin **reset/clear** edilmesi için sayı, ilgili biti sıfır olan diğer tüm bitleri 1 olan bir sayı (bitmask) ile* `&` *işlemine sokulur. Çünkü* `&` *işleminde sıfır yutan, 1 ise etkisiz elemandır.*
>
>*Aşağıdaki örnekte sayının* `4.` *biti sıfır yapılmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        int a = 0x00_00_00_51;  //0101_0001
        int mask = ~0x00_00_00_10; //1110_1111

        Console.writeLine(BitwiseUtil.toBitsStr(a));
        Console.writeLine(BitwiseUtil.toBitsStr(mask));

        a &= mask;
        Console.writeLine(BitwiseUtil.toBitsStr(a));
    }
}
```

>`BitwiseUtil` *sınıfının* `clearBit` *metotlarını inceleyiniz*

>**Bir tamsayının belirli bir bitinin değiştirilmesi:**
>
>*Bu işleme **toggle/flip** denilmektedir. Bunun için `xor` operatörü kullanılır. Bir sayının n-inci bitini değiştirmek için n-inci biti 1 olan diğer tüm bitleri sıfır olan bir sayı (bitmask) ile `xor` işlemine sokulur. Çünkü `xor` işleminde aynı değerler için sıfır, farklı değerler için 1 elde edilir. Bu durumda n-inci bit 1 ise sıfırlanır, sıfır ise 1 yapılmış olur. Bit maskesinin diğer tüm bitleri sıfır olduğundan, sayının diğer bitlerinde değişiklik olmaz*

>`BitwiseUtil` *sınıfının* `toggleBit` *metotlarını inceleyiniz*

>**Bir tamsayının belirli bir bit değerinin elde edilmesi:**
>
>*Bu işlem için ilgili biti 1 olan diğer tüm bitleri sıfır olan bir bit maskesi ile sayı* `&` *işlemine sokulur. Bu durumda elde edilen değer sıfır ise ilgili bit sıfır, sıfır dışı bir değer ise ilgili bit 1 olarak elde edilmiş olur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {

        while (true) {
            var val = Console.readInt("Input a number:");
            var n = Console.readUInt("Input n:");

            Console.writeLine(BitwiseUtil.toBitsStr(val));
            Console.writeLine(BitwiseUtil.toBitsStr(val & 1 << n));
            Console.writeLine((val & 1 << n) != 0 ? "One" : "Zero");

            if (val == 0)
                break;
        }
    }
}
```

>`BitwiseUtil` *sınıfının* `isSet` ve `isReset/isClear` *metotlarını inceleyiniz*

>**_Soru:_** Bir sayının çift olup olmadığı `mod` operatörü kullanmadan kullanılarak nasıl anlaşılır?

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {

        while (true) {
            var val = Console.readInt("Input a number:");

            Console.writeLine(BitwiseUtil.toBitsStr(val));
            Console.writeLine(BitwiseUtil.toBitsStr(val & 1));
            Console.writeLine((val & 1) == 1 ? "Odd" : "Even");

            if (val == 0)
                break;
        }
    }
}
```

>**_Soru:_** Bir sayının ikinin kuvveti olup olmadığı bitsel operatörler kullanarak nasıl anlaşılır?
>
>`NumberUtil` *sınıfının* `isPowerOfTwo` *metodunu inceleyiniz*

>**_Soru:_** Bir tamsayının kaç tane bitinin 1 olduğunu döndüren `setBitsCount` ve kaç tane bitinin sıfır olduğunu döndüren `clearBitsCount/resetBitsCount` metotlarını `BitwiseUtil` sınıfı içerisinde yazınız.

>**_Soru:_** Bir tamsayının 1 olan bitlerinin indeks numaralarını `int` türden bir dizi olarak döndüren `indicesOfSetBits` isimli metodu `BitwiseUtil` sınıfı içerisinde yazınız

>**_Soru:_** Bir tamsayının en küçük indeks numarasındaki 1 olan bitin indeks numarasına geri dönen `lowestSetBitIndex` ve en büyük indeks numarasındaki 1 olan bitin indeks numarasına geri dönen `highestSetBitIndex` metotlarını `BitwiseUtil` sınıfı içerisinde yazınız. Metotlar hiç 1 olan bit yoksa -1 değerine geri dönecektir

>*Programlamada tarih-zaman işlemleri önemlidir. JavaSE'de de bu anlamda hazır türler bulunmaktadır. Tarihsel süreç içerisinde JavaSE'de tarih-zamana ilişkin türlerde değişiklikler yapılmış hatta yeni türler eklenmiştir. Aslında JavaSE'de tarih-zaman işlemleri* `Java 8`*'e kadar görece yanlış bazı tasarımlar içeriyordu. Hatta Java'nın ilk yıllarında eklenen tarih-zaman sınıfları bir takım işlemleri doğru olarak yapamıyorlardı. Bu anlamda bu sınıfların pek çok elemanı şu an* `deprecated` *durumdadır. Bu sınıflardan sonra eklenen tarih-zamana ilişkin sınıflar işlemleri doğru olarak yapsalar da yaklaşım açısından problemlidir. Bu anlamda bu sınıfların elemanları* `deprecated` *olmamıştır. Ancak* `Java 8` *ile birlikte artık kullanımı gerekmemekte, hatta tavsiye edilmemektedir.* `Java 8` *ile birlikte eklenen tarih-zaman türleri ile tarih-zaman işlemleri hem doğru hem de daha etkin bir biçimde yapılır duruma gelmiştir. Buna göre bir java programcısı için* `Java 8+` *ile geliştirilen bir projede (ya da kütüphanede) yeni nesil sınıfların kullanılması tercih edilmelidir. Programlamda tarih-zaman türlerine ilişkin genel olarak iki yaklaşım söz konusudur:*
>
>- Tarih, zaman ve tarih-zaman olarak türleri **ayırmak**
>- Tarih, zaman ve tarih-zaman olarak türleri **ayırmamak**
>
>*Bunlar birer yaklaşımdır ve iyi ya da kötü anlamda düşünülmemelidir. Dile ve teknolojiye göre programcı öğrenir ve ona göre kullanır.*
>
>`Java 1.0` *ile eklenen tarih-zaman işlemlerini yapan* `java.util.Date` *sınıfının pek çok elemanı* `Java 1.1` *ile birlikte* `deprecated` *olmuştur. Aslında bu sınıfın deprecated olan metotları Java1.1 ile birlikte doğru çalışmamaktadır. Java 1.1 ile birlikte* `Calendar` *ve ondan türetilmiş olan* `GregorianCalendar` *isimli sınıflar tarih-zaman işlemlerinde kullanılmak üzere eklenmiştir. Bu sınıfların genel olarak tasarımları kötüdür. Bu 3 sınıf* (`Date`, `Calendar` ve `GregorianCalendar`) *tarih ve zaman kavramlarını ayırmaz. Yani tarih-zaman bir arada tutulur. Yalnızca tarih veya yalnızca zaman için programcı bu sınıfları ona göre kullanır.* `Java 8` *ile eklenen yeni nesil tarih-zaman türlerinde tarih, zaman ve tarih-zaman kavramları ayrı türler olarak temsil edilmiştir. Java 8 ile eklenen tarih-zaman sınıfları immutable'dır.* `Date` ve `Calendar` *(dolayısıyla* `GregorianCalendar`*) sınıfları **immutable** değildir.*

>`java.util.Date` *sınıfının default ctor'u ile nesne yaratıldığında o an çalışılan sistemin tarih zaman bilgisi elde edilir. Bu sınıfın **getter** ve **setter** metotları deprecated'dır. Bu sınıfın* `toString` *metodu tarih zaman bilgisine ilişkin belirli bir formatta yazıya geri döner. Bu formatın detayları gerekirse dokümandan öğrenilebilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Date;

class Application {
    public static void run(String[] args)
    {
        var now = new Date();

        Console.writeLine(now);
    }
}
```

>`Calendar` *ve dolayısıyla* `GregorianCalendar` *sınıfının* `getInstance` *static (factory) metodu ile sistemin o anki tarih-zaman bilgisi elde edilebilir.* `getInstance` *metodunun çeşitli overload'ları vardır. Bu overload'ların parametre değişkenlerinin anlamları ele alınmayacaktır. Bu değişkenlerin anlamları* `Java 8` *ile eklenen sınıflarda ayrıca detaylandırılacaktır. Yine* `Calendar` *sınıfının* `toString` *metodu ile tarih-zaman bilgisi belirli formatta elde edilebilir. Dokümantasyona göre* `toString` *metodunun implementasyona göre değişiklik gösterebilmektedir.* `toString` *metodu ile elde edilen yazı daha çok **debug** amaçlı kullanım için tasarlanmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Calendar;

class Application {
    public static void run(String[] args)
    {
        var now = Calendar.getInstance();

        Console.writeLine(now);
    }
}
```

>`GregorianCalendar` *sınıfının default ctor'u ile sistemin o anki tarih zaman bilgisine ilişkin nesne yaratılabilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.GregorianCalendar;

class Application {
    public static void run(String[] args)
    {
        var now = new GregorianCalendar();

        Console.writeLine(now);
    }
}
```

>`Calendar` *sınıfının **getter/accessor** ve **setter/mutator** elemanları (metotları) alışık olduğumuz şekilde her bileşen için ayrı olarak tasarlanmamıştır. set ve get metotları ilgili eleman ile işlem yapmak için o elemana **(field)** karşılık* `int` *türden değeri parametre olarak alırlar. Bu değerler* `public static final` *olarak* `Calendar` *sınıfında bildirilmiştir. Yani buradaki problem bu değerlerin kaç olduğunun anımsanması değil, bu metotların tasarımıdır. NYPT açısından bu tasarım çok kullanışlı değildir.* `Calendar` *sınıfı için ay bilgisi* `[0, 11]` *aralığındadır.* 
>
>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

class Application {
    public static void run(String[] args)
    {
        var now = new GregorianCalendar();

        Console.writeLine("%02d/%02d/%04d %02d:%02d:%02d.%03d %s",
                now.get(DAY_OF_MONTH), now.get(MONTH) + 1, now.get(YEAR),
                now.get(HOUR), now.get(MINUTE), now.get(SECOND), now.get(MILLISECOND),
                now.get(AM_PM) == AM ? "AM" : "PM");

        Console.writeLine("%02d/%02d/%04d %02d:%02d:%02d.%03d",
                now.get(DAY_OF_MONTH), now.get(MONTH) + 1, now.get(YEAR),
                now.get(HOUR_OF_DAY), now.get(MINUTE), now.get(SECOND), now.get(MILLISECOND));

        now.set(DAY_OF_MONTH, 12);

        Console.writeLine("%02d/%02d/%04d %02d:%02d:%02d.%03d %s",
                now.get(DAY_OF_MONTH), now.get(MONTH) + 1, now.get(YEAR),
                now.get(HOUR), now.get(MINUTE), now.get(SECOND), now.get(MILLISECOND),
                now.get(AM_PM) == AM ? "AM" : "PM");

    }
}
```

>`GregorianCalendar` *sınıfının tarih-zaman bileşenlerinin veribildiği çeşitli ctor'ları vardır. Bu ctor'larda verilmeyen değerler için sıfır alınır. Yani örneğin sadece yıl, ay ve gün ile yaratılan bir nesne için zaman geceyarısını (midnight) gösterir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

class Application {
    public static void run(String[] args)
    {
        var birthDate = new GregorianCalendar(1976, SEPTEMBER, 10);

        Console.writeLine("%02d/%02d/%04d ",
                birthDate.get(DAY_OF_MONTH), birthDate.get(MONTH) + 1, birthDate.get(YEAR));
    }
}
```

>`Calendar` *sınıfının* `getTimeInMillis` *metodu **Epoch Time (01.01.1970 geceyarısı)** ile ilgili zaman arasındaki milisaniye sayısına geri döner. Bu değer* `UTC` *(Universal Time Clock) olarak elde edilir. Aşağıdaki demo örnekte yaş hesaplanmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

class Application {
    public static void run(String[] args)
    {
        var birthDate = new GregorianCalendar(1976, SEPTEMBER, 10);
        var now = new GregorianCalendar();
        var age = (now.getTimeInMillis() - birthDate.getTimeInMillis()) / (1000. * 60 * 60 * 24 * 365);

        Console.writeLine("Age:%f", age);
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Calendar;
import java.util.GregorianCalendar;

class Application {
    public static void run(String[] args)
    {
        var person = new Person("Oğuz Karan", 10, 9, 1976);

        Console.writeLine("%s, %f", person.getName(), person.getAge());
    }
}

class Person {
    private static final double DIVIDER = 1000. * 60 * 60 * 24 * 365;
    private String m_name;
    private Calendar m_birthDate;

    public Person(String name, int day, int month, int year)
    {
        m_name = name;
        m_birthDate = new GregorianCalendar(year, month - 1, day);
    }

    //...
    public String getName()
    {
        return m_name;
    }

    public double getAge()
    {
        var now = new GregorianCalendar();

        return (now.getTimeInMillis() - m_birthDate.getTimeInMillis()) / DIVIDER;
    }
}
```

>`Calendar` *(dolayısıyla* `GregorianCalendar`*) sınıfı tarih-zamana yönelik bileşenlerinin sınır değerlerini kontrol etmez. İlgili değerlere göre uygun tarihi hesaplar. Bu durumda tarih-zamanın geçersizlik durumu programcı tarafından kontrol edilmelidir. Bu da* `Calendar` *sınıfının tasarım problemlerinden biridir. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var day = Console.readInt("Input day:");
            var month = Console.readInt("Input month:");
            var year = Console.readInt("Input year:");

            var birthDate = new GregorianCalendar(year, month - 1, day);

            Console.writeLine("%02d/%02d/%04d ",
                    birthDate.get(DAY_OF_MONTH), birthDate.get(MONTH) + 1, birthDate.get(YEAR));

            if (day == 0 && month == 0 && year == 0)
                break;
        }
    }
}
```

>`Calendar` *sınfının* `before` ve `after` *metotları sırasıyla çağıran referansa ilişkin tarihin, prametresi ile aldığı tarihten önce ve sonra olup olmadığını test eder. Duruma göre* `boolean` *türden değere geri döner.*
>
>`Calendar` sınfının,
>
>       compareTo metodu r = d1.compareTo(d2) 
>çağrısı için:
>1. d1 tarihi d2 tarihinden önce geliyorsa <=> r < 0
>2. d1 tarihi d2 tarihinden sonra geliyorsa <=> r > 0
>3. d1 ile d2 aynı tarihse <=> r == 0
>
>biçimde çalışır.

>*Aşağıdaki örnekte ayın son günü bulunmuştur. Örnekte zaman farkı göz ardı edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.GregorianCalendar;

import static java.util.Calendar.*;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var month = Console.readInt("Input expiry month:");
            var year = Console.readInt("Input expiry year:");
            var expiryDate = new GregorianCalendar(year, month - 1, 1);

            expiryDate.set(DAY_OF_MONTH, expiryDate.getActualMaximum(DAY_OF_MONTH));

            if (new GregorianCalendar().after(expiryDate))
                Console.writeLine("Kartın son kullanma tarihi geçmiştir");
            else
                Console.writeLine("Kartın son kullanma tarihi geçmemiştir");
        }
    }
}
```

>*Aşağıdaki örnekte ayın son günü bulunmuştur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datetime.legacy.DateTime;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var month = Console.readInt("Input expiry month:");
            var year = Console.readInt("Input expiry year:");
            var expiryDate = DateTime.of(1, month, year);

            expiryDate = expiryDate.withDay(expiryDate.getEndOfMonth());

            if (DateTime.today().isAfter(expiryDate))
                Console.writeLine("Kartın son kullanma tarihi geçmiştir");
            else
                Console.writeLine("Kartın son kullanma tarihi geçmemiştir");
        }
    }
}
```

>`Java 8` *ile eklenen* `LocalDate`, `LocalTime` ve `LocalDateTime` *sınıflarının ctor'ları **private** olarak bildirilmiştir. Bu sınıflar ile nesne yaratmak için of **factory** metotları kullanılır.* `LocalDate` ve `LocalDateTime` *sınıfları için ay bilgisi 1 değerinden başlayacak şekilde verilir. Ayrıca yine bu sınıflarla birlikte eklenen* `Month` *enum sınıfı ile de ay bilgisi verilebilir veya elde edilebilir. Bu sınıfların ilgili tarih-zaman değerlerine ilişkin **accessor/getter** metotları ayrıdır. Bu sınıflar immutable olduğundan veri elemanlarının değiştirilmesi ile işlem yapan metotlar yeni bir nesne referansına dönerler. Bu sınıfların* `plusXXX` ve `minusXXX` *metotları ile ilgili zamana çeşitli birimlerde (gün, ay yıl, saat vb) değerler eklenebilir ya da çıkartılabilir. Yine* `withXXX` *metotları ile herhangi bileşenin değişmiş olduğu yeni nesne referansı elde edilebilir. Bu sınıflar tarih-zaman geçerlilik kontrolünü yaparlar ve herhangi bir geçersiz tarih-zaman durumunda exception oluşur. Bu sınıflar nano-saniye mertebesinde değer tutarlar. Bu sınıfların* `toString` *metotları* `ISO-8601` *standardına göre formatlı bir yazıya geri döner. Bu sınıfların* `now` *metotları çalışılan sistemin o anki tarih-zaman bilgisine ilgili nesne olarak geri döner.* `LocalDate` *sınıfının* `atTime` ve `LocalTime` *sınıfının* `atDate` *metotları ilgili bilgilerden oluşan* `LocalDateTime` *nesnesinin referansına geri dönerler.* `LocalDateTime` *sınıfının* `toLocalDate` ve `toLocalTime` *metotları ile ilgili bilgilerden oluşan sırasıyla* `LocalDate` ve `LocalTime` *referansları elde edilebilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

class Application {
    public static void run(String[] args)
    {
        var date = LocalDate.of(2024, Month.JANUARY, 14);
        var time = LocalTime.of(15, 26, 39);
        var dateTime = LocalDateTime.of(2024, Month.JANUARY, 14, 15, 26, 39);

        Console.writeLine(date);
        Console.writeLine(time);
        Console.writeLine(dateTime);
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class Application {
    public static void run(String[] args)
    {
        var date = LocalDate.now();
        var time = LocalTime.now();
        var dateTime = LocalDateTime.now();

        Console.writeLine(date);
        Console.writeLine(time);
        Console.writeLine(dateTime);
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class Application {
    public static void run(String[] args)
    {
        var date = LocalDate.now();
        var time = LocalTime.now();
        var dateTime = LocalDateTime.now();

        Console.writeLine(date);
        Console.writeLine(time);
        Console.writeLine(dateTime);
        Console.writeLine("-------------------------------------------------------------------");

        date = date.plusDays(2).plusMonths(2);
        time = time.minusHours(2).minusMinutes(4);
        dateTime = dateTime.plusWeeks(3).plusDays(4);
        Console.writeLine(date);
        Console.writeLine(time);
        Console.writeLine(dateTime);
        Console.writeLine("-------------------------------------------------------------------");
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalTime;

class Application {
    public static void run(String[] args)
    {
        var date = LocalDate.now();
        var time = LocalTime.now();
        var dateTime1 = date.atTime(time);
        var dateTime2 = time.atDate(date);

        Console.writeLine(date);
        Console.writeLine(time);
        Console.writeLine(dateTime1);
        Console.writeLine(dateTime2);
        Console.writeLine("-------------------------------------------------------------------");
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDateTime;

class Application {
    public static void run(String[] args)
    {
        var now = LocalDateTime.now();
        var date = now.toLocalDate();
        var time = now.toLocalTime();

        Console.writeLine(now);
        Console.writeLine(date);
        Console.writeLine(time);
        Console.writeLine("-------------------------------------------------------------------");
    }
}
```

>`DateTimeFormatter` *sınıfı ile formatlama işlemi yapılabilmektedir. Bu sınıfın static* `ofPattern` *metodu özel karakterlerden oluşan bir pattern verilerek formatlama belirlenebilir. Bu sınıfın format metodu ile ilgili tarih, zaman ya da tarih-zaman bilgisinden ilgili formatta yazı karşılığı elde edilebilir. Pattern'e ilişkin özel karakterler ilgili dokümantasyondan elde elde edilebilir: [Java17 DateTimeFormatter](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html) (14th January 2024 16:11)*
>
>`LocalDate`, `LocalTime` ve `LocalDateTime` *sınıflarının* `parse` *metotları ile yazıdan ilgili nesne elde edilebilir. Tek parametreli metotları* `ISO_LOCAL` *formatını kullanırlar. İki parametreli* `parse` *metotlarının ikinci parametresi* `DateTimeFormatter` *türündendir ve yazının istenilen formatta parse edilmesi sağlanabilir. parse metotları parse işlemini yapamadıkları durumda **exception** oluşur*
>
>`DateTimeFormatter` *sınıfında bazı formatlar public static ve final veri elemanları ile tanımlanmıştır. Bu elemanlar constant expression ile ilkdeğerlenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Application {
    public static void run(String[] args)
    {
        var dateTime = LocalDateTime.now();
        var date = LocalDate.now();
        var time = LocalDateTime.now();
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss");
        var dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var timeFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");

        var dateTimeStr = dateTimeFormatter.format(dateTime);
        var dateStr = dateFormatter.format(date);
        var timeStr = timeFormatter.format(time);

        Console.writeLine(dateTimeStr);
        Console.writeLine(dateStr);
        Console.writeLine(timeStr);
    }
}
```

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Application {
    public static void run(String[] args)
    {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss");
        var dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var timeFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");
        var dateTimeStr = "14/01/2024 16:17:37";
        var dateStr = "14/01/2024";
        var timeStr = "16:17:37";

        var dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        var date = LocalDate.parse(dateStr, dateFormatter);
        var time = LocalTime.parse(timeStr, timeFormatter);

        Console.writeLine(dateTime);
        Console.writeLine(date);
        Console.writeLine(time);
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Application {
    public static void run(String[] args)
    {
        var dateTime = LocalDateTime.now();
        var date = LocalDate.now();
        var time = LocalDateTime.now();
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ssa");
        var dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ssa");

        var dateTimeStr = dateTimeFormatter.format(dateTime);
        var dateStr = dateFormatter.format(date);
        var timeStr = timeFormatter.format(time);

        Console.writeLine(dateTimeStr);
        Console.writeLine(dateStr);
        Console.writeLine(timeStr);
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Application {
    public static void run(String[] args)
    {
        var dateTime = LocalDateTime.now();
        var date = LocalDate.now();
        var time = LocalDateTime.now();
        var dateTimeStr = DateTimeFormatter.ISO_DATE_TIME.format(dateTime);
        var dateStr = DateTimeFormatter.ISO_DATE.format(date);
        var timeStr = DateTimeFormatter.ISO_TIME.format(time);

        Console.writeLine(dateTimeStr);
        Console.writeLine(dateStr);
        Console.writeLine(timeStr);
    }
}
```

>`Java 8` *ile eklenen* `ChronoUnit` *isimli enum sınfı ile çeşitli birimlerde ölçümler yapılabilmektedir. Bunun için tipik olarak* `between` *metodu kullanılabilir. Bu enum sınıfın detayları ileride ele alınacaktır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

class Application {
    public static void run(String[] args)
    {
        var earthquakeDatetime = LocalDateTime.of(1999, Month.AUGUST, 17, 3, 2);
        var now = LocalDateTime.now();
        var hours = ChronoUnit.HOURS.between(earthquakeDatetime, now);
        var days = ChronoUnit.DAYS.between(earthquakeDatetime, now);
        var years = ChronoUnit.YEARS.between(earthquakeDatetime, now);
        var totalYears = ChronoUnit.DAYS.between(earthquakeDatetime, now) / 365.;

        Console.writeLine(hours);
        Console.writeLine(days);
        Console.writeLine(years);
        Console.writeLine(totalYears);
    }
}
```

>**_Sınıf Çalışması:_** `stdin`'den girilen doğum tarihi bilgisine göre aşağıdaki mesajlardan uygun olanı `stdout`'a basan basit uygulamayı yazınız.
>
>**Mesajlar:**
>1. Doğum günü geçmişse          -> Geçmiş doğum gününüz kutlu olsun. Yeni yaşınız:...
>2. Doğum günü ise               -> Doğum gününüz kutlu olsun. Yeni yaşınız:...
>3. Doğum günü henüz gelmemişse  -> Doğum gününüz şimdiden kutlu olsun. Yeni yaşınız:...
>
>Burada yaş bilgisini istediğiniz duyarlılıkta yazdırabilirsiniz.
>
>`~/src/Projects/005-BirthDateApp` uygulamasını ve içerisindeki kütüphaneleri inceleyiniz

>**_Sınıf Çalışması:_** Sonsuz döngü kullanarak tarihi ve zamanı aşağıdaki formatta gösteren basit bir digital saat uygulaması yazınız:
>
>        21 January 2024 Sunday 13:11:27
>Buradaki formatlamada ay ve gün isimleri işletim sisteminin diline göre çıkacaktır

**_Anahtar Notlar:_** Aslında bu örnek sonsuz döngü kullanıldığında efektif değildir. İleride **timer** kullanarak daha efektif biçimi yazılacaktır

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Application {
    public static void run(String[] args)
    {
        var formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy EEEE HH:mm:ss");

        while (true)
            Console.write("%s\r", formatter.format(LocalDateTime.now()));
    }
}
```

>**_Anahtar Notlar:_**
>
>**API (Application Programming Interface):**
>
>*Bir yazılım sisteminde uygulama programcısının doğrudan çağırabileceği veya erişebileceği, o sistem ile uygulama progtamcısı arasında köprü oluşturan metot, sınıf ya da çeşitli formatlardaki bilgilere denir. API oldukça elastik bir terimdir. Hangi metotlara API deneceği tartışılabilir. AncaK genel olarak uygulama programcılarının ilgili sistem üzerinde bir takım işlemleri yapabilmek için kullandıkları ve uygulama programcısını detaylardan soyutlayan sınıf, metot ya da çeşitli formatlardaki bilgilerdir. Örneğin "Java API" dendiğinde Java sınıfları ve içerisindeki metotlar anlaşılır. Benzer şekilde "Java Date Time API" dendiğinde Java 8 ile eklenen tarih-zaman işlemleri yapan türler ve metotlar anlaşılır. Yine örneğin "Windows API" dendiğinde Windows işletim sisteminde temel işemler yapmak için kullanılan fonksiyonlar anlaşılır.*
>
>*Kütüphane (library) ve framework kavramlarının sınırları tam belli değildir. Değişik kaynaklarda değişik biçimde sınırlar çizilebilmektedir. Ancak bir sistemin framework olarak tanımlanabilmesi için aşağıdaki iki özelliğin o sistemde bulunması yönünde bir eğilim vardır:*
>1. Karmaşıklığın kullanıcıya daha basit gösterilmesi ve detayların yani yük oluşturan bazı işlemlerin kullanıcının üzerinde alınması
>2. Kod akışının ele geçirilmesi ve duruma göre programcıya belli zamanlarda verilmesi **(inversion of control (ioc))**
>
>*Kütüphanelerde arka planda bir takım işlemlerin kullanıcı için yapılması ve bu anlamda akışın ele geçirilmesi gibi bir amaç yoktur. Kütüphanelerde programın akışı kullanıcıdır. Programcı isterse kütüphaneye ilişkin sınıfları kullanabilir ve metotları çağırabilir. Bu sınıflar ve metotlar faydalı işlem yaparlar. Şüphesiz pek çok framework aynı zamanda bir ya da birden fazla kütüphaneye ya da API'ye sahiptir. Bazı durumlarda o sistemin bir kütüphane mi yoksa bir framework mü olduğu konusunda tereddütler olabilir*

>*Java'da içiçe tür bildirimleri (nested types) yapılabilir. Bu anlamda örneğin bir sınıf içerisinde bir enum class bildirimi, bir sınıf içerisinde bir interface bildirimi vb. yapılabilir. İçiçe sınıf bildirimleri bu anlamda detaylıdır ve ayrı maddeler halinde ele alınacaktır. Bu anlamda içiçe sınıf bildirimleri şunlardır:*
>    - Local classes
>    - Nested classes
>    - Inner classes
>    - Anonymous classes
>    - Lambda Expression (Closure) (Since Java 8)
>
>*Herhangi bir tür içerisinde bildirilmeyen user defined type (UDT)'lara **"top level types"** denir. Bu anlamda hiç bir tür içerisinde bildirilmeyen sınıflara **"top level classes"** denilmektedir.*

>**_Soru:_** Aşağıdaki sınıfı öyle bir duruma getiriniz ki, yeni bir yetenek (ability) eklendiğinde `abilityTotal` ve `abilityAverage` metotları değiştirilmek zorunda kalmasın
>
>**_Açıklamalar:_**
>- Tüm ability değerleri `int` olarak kabul edilecektir
>- Sınıf şu şekildedir:

```java
class Fighter {
    private String m_name;
    private int m_health;
    private int m_strength;
    private int m_agility;

    public Fighter(String name)
    {
        m_name = name;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public int getHealth()
    {
        return m_health;
    }

    public void setHealth(int health)
    {
        m_health = health;
    }

    public int getStrength()
    {
        return m_strength;
    }

    public void setStrength(int strength)
    {
        m_strength = strength;
    }

    public int getAgility()
    {
        return m_agility;
    }

    public void setAgility(int agility)
    {
        m_agility = agility;
    }

    public double abilityAverage()
    {
        return abilityTotal() / 3.;
    }

    public int abilityTotal()
    {
        return m_agility + m_health + m_strength;
    }
}
```

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.array.ArrayUtil;

class Application {
    public static void run(String[] args)
    {
        Fighter f = new Fighter("Blanka");

        f.setAgility(45);
        f.setHealth(96);
        f.setStrength(56);
        f.setMana(34);
        f.setCombo(345);

        Console.writeLine("Total:%d, Average:%f", f.abilityTotal(), f.abilityAverage());
    }
}

class Fighter {
    private String m_name;
    private final int [] m_abilities;
    private enum Ability {HEALTH, AGILITY, STRENGTH, MANA, COMBO, COUNT}
    //...

    public Fighter(String name)
    {
        m_name = name;
        m_abilities = new int[Ability.COUNT.ordinal()];
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public int getHealth()
    {
        return m_abilities[Ability.HEALTH.ordinal()];
    }

    public void setHealth(int health)
    {
        m_abilities[Ability.HEALTH.ordinal()] = health;
    }

    public int getStrength()
    {
        return m_abilities[Ability.STRENGTH.ordinal()];
    }

    public void setStrength(int strength)
    {
        m_abilities[Ability.STRENGTH.ordinal()] = strength;
    }

    public int getAgility()
    {
        return m_abilities[Ability.AGILITY.ordinal()];
    }

    public void setAgility(int agility)
    {
        m_abilities[Ability.AGILITY.ordinal()] = agility;
    }

    public int getMana()
    {
        return m_abilities[Ability.MANA.ordinal()];
    }

    public void setMana(int mana)
    {
        m_abilities[Ability.MANA.ordinal()] = mana;
    }

    public int getCombo()
    {
        return m_abilities[Ability.COMBO.ordinal()];
    }

    public void setCombo(int combo)
    {
        m_abilities[Ability.COMBO.ordinal()] = combo;
    }

    public double abilityAverage()
    {
        return abilityTotal() / (double)m_abilities.length;
    }

    public int abilityTotal()
    {
        return ArrayUtil.sum(m_abilities);
    }
}
```

>**_Anahtar Notlar:_** Özellikle derleyiciler, özellikle `UDT` isimlerini kendileri belirlediklerinde isimlendirmede `$` karakterini kullanırlar. Programcı için zaten `$` karakterinin değişken isimlendirmede kullanılması iyi bir teknik olmadığından herhangi bir isim çakışması durumu oluşmaz. Zaten değişken isimlendirmede `$` karakterinin kullanılabiliyor olmasının ve programcıya da önerilmemesinin temel nedeni budur.

>*Bir blok içerisinde bildirilen sınıflara **"yerel sınıflar (local classes)"** denir. Yerel sınıflar pratikte çok karşımıza çıkmaz. Özellikle birim testi araçları yaygınlaşmadan önce çok kullanılırdı ancak günümüzde çok tercih edilmez. Yerel bir sınıf ismi bildirildiği yerden bildirildiği bloğun sonuna kadar görülebilirdir. Derleyici yerel sınıfları top level class olacak şekilde* `$` *karakteri kullanarak isimlendirir. Aşağıdaki yerel sınıflarının isimleri yukarıdan aşağıya sırayla şu şekilde belirlenmiş olur:*
>
>        Application$1Sample
>        Sample$1Mample
>        Sample$1Mample$1Test
>        Sample$2Mample
>        Sample$1Test
>*Bu isimlendirme formatının detaylarının bilinmesi çok önemli değildir. Burada önemli olan bu isimlendirmede* `$` *karakterinin kullanılması ve dolayısıyla isim çakışmasının oluşmamasıdır.*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        class Sample {
            //...
            public void foo()
            {
                //...
            }
            public static void bar()
            {
                //...
            }
        }

        var s = new Sample();

        s.foo();
        Sample.bar();
    }
}

class Sample {
    public void foo()
    {
        class Mample {
            //...
            public void func()
            {
                class Test {
                    //...
                }
            }
        }
    }

    public void bar()
    {
        class Mample {
            //...
        }
    }

    public void tar()
    {
        class Test {
            //...
        }
    }
}
```

>*Yerel bir sınıf içerisinde kendisinden önce bildirilmiş yerel değişken ve parametre değişkeni kullanılabilir. Bu kavrama **"capture"** denir. Aşağıdaki demo örnekte* `Util` *sınıfı val parametre değişkenini yakalamıştır. Yani* `isEven` *metodu içerisinde val değişkeni kullanılabilmiştir. Örnekteki* `Util` *sınıfının üretilen byte kodunun Java kodu karşılığı yaklaşık olarak şu şekildedir:*
>    
>        class Sample$1Util {
>            private final int m_val;
>
>            public Sample$1Util(int val)
>            {
>                m_val = val;
>            }
>
>            public boolean isEven()
>            {
>                return m_val % 2 == 0;
>            }
>        }
>*Sınıfın kullanımının ise yaklaşık Java kodu karşılığı da şu şekildedir:*
>
>        var util = new Sample$1Util(val);

```java
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
                return val % 2 == 0;
            }
        }

        var util = new Util();

        Console.writeLine(util.isEven() ? "Çift" : "Tek");
    }
}
```

>**_Yakalanan_** *bir yerel değişken veya parametre değişkeninin değeri faaliyet alanı **(scope)** boyunca değiştirilemez. Bu anlamda değişken **"effectively final"** olarak ele alınır. Effectively final kavramı Java 8 ile dile eklenmiştir. Bu anlamda Java 8'den önce yakalanan bir değişkenin* `final` *olarak bildirilmesi zorunluluğu vardır.*

```java
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
```

>**_Anahtar Notlar:_** Capture kavramı yalnızca yerel sınıflara özgü değildir. Anonim sınıflar ve lambda ifadeleri için de capture yapılabilir. Capture'a ilişkin yukarıda anlatılan effectively final kavramı da anonim sınıflarda ve lambda ifadelerinde de aynıdır

>**this referansı (tekrar):**
>
>*Aşağı seviyede* `non-static` *metot kavramı yoktur. Aşağı seviyede metotlar global düzeyde ele alınır. Derleyici non-static bir metot için +1 tane daha parametreli fonksiyon olacak şekilde kodu üretir. Bu +1 parametrenin türü metodun ait olduğu sınıf türünden referanstır. Teknik olarak bu referansın kaçıncı parametre olduğunun önemi yoktur ancak genel olarak ilk parametre olarak verilir. Yani aşağı seviyede non-static metotlar Java düşüncesiyle adeta* `static` *metotmuş gibi ele alınır. Non-static bir metot çağrısı aşağı seviyedeki ilgili metoda çağırmada kullanılan referans geçilerek yapılır. Non-static metot içerisinde bu **gizlice** geçirilen adrese erişmek için* `this` *referansı kullanılır.* 
>
>*Bu anlamda şu cümle doğrudur ve karşımıza çıkar:* 
>
>`non-static` *bir metoda* `this` *geçirilir.* `this` *hangi sınıf içerisindeki metotta kullanılıyorsa o sınıf türünden bir referanstır.* `this` *referansı değişken olarak kabul edilmez dolayısıyla atama işlemi **yapılamaz**.* `this` *aslında bir **constant expression'dır.*** `static` *metotlara* `this` *geçirilmez*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var s = new Sample();
        int value;

        s.setValue(10);

        //setValue çağrısının aşağı seviyedeki yaklaşık karşılığı
        Sample.setValue(s, 10);

        value = s.getValue();

        //getValue çağrısının aşağı seviyedeki yaklaşık karşılığı
        value = Sample.getValue(s);
        //...

    }
}

class Sample {
    private int m_value;

    //...

    public void setValue(int value)
    {
        //...
        this.m_value = value;
    }

    //setValue metodunun aşağı seviyedeki yaklaşık karşılığı
    public static void setValue(final Sample s, int value)
    {
        s.m_value = value;
    }

    public int getValue()
    {
        return this.m_value;
    }

    //getValue metodunun aşağı seviyedeki yaklaşık karşılığı
    public static int getValue(final Sample s)
    {
        return s.m_value;
    }
    //...
}
```

>**this referansı (tekrar):**
>
>*Aşağıdaki metotlarda* `this` *kullanımı zorunludur. Aksi durumda sınıf veri elemanı ile parametre değişkenin aynı isimde olmaması gerekir değil mi?*

```java
package org.csystem.app;

class Sample {
    private int value;

    //...

    public void setValue(int value)
    {
        //...
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    //...
}
```

>**this referansı (tekrar):**
>
>`this` *referansı tipik olarak zincir biçiminde çağrılabilen **(fluent pattern)** metotlarda geri dönüş değeri olarak kullanılır.* `StringBuilder` *sınıfının pek çok metodu* `this` *referansına geri döner.*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var s1 = new Sample();
        var s2 = new Sample();

        s1.foo().tar().bar();
        s2.bar().zar();
    }
}

class Sample {
    public Sample foo()
    {
        //...
        return this;
    }

    public Sample bar()
    {
        //...

        return this;
    }

    public Sample tar()
    {
        //...

        return this;
    }

    public Sample zar()
    {
        //...

        return this;
    }
}
```

>*Bir sınıf başka bir sınıfın içerisinde* `static` *anahtar sözcüğü ile bildirilebilir **(nested class)**. Bu bildirim ile aslında sınıfa static bir eleman **(member)** eklenmiş olur. Bu anlamda bu sınıfın erişim belirleyicisi de diğer elemanlar gibi olabilmektedir.* `static` *bir sınıf ait olduğu sınıfın* `private` *bölümüne erişebilir.* `static` *olarak bildirilmiş bir sınıf ismine sınıf dışından içerisinde bulunduğu sınıf ismi ve nokta operatörüyle erişilebilir. Bu anlamda aslında* `static` *bir elemana erişilmiştir, dolayısıyla* `static` *bir elemana erişimdeki durumların hepsi sınıf ismine erişimde de geçerlidir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var b = new A.B();
        var a = b.foo(10);

        Console.writeLine("value:%d", a.getA());
    }
}

class A {
    private int m_a;

    private A(int a)
    {
        m_a = a;
    }

    public int getA()
    {
        return m_a;
    }

    //...
    public static class B {
        //...
        public A foo(int a)
        {
            var x = new A(a);

            ++x.m_a;

            return x;
        }
    }
}
```

>*Nested bir sınıf içerisinde niteliksiz isim arama **(unqualified name lookup)** genel kurallarına aranan bir isim sınıf içerisinde (taban sınıf da dahil) bulunamazsa ait olduğu sınıf içerisinde de aranır. Bulunursa yine erişimin geçerliliği kontrol edilir. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        A.B.bar(10);
    }
}

class A {
    //...
    public static void foo(int a)
    {
        Console.writeLine("A.foo");
    }

    public static class B {
        //...
        public static void bar(int a)
        {
            Console.writeLine("B.bar");
            foo(a);
        }
    }
}
```

>*Aşağıdaki demo örnekte* `**` *ile belirtilen deyim ile niteliksiz isim arama genel kuralları dolayısıyla **recursion** oluşur ve stack taşması dolayısıyla program sonlanır*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        A.B.foo(10);
    }
}

class A {
    //...
    private static void foo(int a)
    {
        Console.writeLine("A.foo");
    }

    public static class B {
        //...
        public static void foo(int a)
        {
            Console.writeLine("B.foo");
            foo(a); //**
        }
    }
}
```

>*Aşağıdaki demo örnekte* `**` *ile belirtilen deyimde* `A` *sınıfının* `foo` *metodu nitelikli olarak çağrılmıştır. Bu durumda recursion oluşmaz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        A.B.foo(10);
    }
}

class A {
    //...
    private static void foo(int a)
    {
        Console.writeLine("A.foo");
    }

    public static class B {
        //...
        public static void foo(int a)
        {
            Console.writeLine("B.foo");
            A.foo(a); //**
        }
    }
}
```

>*Nested class'ların byte code'u üretildiğinde isimlendirmede* `$` *karakteri kullanılır.*
>
>Örneğin
>
>       class A {
>           //...
>           static class B {
>               //...
>           }
>       }
>*demo bildiriminde* `B` *sınıfı için byte code düzeyinde isimlendirme genel olarak şu şekildedir:* `A$B`

>*Aşağıdaki demo örnekte* `XML` *sınıfına ilişkin nesnenin istenilen şekilde yaratılabilmesi için karmaşayı azaltmak amacıyla nested bir sınıftan faydalanılmıştır.* `Builder` *sınıf ilgili sınıfın private bölümüne de erişebilediği için nested yapılmıştır. Örnekte tipik olarak **"builder"** kalıbı **"fluent"** olarak kullanılmıştır. Bu tarz bir tasarıma **"fluent builder"** da denilmektedir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var xml = new XML.Builder()
                .setTag("EditText")
                .setAttribute("android:hint")
                .setAttributeValue("Input your name")
                .setValue("test")
                .build();

        Console.writeLine(xml);
        //...
    }
}

class XML {
    private String m_tag;
    private String m_attribute;
    private String m_attrValue;
    private String m_value;

    //...

    public static class Builder {
        private final XML m_xml;

        public Builder()
        {
            m_xml = new XML("", "", "", "");
        }

        public Builder setTag(String tag)
        {
            m_xml.m_tag = tag;

            return this;
        }

        public Builder setAttribute(String attribute)
        {
            m_xml.m_attribute = attribute;

            return this;
        }

        public Builder setAttributeValue(String value)
        {
            m_xml.m_attrValue = value;

            return this;
        }

        public Builder setValue(String value)
        {
            m_xml.m_value = value;

            return this;
        }

        public XML build()
        {
            return m_xml;
        }

    }

    private XML(String tag, String attribute, String attValue, String value)
    {
        m_tag = tag;
        m_value = value;
        m_attribute = attribute;
        m_attrValue = attValue;
    }

    public String toString()
    {
        var sb = new StringBuilder();

        sb.append("<")
                .append(m_tag)
                .append(!m_attribute.isEmpty() ? " " + m_attribute + "=" + (m_attrValue.isEmpty() ? "\"\"" : '"' + m_attrValue + '"'): "")
                .append(">")
                .append(m_value)
                .append("</").append(m_tag).append(">");

        return sb.toString();
    }
    //...
}
```

>*Yukarıdaki örnek aşağıdaki gibi de yapılabilir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var xml = XML.createBuilder()
                .setTag("EditText")
                .setAttribute("android:hint")
                .setAttributeValue("Input your name")
                .setValue("test")
                .build();

        Console.writeLine(xml);
        //...
    }
}

class XML {
    private String m_tag;
    private String m_attribute;
    private String m_attrValue;
    private String m_value;

    //...

    public static class Builder {
        private final XML m_xml;

        private Builder()
        {
            m_xml = new XML("", "", "", "");
        }

        public Builder setTag(String tag)
        {
            m_xml.m_tag = tag;

            return this;
        }

        public Builder setAttribute(String attribute)
        {
            m_xml.m_attribute = attribute;

            return this;
        }

        public Builder setAttributeValue(String value)
        {
            m_xml.m_attrValue = value;

            return this;
        }

        public Builder setValue(String value)
        {
            m_xml.m_value = value;

            return this;
        }

        public XML build()
        {
            return m_xml;
        }
    }

    private XML(String tag, String attribute, String attValue, String value)
    {
        m_tag = tag;
        m_value = value;
        m_attribute = attribute;
        m_attrValue = attValue;
    }

    public static Builder createBuilder()
    {
        return new Builder();
    }

    public String toString()
    {
        var sb = new StringBuilder();

        sb.append("<")
                .append(m_tag)
                .append(!m_attribute.isEmpty() ? " " + m_attribute + "=" + (m_attrValue.isEmpty() ? "\"\"" : '"' + m_attrValue + '"'): "")
                .append(">")
                .append(m_value)
                .append("</").append(m_tag).append(">");

        return sb.toString();
    }
    //...
}
```

>**Garbage Collector (Hatırlatma):**
>
>*Heap'de tahsis edilen alanlara dinamik olarak tahsis edilen alanlar denir ve ilgili alanlar kullanılmaz duruma geldiğinde bunun geri bırakılması (delete/free) gerekir. Bu işlem Java'da garbage collector (GC) denilen bir mekanizma ile gerçekleştirilir. GC olmayan dillerde geri bırakma işlemi programcının sorumluluğundadır. Java'da bir nesneyi gösteren hiç bir referans kalmamışsa nesne artık garbage collected/eligible duruma gelir. GC devre girdiğinde eligible olan nesneleri geri bırakır. GC'nin ne zaman devreye gireceği yazanlara bırakılmıştır. Programcı bu anlamda GC'nin etkinliğine güvenir.*

>*Inner class'lar static anahtar sözcüğü ile bildirilmeyen içteki sınıflardır. Yani aslında non-static bir elemandır. Inner bir class türünden nesnenin yaratılabilmesi içerisinde olduğu sınıf türünden bir nesneye ihtiyaç vardır. Inner bir sınıf türünden nesne ait olduğu sınıf dışından new operatörüne ilişkin şu sentaks ile yaratılabilir:*
>
>`<Ait olunan sınıf türünden referans>.new <inner class>([argümanlar]);`
>
>*Bu sentaksta ait olunan sınıf türünden referansın gösterdiği nesnenin de şüphesiz yaratılmış olması gerekir. Ait olunan sınıf içerisinde klasik new operatörü sentaksı ile yaratılabilir çünkü bu aslında this.new anlamına gelir. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var a = new A();
        var b = a.new B();

        //...
    }
}

class A {
    //...
    public void foo()
    {
        //...

        var b = new B(); //this.new B();
    }

    public class B {
        //...
    }
}
```

>*Aşağıdaki demo örnekte bar metodu içerisinde çağrılan `foo` metodu `bar` metodunun çağrılmasında kullanılan referansın gösterdiği nesnenin referansı ile çağrılır. Yani örnekte bar metodu içerisinde `foo` metodu `a` referansı ile çağrılmış olur*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var a = new A();
        var b = a.new B();

        b.bar();

        //...
    }
}

class A {
    //...
    public void foo()
    {
        //...
    }

    public class B {
        //...
        public void bar()
        {
            foo();
        }
    }
}
```

>*Aşağıdaki demo örnekte isim arama kuralları gereği recursion oluşur ve stack taşması dolayısıyla program abnormal bir biçimde sonlanır. `B` sınıfının `foo` metodu içerisinde `A` sınıfının `foo` metodunun çağrılabilmesi için sınıf ismi kullanılamaz çünkü sınıf ismi ile ancak static metotlar çağrılabilir*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var a = new A();
        var b = a.new B();

        b.foo();

        //...
    }
}

class A {
    //...
    public void foo()
    {
        //...
        Console.writeLine("A.foo");
    }

    public class B {
        //...
        public void foo()
        {
            Console.writeLine("B.foo");

            foo();
            //A.foo(); //error
        }
    }
}
```

>*Yukarıdaki örnek için `A`'nın `foo`'su **"this expression"** sentaksı kullanılarak çağrılabilir. this expression sentaksının genel biçimi şu şekildedir:*
>
>           <Sınıf ismi>.this
>*Bu sentaksın kullanıldığı yerde ilgili sınıfa ilişkin* `this`*'in geçirilmiş olnması gerekir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var a = new A();
        var b = a.new B();

        b.foo();

        //...
    }
}

class A {
    //...
    public void foo()
    {
        Console.writeLine("A.foo");
    }

    public class B {
        //...
        public void foo()
        {
            Console.writeLine("B.foo");
            A.this.foo();
        }
    }
}
```

>*Aslında `inner class` içerisinde ait olduğu sınıf türünden bir referans veri elemanı bulunur ve nesne yaratılması sırasında bu referansa ilgili adres yani inner class'ın yaratılmasında kullanılan ait olduğu sınıf türünden nesnenin adresi geçilir. Bu durum tipik olarak inner class'ın her ctor'una +1 tane parametre ekleyerek yapılabilir. O parametrenin türü ait olduğu sınıf türünden referanstır.* 
>
>*Aşağıda* `A` *sınıf bildiriminin ve nesne yaratılmasının yaklaşık karşılığı gösterilmiştir.*
>
>*Bildirimin aşağı seviyedeki yaklaşık karşılığı:*
>
>        class A {
>            //...
>            public void foo()
>            {
>                Console.writeLine("A.foo");
>            }
>
>            public static class B {
>                private final A m_a; //A.this'e karşılık gelen veri elemanı
>                //...
>                public B(A a) //Default ctor'un yaklaşık karşılığı
>                {
>                    m_a = a;
>                }
>
>                public void foo()
>                {
>                    Console.writeLine("B.foo");
>                    m_a.foo();
>                }
>            }
>        }
>*Nesne yaratılmasının aşağı seviyedeki yaklaşık karşılığı:*
>
>        var a = new A();
>        var b = new A.B(a);

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var a = new A();
        var b = a.new B();

        b.foo();

        //...
    }
}

class A {
    //...
    public void foo()
    {
        Console.writeLine("A.foo");
    }

    public class B {
        //...
        public void foo()
        {
            Console.writeLine("B.foo");
            A.this.foo();
        }
    }
}
```

>*Aşağıdaki demo örnekte `A`'nın `foo` metodu içerisinde `A`'ya ait olan `m_b` referansına yaratılmış olan bir `B` nesnesinin adresi atanmıştır. Bu durumda `B`'nin içerisinde de içsel olarak tutulan `A` türünden bir referans da olduğundan döngü içerisinde yaratılmış olan `A` nesnelerinin hiç bir tanesi yok edilemeyecektir. Bu durumda kullanılmayan hatta erişilemeyen nesneler kalacak ve bellek sızıntısı **(memory leak)** oluşacaktır. Hatta bu program hiç sonlanmayacak bir biçimde yazıldığından belirli bir zaman sonra* `heap`*'de yer kalmayacaktır. İşte inner sınıflarda bu gibi durumlarda pogramcı dikkatli olmalıdır*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = new A();

            //...
            a.foo();
            //...
        }
    }
}

class A {
    private B m_b;
    //...
    public void foo()
    {
        Console.writeLine("A.foo");
        m_b = new B();
    }

    public class B {
        //...
        public void foo()
        {
            Console.writeLine("B.foo");
        }
    }
}
```

>*Yukarıdaki memory leak probleminin pek çok çözümü olabilmektedir. Ancak inner class kullanılarak gerçekleştirilen çözümlerinden biri aşağıdaki gibi "domain'e göre" işlem bittikten sonra ilgili `B` türünden referansın nesneden kopartılması biçiminde aşağıdaki gibi olabilir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = new A();

            //...
            a.foo();
            //...
        }
    }
}

class A {
    private B m_b;
    //...
    public void foo()
    {
        Console.writeLine("A.foo");
        m_b = new B();

        //...

        m_b = null;
    }

    public class B {
        //...
        public void foo()
        {
            Console.writeLine("B.foo");
        }
    }
}
```

>*Daha önce yazdığımız* `XML` *demo sınıfı aşağıdaki gibi inner class kullanılarak da yazılabilir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var xml = XML.createBuilder()
                .setTag("EditText")
                .setAttribute("android:hint")
                .setAttributeValue("Input your name")
                .setValue("test")
                .build();

        Console.writeLine(xml);
        //...
    }
}

class XML {
    private String m_tag;
    private String m_attribute;
    private String m_attrValue;
    private String m_value;

    //...

    public class Builder {
        private Builder()
        {
        }
        public Builder setTag(String tag)
        {
            m_tag = tag;

            return this;
        }

        public Builder setAttribute(String attribute)
        {
            m_attribute = attribute;

            return this;
        }

        public Builder setAttributeValue(String value)
        {
            m_attrValue = value;

            return this;
        }

        public Builder setValue(String value)
        {
            m_value = value;

            return this;
        }

        public XML build()
        {
            return XML.this;
        }

    }

    private XML(String tag, String attribute, String attValue, String value)
    {
        m_tag = tag;
        m_value = value;
        m_attribute = attribute;
        m_attrValue = attValue;
    }

    public static Builder createBuilder()
    {
        return new XML("", "", "", "").new Builder();
    }

    public String toString()
    {
        var sb = new StringBuilder();

        sb.append("<")
                .append(m_tag)
                .append(!m_attribute.isEmpty() ? " " + m_attribute + "=" + (m_attrValue.isEmpty() ? "\"\"" : '"' + m_attrValue + '"'): "")
                .append(">")
                .append(m_value)
                .append("</").append(m_tag).append(">");

        return sb.toString();
    }
    //...
}
```

>*Inner class'ların byte code'u üretildiğinde isimlendirmede* `$` *karakteri kullanılır.*
>
>Örneğin
>
>       class A {
>          //...
>          class B {
>                //...
>            }
>       }
>*demo bildiriminde* `B` *sınıfı için byte code düzeyinde isimlendirme genel olarak şu şekildedir:* `A$B`
>
>*Bu durumda nested class'lar ile inner clas'ların byte code düzeyindeki isimlendirme biçimi aynıdır.*

>**Polymorphism (Hatırlatma):**
>
>*NYPT'de polymorphism genel olarak ikiye ayrılır:*
>
>       run time polymorphism (RTP)
>       compile time polymorphism (CTP)
>*`Polymorphism` dendiğinde ilk olarak akla `RTP` gelir. RTP ile ilgili pek çok tanım ve betimleme yapılabilse de tüm bunlar şu üç tanıma indirginebilir:*
>1. **Biyolojik Tanım:** Taban sınıfın bir fonksiyonun türemiş sınıf içerisinde yeniden implemente edilmesidir.
>2. **Yazılım Mühendisliği Tanımı:** Türden bağımsız kod yazmaktır.
>3. **Aşağı Seviyeli Tanım:** Önceden yazılmış kodların sonradan yazılmış kodları çağırabilmesidir.
>
>*Java'da `RTP` sanal metotlar **(virtual method)** ile gerçekleştirilir. private olmayan bir metot non-static ise ve final olarak bildirilmemişse sanaldır. Sanal bir metot türemiş sınıfta aynı imza ve aynı geri dönüş değeri ile ve erişim belirleyicisi aynı olacak şekilde veya erişim anlamında yükseltilecek şekilde yazıldığında **"override"** edilmiş olur. Sanal bir metot çağrıldığında derleyici aşağıdaki şekilde kod üretir:* 
>
>*"Sanal metodun çağrılmasında kullanılan referansın dinamik türüne bak, o türe ilişkin sınıfta ilgili metot override edilmişse onu çağır, edilmemişse taban sınıfa ve dolaylı taban sınıflara bak ve ilk override edilen veya en sonunda hiç override edilmemişse static türe ilişkin sınıfın ilgili metodunu çağır".*
>
>*Bir metodun taban sınıf içerisinde gövdesi olmasının anlamlı olmadığı ancak override edildiğinde anlamlı olduğu durumlar olabilmektedir. Bu anlamda ilgili tabam sınıfın da tek başına nesne özelliği göstermesi anlamlı olmayıp ondan türetilmiş olan bir sınıf nesne olarak anlamlı olabilir. Yani aslında bir sınıf soyut **(abstract)** bir kavramı temsil edebilir. Ondan türemiş olan sınıflar somut **(concrete)** olabilir. Bu tarz sınıflara **"abstract classes"** denir ve bu sınıflar* `abstract` *anahtar anahtar sözcüğü ile bildirilir. Zorunlu olmasa da abstract bir sınıf içerisinde genel olarak abstract metotlar bulunur. Zaten en az bir tane abstract metodu olan bir sınıf da abstract olarak bildirilmek zorundadır. abstract sınıf türünden bir nesne new operatörü ile **yaratılamaz**. Ancak türemiş sınıf nesnesi içerisinde türemiş sınıf nesnesi yaratılırken yaratılır. abstract metotlar non-static olurlar ve gövdeleri olmaz ve sanaldırlar. Dolayısıyla türemiş sınıfta override edildilebilirler.*

>*Aşağıdaki demo örnekte draw işlemine ilişkin polimorfik yaklaşıma dikkat ediniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        DemoPowerPointApp.run(args);
    }
}

class DemoPowerPointApp {
    private static void fillShapes(Shape [] shapes, ShapeFactory factory)
    {
        for (var i = 0; i < shapes.length; ++i)
            shapes[i] = factory.createShape();
    }

    private static void drawShapes(Shape [] shapes)
    {
        var random = new Random();

        for (var shape : shapes)
            shape.draw(random.nextInt(0, 200), random.nextInt(0, 200));
    }

    public static void run(String[] args)
    {
        Shape [] shapes;
        ShapeFactory factory = new ShapeFactory();

        while (true) {
            var count = Console.readInt("Input shape count:");

            if (count <= 0)
                break;

            shapes = new Shape[count];
            fillShapes(shapes, factory);
            drawShapes(shapes);
        }
    }
}

class ShapeFactory {
    private final Random m_random = new Random();
    private static final ShapeColor [] SHAPE_COLORS = ShapeColor.values();

    private ShapeColor createShapeColor()
    {
        return SHAPE_COLORS[m_random.nextInt(SHAPE_COLORS.length)];
    }

    public Shape createShape()
    {
        return switch (m_random.nextInt(5)) {
            case 0 -> new Rectangle(createShapeColor(), createShapeColor());
            case 1 -> new Triangle(createShapeColor(), createShapeColor());
            case 2 -> new Ellipse(createShapeColor(), createShapeColor());
            case 3 -> new ConnectorCurve(createShapeColor());
            default -> new Line(createShapeColor());
        };
    }
}

class Rectangle extends Shape {
    //...

    public Rectangle(ShapeColor foreGroundColor, ShapeColor backGroundColor/*...*/)
    {
        super(foreGroundColor, backGroundColor);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Rectangle:%s, %s", foreGroundColor, backGroundColor);
    }
}

class Triangle extends Shape {
    //...

    public Triangle(ShapeColor foreGroundColor, ShapeColor backGroundColor/*...*/)
    {
        super(foreGroundColor, backGroundColor);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Triangle:%s, %s", foreGroundColor, backGroundColor);
    }
}

class Ellipse extends Shape {
    //...

    public Ellipse(ShapeColor foreGroundColor, ShapeColor backGroundColor/*...*/)
    {
        super(foreGroundColor, backGroundColor);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Ellipse:%s, %s", foreGroundColor, backGroundColor);
    }
}

class Line extends Shape {
    //...

    public Line(ShapeColor foreGroundColor/*...*/)
    {
        super(foreGroundColor, null);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw Line:%s", foreGroundColor);
    }
}

class ConnectorCurve extends Shape {
    //...

    public ConnectorCurve(ShapeColor foreGroundColor/*...*/)
    {
        super(foreGroundColor, null);
    }

    public void draw(int x, int y) //override
    {
        //...
        Console.writeLine("Draw ConnectorCurve:%s", foreGroundColor);
    }
}

enum ShapeColor {
    AUTO, RED, GREEN, BLUE, WHITE, BLACK
}

abstract class Shape {
    protected ShapeColor foreGroundColor;
    protected ShapeColor backGroundColor;

    //...

    protected  Shape(ShapeColor foreGroundColor, ShapeColor backGroundColor)
    {
        this.foreGroundColor = foreGroundColor;
        this.backGroundColor = backGroundColor;
    }

    public abstract void draw(int x, int y);
}
```

>**Anonim Sınıflar:** 
>
>*Programcının bildirimde isim vermediği sınıflardır. Anonim sınıf bildiriminin genel biçimi aşağıdaki gibidir:*
```
    new <UDT ismi>([parametreler]) {
        //...
    };
```
Örneğin:
```
    new Sample() {
        //...
    }
```
>*Burada* `Sample` *sınıfından türetilmiş olan bir sınıf bildirilmiştir ve o sınıf türünden nesne yaratılmıştır. Bu durumda her anonim sınıf bildirimi ayrı bir sınıf bildirimi anlamındadır. Derleyici yine anonim sınıflar için byte code'u üretirken* `$` *karakteri kullanarak isimlendirme yapar. Aşağıdaki demo örneği çalılştırıp **dinamik** türleri gözlemleyiniz*

```java
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
```

>*Anonim sınıflar da kendisinden önce bildirilen yerel değişkenleri ve parametre değişkenlerini* `effectively final` *olarak yakalar (capture)*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var value = Console.readInt("Input a value:");

        Sample s1 = new Sample() {
            public void foo()
            {
                Console.writeLine("Sample 1:%d", value);
            }
        };

        Sample s2 = new Sample() {
            public void foo()
            {
                Console.writeLine("Sample 2:%d", value);
            }
        };

        s1.foo();
        s2.foo();
    }
}

abstract class Sample {
    public abstract void foo();
}
```

>*Bir metot özellikle **soyut** bir türden referansa sahipse o metot genel olarak sanal bir metodu içerisinde çağırıyordur. Bu durumda ilgili metot aslında çağıranın nasıl yaptığını bilmeden yalnızca neyi çağıracağına göre yazılır. Böylesi metotların aldıkları bu tarz parametrelere* `callable/callback` *denir. Yani aslında metot mantıksal olarak, içerisinde çağırdığı metodu ya da metotları dışarıdan almış olur. Yapacağı işim detayını dışarıdan alan bu tarz metotlar için "high order method" ya da "vip method" gibi kavramlar söylenir. Aşağıdaki basit örnekte aslında* `doWork` *metodu **callback** alan bir metottur.*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var value = Console.readInt("Input a value:");

        MyClass.doWork(SampleFactory.createSample("Sample 1", value));
        MyClass.doWork(SampleFactory.createSample("Sample 2", value));
    }
}

class SampleFactory {
    public static Sample createSample(String message, int value)
    {
        return new Sample() {
            public void foo()
            {
                Console.writeLine("%s:%d", message, value);
            }
        };
    }
}

class MyClass {
    public static void doWork(Sample s)
    {
        //...
        s.foo();
    }
}
abstract class Sample {
    public abstract void foo();
}
```

>*JavaSE'de periyodik işlemler yapmak için tipik olarak* `Timer` *sınıfı kullanılabilir. Bu sınıf normal akışı etkilemeden yani **asenkron** bir biçimde belirlenen zamanda bir işin yapılmasını sağlar. Bunun için* `TimerTask` *abstract sınıfının* `run` *metodu override edilir. TimerTask referansını callback olarak alan çeşitli metotlar ile timer işlemi başlatılır. Timer'ı durdurmak için* `Timer` *sınıfının* `cancel` *metodu kullanılır. Timer başlatmak için önce bir Timer nesnesi yaratılır. Biz burada Timer sınıfının default ctor'u ile nesne yaratacağız. Diğer 3 ctor burada ele alınmayacaktır. Timer başlatmak için ise* `scheduleXXX` *metotları çağrılır. Bu metotların aralarında bazı farklar vardır. İleride bu farklar alınacaktır. Burada* `scheduleAtFixedRate` *metodunu kullanacağız. Bir timer nesnesi ile yalnızca bir kez schedule yapılabilir yani bir kez timer başlatılabilir.*

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

class Application {
    public static void run(String[] args)
    {
        var timer = new Timer();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run()
            {
                Console.write("%s\r", formatter.format(LocalDateTime.now()));
            }
        }, 0, 1000);
    }
}
```

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

class Application {
    public static void run(String[] args)
    {
        var timer = new Timer();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        timer.scheduleAtFixedRate(new DateTimeTask(formatter), 0, 1000);
    }
}

class DateTimeTask extends TimerTask {
    private final DateTimeFormatter m_dateTimeFormatter;

    public DateTimeTask(DateTimeFormatter dateTimeFormatter)
    {
        m_dateTimeFormatter = dateTimeFormatter;
    }

    public void run()
    {
        Console.write("%s\r", m_dateTimeFormatter.format(LocalDateTime.now()));
    }
}
```

>`TimeUnit` *enum sınıfı zaman birimini temsil eder. Sınıfın pek çok yararlı metodu vardır.* `convert` *metodu istenilen bir birime dönüşüm yapılabilmektedir. convert metodu daha genel bir metottur. Ayrıca ilgili birimlere dönüşüm için kullanılan* `toXXX` *metotları da vardır.*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.concurrent.TimeUnit;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var days = Console.readLong("Input days left:");

            if (days < 0)
                break;

            var hours = TimeUnit.HOURS.convert(days, TimeUnit.DAYS);

            Console.writeLine("Hours:%d", hours);
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.concurrent.TimeUnit;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var days = Console.readLong("Input days left:");

            if (days < 0)
                break;

            var hours = TimeUnit.DAYS.toHours(days);

            Console.writeLine("Hours:%d", hours);
        }
    }
}
```

>**_Sınıf Çalışması:_** Aşağıdaki `Alarm` isimli sınıfı `ScheduleLib` içerisinde sınıfın public bölümünü değiştirmeden yazınız:

```java
package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimerTask;

public class Alarm {
    public Alarm(LocalTime time)
    {

    }

    public Alarm(LocalDateTime dateTime)
    {

    }

    public Alarm(LocalDate date)
    {

    }

    public void start(TimerTask task)
    {

    }

    public void cancel()
    {

    }
}
```

>**_Açıklamalar:_**
>- Sınıfın `LocalTime` parametreli ctor'u aldığı zaman geldiğinde `start` metodu ile verilen **callback**'e ilişkin metod çağıracaktır.
>- Sınıfın `LocalDateTime` parametreli ctor'u aldığı tarih-zaman geldiğinde `start` metodu ile verilen **callback**'e ilişkin metodu çağıracaktır.
>- Sınıfın `LocalDate` parametreli ctor'u aldığı tarih geldiğinde (geceyarısı) geldiğinde `start` metodu ile verilen **callback**'e ilişkin metodu çağıracaktır.
>- Sınıf tarihin, zamanın ya da tarih zamanın geçmiş olup olmadığını kontrol etmeyecektir.
>- Sınıfı `Timer` kullanarak yazınız
>- Bir `Alarm` nesnesinden **yalnızca bir kez** yapılabilecektir

```java
/*-------------------------------------------------------------
    FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private final LocalDateTime m_dateTime;
    private final Timer m_timer;

    private TimerTask createTimerTask(TimerTask timerTask)
    {
        return new TimerTask() {
            public void run()
            {
                if (LocalDateTime.now().isBefore(m_dateTime))
                    return;

                timerTask.run();
                m_timer.cancel();
            }
        };
    }

    public Alarm(LocalTime time)
    {
        this(time.atDate(LocalDate.now()));
    }

    public Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public Alarm(LocalDate date)
    {
        this(date.atTime(0, 0));
    }

    public void start(TimerTask task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), 0, 1000);
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
```

>**_Sınıf Çalışması:_** Aşağıdaki geri sayım işlemini yapaan `CountDownScheduler` isimli sınıfı public bölümünü değiştirmeden `SchedulerLib` içerisinde yazınız

```java
package org.csystem.scheduler;

public abstract class CountDownScheduler {
    protected CountDownTimer(long durationInFuture, long countDownInterval, TimeUnit timeUnit)
    {

    }

    protected CountDownTimer(long millisInFuture, long countDownInterval)
    {

    }

    public abstract void onTick(long remainingMilliseconds);
    public abstract void onFinish();

    public final void start()
    {

    }

    public final void cancel()
    {

    }
}
```

>**_Açıklamalar:_**
>- Sınıf ctor'ları ilgili parametrelere göre geri sayımın her adımında `onTick` metodunu çağıracaktır. Geri sayım tamamlandığında ise onFinish metodu çağrılacaktır. `onTick` metoduna kalan milisaniye sayısı argüman olarak geçilecektir.
>
>Örnek bir kullanım şu şekilde olabilir:
>
>        new CountDownScheduler(10000, 1000) {
>            public void onTick(long ms)
>            {
>                //Her saniyede bir çağrılacak ve kalan zaman (milisaniye cinsinden) argüman olarak geçilmiş olacak
>            }
>
>            public void onFinish()
>            {
>                //10 saniye sonunda yani geri sayım tamamlandığında çağrılacak
>            }
>        }.start();
>
>Örnek saniye cinsinden şu şekilde de kullanılabilir:
>
>        new CountDownScheduler(10, 1, TimeUnit.SECONDS) {
>            public void onTick(long ms)
>            {
>                //Her saniyede bir çağrılacak ve kalan zaman (milisaniye cinsinden) argüman olarak geçilmiş olacak
>            }
>
>            public void onFinish()
>            {
>                //10 saniye sonunda yani geri sayım tamamlandığında çağrılacak
>            }
>        }.start();
>
>- Sınıfı `Timer` sınıfını kullanarak yazınız
>- Sınıfı bir `CountDownTimer` nesnesi ile **yalnızca bir kez** geri sayım yapacak şekilde yazınız

>**_Sınıf Çalışması:_** Aşağıdaki gibi kullanılan ve `CountDownScheduler` sınıfından türetilen `CountDownSchedulerEx` isimli abstract sınıfı yazınız
>
>**_Açıklamalar:_**
>- Sınıfın `onStart` isimli metodu timer başlatıldığında çağrılacaktır

>*Alarm sınıfının aşağıdaki versiyonunu inceleyiniz*

```java
/*-------------------------------------------------------------
    FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private LocalDateTime m_dateTime;
    private Timer m_timer;
    private boolean m_repeat;

    private TimerTask createTimerTask(TimerTask timerTask)
    {
        return new TimerTask() {
            public void run()
            {
                if (LocalDateTime.now().isBefore(m_dateTime))
                    return;

                timerTask.run();
                m_timer.cancel();
                if (m_repeat) {
                    m_timer = new Timer();
                    m_dateTime = m_dateTime.plusDays(1);
                    m_timer.scheduleAtFixedRate(createTimerTask(timerTask), 0, 1000);
                }
            }
        };
    }

    private Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public static Alarm of(LocalTime time)
    {
        return of(time.atDate(LocalDate.now()));
    }

    public static Alarm of(LocalTime time, boolean repeat)
    {
        var alarm = of(time.atDate(LocalDate.now()));

        alarm.m_repeat = repeat;

        return alarm;
    }

    public static Alarm of(LocalDateTime dateTime)
    {
        return new Alarm(dateTime);
    }

    public static Alarm of(LocalDate date)
    {
        return of(date.atTime(0, 0));
    }

    public void start(TimerTask task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), 0, 1000);
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
```

>**Arayüzler (Hatırlatma):**
>
>*Java'da arayüzler (interfaces) denilen ayrı* `UDT` *vardır. Arayüzler en çok abstract sınıflara benzese de doğrudan bir sınıf gibi özellik göstermez. Arayüzlerin non-static veri elemanları olamaz. Bu anlamda nesne özelliği göstermez. Interface içerisinde veri elemanları bulunabilir. Veri elemanları yazılsa da yazılmasa da public, static ve final olarak bildirilmiş olur. Arayüzlerin içerisinde veri elemanı kullanımı çok fazla karşımıza çıkmaz. Arayüzler içerisinde gövdesiz ve public olarak bildirilmiş metotlar bulunabilir. Bu metot bildirimlerinde public ve abstract yazılmasına gerek yoktur. Yine yazmamayı tercih edeceğiz. Java 8 ile birlikte interface içerisinde abstract olmayan public metotlar yazılabilmektedir. Bu metotlar default anahtar sözcüğü ile bildirilmelidir. Default metotlar da sanaldır. Yine Java 8 ile birlikte bir arayüz içerisinde public olarak bildirilmiş static metotlar da olabilmektedir. Java 9 ile private olarak bildirilmiş static veya non-static metotlar da arayüzler içerisinde olabilmektedir. Interface içerisinde protected ve friendly elemanlar olamaz. Bu anlamda no-modifier erişim public anlamına gelir. Bir sınıf bir arayüzü implements anahtar sözcüğü ile destekler. Bir arayüzü destekleyen sınıf o arayüzün en az bir tane abstract metodunu override etmezse sınıf abstract olarak bildirilmelidir. Aksi durumda error oluşur. Hepsini override ederse concrete bir sınıf olabilir. Anımsanacağı bir sınıf yalnızca tek bir sınıftan türetilebilir. Ancak bir sınıf istediği kadar arayüzü destekleyebilir. implements listesinde arayüz isimleri virgül ayrılır. Bu anlamda aaryüz referansları aslında taban sınıf referansı gibi kullanılarak RTP de gerçekleştirilmiş olur. Aynı zamanda birden fazla arayüz desteklenmesi ile "multiple inheritance"'a kısmi bir destek verilmiş olur. Arayüzler aslında bir anlaşmayı temsil ederler. Bu anlamda bir arayüzü desteklemek aslında onun abstract olan davranışlarını desteklemek anlamında da kullanılır. Arayüz ile RTP arayüz referansının taban sınıf referansı gibi kullanılması ile gerçekleştirilir. Bu anlamda şu kural geçerlidir. Bir sınıf türünden referans desteklediği arayüz referansına implicit olarak atanabilir. Yani bu mantıksal olarak bir upcasting'dir.*
>
>*Bir arayüzün hiç abstract metodu yoksa bu arayüze **"marker interface"** denir. Aslında programcının bir marker interface yazma ihtiyacı* `Java 5`*'den sonra pek kalmamıştır. Bunun nedeni annotation konusunda anlaşılacaktır. Ancak JavaSE'de çok kullanılan ve Java 5 öncesinden kalma bazı marker arayüzler vardır. Bu anlamda programcı yeni bir marker arayüz genel olarak bildirmez ancak var olanları kullanabilir.* `Java 8` *ile birlikte arayüzlere belirli koşullar altında adeta yeni bir görev yüklenmiştir. İçerisinde bir ve yalnız bir tane abstract metodu bulunan bir arayüz "functional interface" olarak kullanılabilmektedir. Functional interface'ler Lambda ifadeleri (lambda expressions) ve metot referansları (method reference) ile birlikte kullanılabilirler. Bu anlamda fonksiyonel programlama tekniği ve dolayısıyla callback işlemleri daha gelişmiş bir biçimde yapılabilmektedir. Bir arayüz ile anonim sınıf bildirimi yapılabilir. Bu durumda anonim sınıf o arayüzü destekleyen bir sınıf olarak bildirilmiş ve o sınıf türünden bir nesne yaratılmış olur. Bu durumda tipik olarak bir arayüz ile bildirilen anonim sınıf türünden adres ilgili arayüz türünden referansa doğrudan atanabilir. Yine ilgili anonim sınıf isimlendirmesi* `$` *karakteri ile yapılacaktır. Arayüzler arasında türetme yapılabilir. Hatta arayüzler arasında multiple inheritance'da yapılabilir. Türetme işlemi için extends anahtar sözcüğü kullanılır.*
>
>**_Anahtar Notlar:_** Arayüzlerin isimlerini okunabilirlik/algılanabilirlik açısından I ile başlatacağız. JavaSE'de bulunan arayüzler bu convention'a uymamışlardır.
>
>*Programcının soyut bir tür tasarlaması gerektiğinde bunun interface'mi yoksa anonim sınıf mı olması gerektiğine karar verecektir?*
>
>*Aslında bu zaman içerisinde refleks haline gelse de özellikle yeni başlayan programcılar için şöyle basit bir kural önerilebilir: Önce interface düşünülmeli, interface özellikler domain'e ilişkin ihtiyaçları karşılamıyorsa abstract sınıf tercih edilmelidir. Bu şekilde düşünmenin çeşitli avantajları vardır. Örneğin ilgili domain'de interface yapılabilen bir UDT, abstract sınıf olarak bildirildiğinde o sınıftan türetme yapan bir sınıf başka bir sınıftan türetme yapamaz. Ancak interface olarak bildirilirse istediği sınıftan türetme yapıp o arayüzü de destekleyebilir.*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        IY iy = new IY() {
            //...
        };
    }
}

abstract class A implements IX, IY {
    //...
}

class B implements IX, IY {
    public void foo()
    {
        //...
    }
}

interface IT extends IX, IY {
    //...
}

interface IZ extends IX {
    //...
}

interface IY {
    //...
}

interface IX {
    int VALUE = 1;

    void foo();

    default void bar() //Since Java 8
    {
        //...
    }

    static void tar() //Since Java 8
    {
        //...
    }

    private void car() //Since Java 9
    {
        //...
    }

    private static void zar() //Since Java 9
    {
        //...
    }
}
```

>**Lambda İfadeleri (Lambda expressions):**
>
>*Lambda ifadeleri (LE) matematikten programlamaya aktarılmıştır. LE çok karmaşık olarak yazılabilse de sentaksı pratikte 8 genel biçime indirgenebilir:*
>
>       1.  (<değişken listesi>) -> ifade
>       2.  (<değişken listesi>) -> {...}
>       3.  <değişken> -> ifade
>       4.  <değişken> -> {...}
>       5.  () -> ifade
>       6.  () -> {...}
>       7.  (<tür değişken listesi>) -> ifade
>       8.  (<tür değişken listesi>) -> {...}
>*LE'ler **bir ve yalnız bir** tane **abstract metodu** olan yani **"functional interface"** referanslarına atanabilir. Aksi durumda error oluşur. Bir LE atanmış olduğu referansa ilişkin iterface'in abstract metodu yerine geçer. Yani aslında LE ile o arayüzü destekleyen bir sınıf bildirimi yapılmış ve ilgili abstract metodu override edilmiş olur. Aslında LE anonim sınıfların daha gelişmiş biçimidir. Ancak anonim sınıfla yapılan herşey LE ile yapılamaz. Örneğin birden fazla abstract metoda sahip bir arayüz ile anonim sınıf bildirilebilir ancak LE kullanılamaz. Ya da örneğin, bir anonim sınıf abstract bir sınıf ile bildirilebiliren LE ile kullanılamaz. Buradan haraketle şu söylenebilir. LE'nin kullanılabildiği durumda anonim sınıf yeriene tercih edilmelidir. JavaSE'de bir çok fonksiyonel arayüz vardır. Özellikle* `Java 8` *ile birlikte* `java.util.function` *paketi içerisinde bir grup fonksiyonel arayüz pek çok yerde kullanılmaktadır. Bu arayüzler ileride ele alınacaktır. LE yazıldığında derleyicinin ürettiği koda **"closure"** denir. Aslında closure terimi LE ile yayılmış olsa da aslında anonim, nested, inner ve local sınıflar içinde üretilen kodlar aslında closure olarak düşünülebilir. LE ile üretilen kodlar için genel olarak ayrı byte code üretilmez. LE ile üretilen kodların detaylaro Java ile Uygulama 2 kursunda ele alınacaktır. Bir LE de kendisinden önce bildirilen yerel ve parametre değişkenlerini "effectively final" olarak yakalar (capture).*
>
>*LE'de* `->` *'dan önce yerine geçtiği abstract metodun parametreleri bulunur. Örneğin metot iki parametreli ise* `1`, `2` veya `7`, `8` *biçimlerinde biri kullanılabilir. Ya da örneğin yerine geçtiği abstract metodun parametresi yoksa* `5` ve `6` *biçimleri, tek parametreli ise* `3` ve `4.` *biçimler kullanılabilir.* `1`, `2`, `3` ve `4.` *biçimlerde parametre değişkenlerinin türleri atanan fonksiyonel arayüz refaransına ilişkin abstract metot ile aynı sayıda verilmelidir ve parametreler için tür tespiti (type inference) buradan hareketle yapılır.*
>
>*Yukarıdaki* `1`, `3`, `5`, `7` *ile belirtilen genel biçimlerde bir ifade yazılır. Eğer LE'nin yerine geçtiği abstract metodun geri dönüş değer varsa yazılan ifadenin değeri geri dönüş değeri olarak verilmiş olur. Bu biçimlerde return deyimi yazılması error oluşturur.* `2`, `4`, `6` ve `8` *biçimlerinde tek bir metodun gövdesi yazılabilir. Eğer hedef metodun geri dönüş değeri varsa bu biçimlerde return deyimi akışın her noktasında olmak zorundadır. LE içerisinde kullanılan parametre değişkenlerinin faaliyet alanı (scope) LE boyuncadır.*
>
>`7` ve `8.` *biçimlerde değişkenler tür bilgisi yazılır. Bir değişken için tür bilgisi yazıldığında hepsi için yazılmalıdır.* `Java 11` *ile birlikte LE'nin parametre değişkenlerinin tür bilgisi için var yazılabilmektedir. Şüphesiz var yazıldığında da türün derleyici tarafından tespit edilebilmesi gerekir*
>
>*Genel olarak söylemek gerekirse LE ile atandığı fonksiyonel arayüz referansına ilişkin abstract metodun karşılıklı olarak uyumlu olması gerekir. Aksi durumda error oluşur*

>*Aşağıdaki demo örneği inceleryiniz. Örnekteki* `xorOp` *referansına atanan Lambda ifadesinin anonim sınıf karşılığı şu şekildedir:*
>
>       new IIntBinaryOperator() {
>           public int applyAsInt(int a, int b)
>           {
>               return a  b;
>           }
>       };

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();

        IIntBinaryOperator xorOp = (a, b) -> a ^ b;
        IDoubleBinaryOperator doubleOp = (a, b) -> {Console.writeLine("a = %f, b = %f", a, b); return a + b;};
        IIntSupplier supplier = () -> random.nextInt(100);
        IInConsumer consumer = a -> Console.writeLine("Value:%d", a);

        Console.writeLine(xorOp.applyAsInt(10, 20));
        Console.writeLine(doubleOp.applyAsDouble(3.4, 5.6));
        Console.writeLine(supplier.get());
        consumer.accept(20);
    }
}

interface IIntBinaryOperator {
    int applyAsInt(int a, int b);
}


interface IDoubleBinaryOperator {
    double applyAsDouble(double a, double b);
}

interface IIntSupplier {
    int get();
}

interface IInConsumer {
    void accept(int a);
}
```

>*Aşağıdaki demo örnekte* `doWork` *metotlarına geçilen LE'lerin parametre değişkenlerine tür bilgisi yazılmazsa ambiguity oluşur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        Sample.doWork((int a, int b) -> a * b, 10, 20);
        Sample.doWork((double a, double b) -> a * b, 10, 20);
    }
}

class Sample {
    public static void doWork(IIntBinaryOperator op, int a, int b)
    {
        Console.writeLine("Int binary operator:a = %d, b = %d, result = %d", a, b, op.applyAsInt(a, b));
    }

    public static void doWork(IDoubleBinaryOperator op, double a, double b)
    {
        Console.writeLine("Double binary operator:a = %f, b = %f, result = %f", a, b, op.applyAsDouble(a, b));
    }
}

interface IIntBinaryOperator {
    int applyAsInt(int a, int b);
}


interface IDoubleBinaryOperator {
    double applyAsDouble(double a, double b);
}
```

>`Alarm` sınıfının `org-csystem-util-scheduler` kütüphanesine `13.0.0` ile eklenen versiyonu ve test kodunu inceleyiniz

```java
/*-------------------------------------------------------------
    FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 4th Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private LocalDateTime m_dateTime;
    private Timer m_timer;
    private boolean m_repeat;

    private TimerTask createTimerTask(Runnable runnable)
    {
        return new TimerTask() {
            public void run()
            {
                if (LocalDateTime.now().isBefore(m_dateTime))
                    return;

                runnable.run();
                m_timer.cancel();
                if (m_repeat) {
                    m_timer = new Timer();
                    m_dateTime = m_dateTime.plusDays(1);
                    m_timer.scheduleAtFixedRate(createTimerTask(runnable), 0, 1000);
                }
            }
        };
    }

    private Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public static Alarm of(LocalTime time)
    {
        return of(time.atDate(LocalDate.now()));
    }

    public static Alarm of(LocalTime time, boolean repeat)
    {
        var alarm = of(time.atDate(LocalDate.now()));

        alarm.m_repeat = repeat;

        return alarm;
    }

    public static Alarm of(LocalDateTime dateTime)
    {
        return new Alarm(dateTime);
    }

    public static Alarm of(LocalDate date)
    {
        return of(date.atTime(0, 0));
    }

    public void start(Runnable runnable)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(runnable), 0, 1000);
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
```

```java
package org.csystem.scheduler.timeout;

import com.karandev.io.util.console.Console;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;

@Ignore
public class AlarmTest {
    @Test
    public void test()
    {
        var alarm = Alarm.of(LocalDateTime.now().plusSeconds(10));

        alarm.start(() -> Console.writeLine("Alarm!...."));

        Console.readChar();
    }
}
```

>**_Sınıf Çalışması:_** Aşağıda belirtilen `Scheduler` isimli sınıfı public bölümünü değiştirmeden yazınız.
>
>**_Açıklamalar:_**
>    - Sınıf `Timer` sınıfı kullanılarak yazılacaktır ancak türetme yapılmayacaktır.
>    - Sınıf `Timer` sınıfının daha fonksiyonel olarak kullanılabilen bir biçimi olarak düşünülebilir.
>
>Sınıfın public bölümü aşağıdaki gibidir:
```java
package org.csystem.scheduler;

import java.util.concurrent.TimeUnit;

public class Scheduler {
    public Scheduler(long interval, TimeUnit timeUnit)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    public Scheduler(long intervalInMillis)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    public Scheduler(long delay, long interval, TimeUnit timeUnit)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    public Scheduler(long delayInMillis, long intervalInMillis)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    public final void schedule(Runnable task)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    public final void cancel()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: Scheduler.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 10th Feb 2024

	Scheduler class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final Timer m_timer;
    private final long m_delay;
    private final long m_interval;

    private static TimerTask createTimerTask(Runnable task)
    {
        return new TimerTask() {
            public void run()
            {
                task.run();
            }
        };
    }

    public Scheduler(long interval, TimeUnit timeUnit)
    {
        this(0, interval, timeUnit);
    }

    public Scheduler(long intervalInMillis)
    {
        this(0, intervalInMillis);
    }

    public Scheduler(long delay, long interval, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(delay), timeUnit.toMillis(interval));
    }

    public Scheduler(long delayInMillis, long intervalInMillis)
    {
        m_timer = new Timer();
        m_delay = delayInMillis;
        m_interval = intervalInMillis;
    }

    public final void schedule(Runnable task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), m_delay, m_interval);
    }

    public final void cancel()
    {
        m_timer.cancel();
    }

    //...
}
```

>`Scheduler` sınıfının `org-csystem-util-scheduler` kütüphanesine `13.1.0` ile eklenen versiyonu

```java
/*-------------------------------------------------------------
    FILE		: Scheduler.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 10th Feb 2024

	Scheduler class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final Timer m_timer;
    private final long m_delay;
    private final long m_interval;

    private static TimerTask createTimerTask(Runnable task)
    {
        return new TimerTask() {
            public void run()
            {
                task.run();
            }
        };
    }

    private Scheduler(long delayInMillis, long intervalInMillis)
    {
        m_timer = new Timer();
        m_delay = delayInMillis;
        m_interval = intervalInMillis;
    }

    public static Scheduler of(long interval, TimeUnit timeUnit)
    {
        return of(0, interval, timeUnit);
    }

    public static Scheduler of(long intervalInMillis)
    {
        return of(0, intervalInMillis);
    }

    public static Scheduler of(long delay, long interval, TimeUnit timeUnit)
    {
        return of(timeUnit.toMillis(delay), timeUnit.toMillis(interval));
    }

    public static Scheduler of(long delayInMillis, long intervalInMillis)
    {
        return new Scheduler(delayInMillis, intervalInMillis);
    }

    public final void schedule(Runnable task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), m_delay, m_interval);
    }

    public final void cancel()
    {
        m_timer.cancel();
    }

    //...
}
```

>`Scheduler` ve `Alarm` sınıflarının `org-csystem-util-scheduler` kütüphanesine `14.0.0` ile eklenen versiyonları

```java
/*-------------------------------------------------------------
    FILE		: Scheduler.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 10th Feb 2024

	Scheduler class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final Timer m_timer;
    private final long m_delay;
    private final long m_interval;
    private Runnable m_cancelTask;

    private static TimerTask createTimerTask(Runnable task)
    {
        return new TimerTask() {
            public void run()
            {
                task.run();
            }
        };
    }
    public static Scheduler of()
    {
        return new Scheduler(0, 0);
    }

    private Scheduler(long delayInMillis, long intervalInMillis)
    {
        m_timer = new Timer();
        m_delay = delayInMillis;
        m_interval = intervalInMillis;
    }

    public static Scheduler of(long interval, TimeUnit timeUnit)
    {
        return of(0, interval, timeUnit);
    }

    public static Scheduler of(long intervalInMillis)
    {
        return of(0, intervalInMillis);
    }

    public static Scheduler of(long delay, long interval, TimeUnit timeUnit)
    {
        return of(timeUnit.toMillis(delay), timeUnit.toMillis(interval));
    }

    public static Scheduler of(long delayInMillis, long intervalInMillis)
    {
        return new Scheduler(delayInMillis, intervalInMillis);
    }

    public final void schedule(Runnable task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), m_delay, m_interval);
    }

    public final void schedule(Runnable task, Runnable cancelTask)
    {
        m_cancelTask = cancelTask;
        schedule(task);
    }

    public final void schedule(Runnable task, LocalDateTime dateTime)
    {
        m_timer.schedule(createTimerTask(task), ChronoUnit.MILLIS.between(LocalDateTime.now(), dateTime));
    }

    public final void cancel()
    {
        m_timer.cancel();
        if (m_cancelTask != null)
            m_cancelTask.run();
    }

    //...
}
```

```java
/*-------------------------------------------------------------
    FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 10th Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private LocalDateTime m_dateTime;
    private Timer m_timer;
    private boolean m_repeat;

    private TimerTask createTimerTask(Runnable runnable)
    {
        return new TimerTask() {
            public void run()
            {
                runnable.run();
                if (m_repeat) {
                    m_timer = new Timer();
                    m_dateTime = m_dateTime.plusDays(1);
                    m_timer.schedule(createTimerTask(runnable), ChronoUnit.MILLIS.between(LocalDateTime.now(), m_dateTime));
                }
            }
        };
    }

    private Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public static Alarm of(LocalTime time)
    {
        return of(time.atDate(LocalDate.now()));
    }

    public static Alarm of(LocalTime time, boolean repeat)
    {
        var alarm = of(time.atDate(LocalDate.now()));

        alarm.m_repeat = repeat;

        return alarm;
    }

    public static Alarm of(LocalDateTime dateTime)
    {
        return new Alarm(dateTime);
    }

    public static Alarm of(LocalDate date)
    {
        return of(date.atTime(0, 0));
    }

    public void start(Runnable runnable)
    {
        m_timer.schedule(createTimerTask(runnable), ChronoUnit.MILLIS.between(LocalDateTime.now(), m_dateTime));
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
```

>`Alarm` sınıfının `org-csystem-util-scheduler` kütüphanesine `14.0.1` ile eklenen versiyonları

```java
/*-------------------------------------------------------------
    FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 10th Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import org.csystem.scheduler.Scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimerTask;

public class Alarm {
    private LocalDateTime m_dateTime;
    private Scheduler m_scheduler;
    private boolean m_repeat;

    private Runnable createTask(Runnable runnable)
    {
        return new TimerTask() {
            public void run()
            {
                runnable.run();
                if (m_repeat) {
                    m_scheduler = Scheduler.of();
                    m_dateTime = m_dateTime.plusDays(1);
                    m_scheduler.schedule(createTask(runnable), m_dateTime);
                }
            }
        };
    }

    private Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_scheduler = Scheduler.of();
    }

    public static Alarm of(LocalTime time)
    {
        return of(time.atDate(LocalDate.now()));
    }

    public static Alarm of(LocalTime time, boolean repeat)
    {
        var alarm = of(time.atDate(LocalDate.now()));

        alarm.m_repeat = repeat;

        return alarm;
    }

    public static Alarm of(LocalDateTime dateTime)
    {
        return new Alarm(dateTime);
    }

    public static Alarm of(LocalDate date)
    {
        return of(date.atTime(0, 0));
    }

    public void start(Runnable runnable)
    {
        m_scheduler.schedule(createTask(runnable), m_dateTime);
    }

    public void cancel()
    {
        m_scheduler.cancel();
    }
}
```

>**Method Reference:**
>
>*Bir metodu referans için kullanılan ifadeye metot referansı (method reference) (MR) denir. MR* `Java 8` *ile birlikte dile eklenmiştir ve belirli koşullar altında kullanışabilmektedir. MR ilgili abstract metodu, referans edilen metot ile uyumlu olan bir fonksiyonel arayüz referansına atanabilir. Burada uyumluluk geri dönüş değerinin ve parametrik yapısının ilgili abstract metot ile genel olarak aynı olmasıyla sağlanır. MR'ın genel biçimi şu şekildedir:*
>
>   `<isim1>::<isim2>`
>- isim1 şunlardan biri olabilir: UDT ismi, referans ismi
>- isim2 şunlardan biri olabilir: metot ismi, new anahtar sözcüğü
>
>**MR çeşitleri:**
>    1. static method reference:
>    2. reference to an instance method of particular object:
>    3. reference to an instance method of any object of a particular type:
>    4. ctor reference:

>*Aşağıdaki demo örnekte MR kullanımına odaklınınız*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();

        IIntBinaryOperator ibo = IntOperation::multiply; //1 -> Lambda karşılığı: (a, b) -> IntOperation.multiply(a, b);

        Console.writeLine(ibo.applyAsInt(10, 20));
        Console.writeLine("------------------------------------------------------------------");

        ibo = Integer::sum; //1
        Console.writeLine(ibo.applyAsInt(10, 20));
        Console.writeLine("------------------------------------------------------------------");

        IIntPredicate intPredicate = NumberUtil::isPrime; //1 -> Lambda karşılığı: a -> NumberUtil.isPrime(a);
        Console.writeLine(intPredicate.test(1_000_003) ? "Asal" : "Asal değil");
        Console.writeLine("------------------------------------------------------------------");

        IIntSupplier intSupplier = random::nextInt; //2 -> Lambda karşılığı:() -> random.nextInt();
        Console.writeLine(intSupplier.get());
        Console.writeLine("------------------------------------------------------------------");

        IIntRandomBoundSupplier intRandomBoundSupplier = Random::nextInt; //3 -> Lambda karşılığı: (r, b) -> r.nextInt(b);
        Console.writeLine(intRandomBoundSupplier.get(random, 100));
        Console.writeLine("------------------------------------------------------------------");

        IStringToIntConverter converter = String::length; //3 -> Lambda karşılığı: s -> s.length();
        Console.writeLine(converter.convert("ankara"));
        Console.writeLine("------------------------------------------------------------------");

        IIntValueFactory intValueFactory = IntValue::new; //4 -> Lambda karşılığı: a -> new IntValue(a);
        Console.writeLine(intValueFactory.create(10).getValue());
        Console.writeLine("------------------------------------------------------------------");

        IIntValueDefaultFactory intValueDefaultFactory = IntValue::new; //4 -> Lambda karşılığı: () -> new IntValue();
        Console.writeLine(intValueDefaultFactory.create().getValue());
        Console.writeLine("------------------------------------------------------------------");
    }
}

class IntOperation {
    public static int multiply(int a, int b)
    {
        return a * b;
    }
}

class IntValue {
    private int m_value;

    public IntValue()
    {
    }

    public IntValue(int value)
    {
        m_value = value;
    }

    public int getValue()
    {
        return m_value;
    }

    //...
}

interface IIntValueFactory {
    IntValue create(int val);
}

interface IIntValueDefaultFactory {
    IntValue create();
}

interface IIntBinaryOperator {
    int applyAsInt(int a, int b);
}

interface IIntPredicate {
    boolean test(int a);
}

interface IIntSupplier {
    int get();
}

interface IIntRandomBoundSupplier {
    int get(Random random, int bound);
}

interface IStringToIntConverter {
    int convert(String str);
}
```

>**_Anahtar Notlar:_** Programlama açısından MR, LE ve anonim sınıflarla ilgili şu temel kullanım sırası önerilir:
>    1. MR
>    2. LE
>    3. Anonim sınıf
>
>*Bu kullanım sırasına sentaks olarak geçerli olduğu sürece uyulmalıdır. Zaten pek çok static kod analizi aracı da bu sıraya göre kullanım önerisi sunar. Ancak programcı "static kod analizi nasılsa uyarır" fikriyle kullanmamalıdır.*

>*Java 8 ile birlikte* `java.util.function` *paketi içerisinde bulunan bir grup fonksiyonel arayüz eklenmiştir. Bu arayüzle özellikle yine Java 8 ile birlikte eklenen "Stream API" tarafından da kullanılmaktadır. Stream API ileride ele alınacaktır. Şüphesiz bu arayüzler yalnızca Stream API ile kullanılmaz. JavaSE'de pek çok yerde gerektiğinde kullanılır. Programcı da kendi tasarımlarında özel bir durum yoksa bu arayüzlerleri tercih etmelidir. Bu arayüzler anlaşılması ve akılda tutulması açısından kolaylık sağlaması için şu şekilde gruplandırılabilir:*
>- **Function arayüzleri:** Bu arayüzler bir ya da iki girdi (input) alan ve bir değer üretilmesini sağlayan arayüzlerdir. Bu arayüzlerin en geneli `Function` ve `BiFunction` arayüzleridir. Bu arayüzlerin abstract metotları `applyXXX` biçiminde isimlendirilmiştir.
>- **Operator arayüzleri:**  Bu arayüzler bir ya da iki girdi alan ve bir değer üretilmesini sağlayan arayüzlerdir. Bu arayüzler Function arayüzlerinin adeta özelleştirilmiş biçimleridir. Bazıları `Function` veya `BiFunction` arayüzlerinden türetilmiştir. Bu arayüzlerin abstract metotları `applyXXX` biçimide isimlendirilmiştir.
>- **Consumer arayüzleri:** Bu arayüzler bir ya da iki girdiye göre işlem yapan, bir sonuç üretmeyen işlemlerde kullanılır. Bu arayüzlerin abstract metotlarının geri dönüş değeri yoktur ve `acceptXXX` biçiminde isimlendirilmişlerdir.
>- **Supplier arayüzleri:** Bu arayüzler bir girdi almdan değer üretmek için kullanılır. Bu arayüzlerin abstract metotları `getXXX` biçiminde isimlendirilmiştir.
>- **Predicate arayüzleri:** Bu arayüzler bir ya da iki girdi alan ve sonucunda boolean türden bir değer üretilmesini sağlayan arayüzlerdir. Bu arayüzlerin abstract metotları `test` olarak isimlendirilmiştir.

**_Anahtar Notlar:_** Yukarıdaki gruplara bakıldığında hiç girdi almayıp, bir değer de üretmeyen (yani parametresiz ve geri dönüş değeri olmayan bir abstract metodu olan) bir fonksiyonel arayüz **yoktur**. Bu ihtiyaç zaten Java'da ilk zamanlardan beri bulunan `Runnable` arayüzü ile giderilmiştir. Yani aslında `Runnable` arayüzü ayrı bir grup olarak düşünülebilir.

>*Bir kod içerisinde bulunan programalama hatalarına genel olarak bug denilmektedir. Örneğin bir metot sıralama işlem yapıyor olsun. Bu metot örneğin dizi sıralıyken sırayı bozuyorsa ancak diğer durumlarda sıralama işlemini gerçekleştiriyorsa, bu metotta "bug" var denebilir. Bir kodun bug'larının giderilmesi işlemine ise "debug" denilmektedir. Bir kodu debug etmek için kullanılabilen yardımcı programlara "debugger" denir. Debugger'lar ile kodlar adım adım çalıştırılabilir, akış herhangi bir noktada durdurulabilir ve bunun gibi pek çok yardımcı işlem yapılabilmektedir. Java geliştirmede kullanılan IDE'ler de debugger'ları çalıştırarak daha görsel araçlarla kullanımı kolaylaştırırlar. Bir debugger kullanılırken akışın belli bir noktaya geldiğinde beklemesi için "break point" denilen bir araç da kullanılabilir. Debugger'lar ile kullanılan pek çok araç vardır. Zamanla anlaşılacaktır*

>*Java'da test işlemlerinde kullanılabilen "assert statement" bulunmaktadır. assert deyiminin genel biçimi şu şekildedir:*
>
>   `assert <boolean türden ifade> [: <void olmayan bir ifade>];`
>*Akış assert deyimine geldiğinde boolean türden ifade hesaplanır, eğer true ise akış devam eder, false ise AssertionError türünden exception fırlatılır ve tipik olarak akış sonlanır. Eğer optional olan void olmayan ifade varsa o ifadenin değeri de stderr'ye basılır*
>
>*assert kullanımı için JVM'e* `-ea` veya `-enableAssertions` *seçeneği verilmelidir. Eğer bu seçeneklerden biri verilmezse assert deyimleri görmezden gelinir. assert deyimleri derleme sırasında var olduğundan seçenekler verilmemiş olsa bile byte code içerisinde bulunurlar. Öyleyse programcının, ürün aşamasında assert deyimlerinin byte kodda olmayacağı biçimde yazması gerekir. Şüphesiz assert deyimlerini yorum satırlarına almak ya da koddan kaldırmak profesyonel bir çözüm değildir. Bu durumda programcı sabit görevi göre bir flag veri elemanı ile bu kodları devreye sokabilir ya da derleme aşamasında arakoda eklenmemesini sağlayabilir.* `UtilLib` *içerisindeki* `numberToText3DigitsTR` *private metodunda bu yöntem kullanılmıştır. Anımsanacağı gibi derleyici akışın bir noktaya kesin gelmeyeceğini anlarsa ya error verir ya da ilgili kodu byte code'a eklemez. Ayrıca **sabit ifadesi (constant expression)** ile değer verilmiş final bir değişken arakodda sabit görevi görür. Yani örneğin onun için bellekte (heap ya da stack) bir ayrılması gerekmez. Örnekte* `DEBUG` *sabitine false değeri verildiğinde akış hiç bir şekilde assert deyimlerine gelemeyeceğinden derleyici assert deyimlerini byte code'a eklemez. Bu durumda test işleminden sonra* `DEBUG` *false değerine çekilerek nihai ürün elde edilir. assert deyimleri özellikle private metotların testlerinde tercih edilebilir.*

>**_Sınıf Çalışması:_** `switch` deyiminde bir senaryo ile aşağı düşmenin olmadığını test eden kodu `assert` deyimi kullanarak yazınız.
>
>**_Sınıf Çalışması:_** Algoritmanızın, `switch` deyiminde `default case`'e gelmediğini `assert` deyimi kullanarak bir demo örnekle yazınız.

>**Annotation:**
>
>*Anotation'lar modern programlama dillerine gittikçe daha fazla girmeye başlamıştır. Gerçekten* `C++`, `C#`, `Kotlin`, `Swift`, `Python` *gibi dillerde de çeşitli isimlerle bulunur. Bu kavram çeşitli dillerde çeşitli isimlerle anılmaktadır. Java'ya annotation'lar* `Java 1.5` *ile eklenmiştir. Java'da annotation'lar, genel olarak bir sentaktik elemanının önüne getirilebilen ve* `@` *işareti ile başlatılan user defined type'lardır. Annotation'lar ile işaretlenebilen (mark) sentaktik elemanlarından bazıları şunlardır:*
>    - Sınıf bildirimleri
>    - Arayüz bildirimleri
>    - Enum class bildirimleri
>    - Annotation bildirimleri
>    - Record bildirimleri
>    - Metot bildirimleri
>    - Veri elemanı bildirimleri
>    - Metot parametre değişkeni bildirimleri
>    - ...
>
>**_Anahtar Notlar:_** Annotation'lar genel olarak derleyicinin, kod üreten bazı araçların (plugin) veya çalışma zamanında bir kod tarafından bakılıp ona göre işlem yapıldığı durumlarda kullanılır. Örneğin bir metot `org.junit` paketindeki `@Test` annotation'ı ile işaretlendiğinde JUnit o metodu test metodu olarak çalıştıracağını anlar. JUnit `@Test` ile işaretlenmemiş bir metodu test metodu olarak düşünmez.
>
>**_Anahtar Notlar:_** Bu bölüme yazılacak olan annotation'ların programlamada kullanımı "reflection" isimli konuda ele alınacaktır.
>
>*Bir annotation @interface ile bildirilebilir. Annotation'ın elemanları olabilir. Bu member'lara genel olarak attribute denilmektedir. Annotation elemanları geri dönüş değeri olan parametresiz metotlar gibi bildirilir. Gövdeleri olmaz, erişim belirleyicileri olmaz (aslında bu elemanlar aşağı seviyede gerçekten metot olarak ele alınır). Attribute'ların default değeri olabilir. Attribute bildiriminin genel biçimi şu şekildedir:*
>
>   `<tür ismi> <attribute ismi>() [default <ilgili türden sabit ifadesi>]`
>*Burada, attribute herhangi bir türden olabilir. Bir attribute'un default değeri sabit ifadesi (constant expression) olarak verilmelidir. Bir annotation'ın sentaktik elemana eklenmesinin genel biçimi şu şekildedir:*
>
>   `@<annotation ismi>[([attribute = değer listesi])]`
>*Bir annotation, bir sentaktik elemana işaretlendiğinde, annotation'ın attribute'ı yoksa veya tüm attribute'ları d default değerlerini almışsa bu durumda ekleme iki biçimden biriyle yapılabilir:*
>1. `@<annotation ismi>`
>2. `@<annotation ismi>()`
>
>*Bu iki biçim tamamen aynı anlamdadır. Bir annotation'ın attribute'u olmayabilir. Bir attribute ekleme sırasında attribute ismine değer verilerek yapılır. Değerler sabit ifadesi olmalıdır. Bir attribute'un türü ne olursa olsun ismi "value" ise, işaretlemede bir tek value attribute'unun değeri verilecekse attribute ismini yazmadan doğrudan değer yazılabilir. Eğer value ile birlikte başka attribute'lara da değerler verilecekse bu durumda value da yazılmalıdır. Bir attribute bir dizi türünden olabilir. Dizi türünden bir attribute için değerler* `{}` *içerisinde verilmelidir. Eğer bir tane değer verilecekse* `{}` *yazılmayabilir. Birden fazla değer için kesinlikle yazılmalıdır.*
>
>*Bir annotation bir sentaktik elemana default olarak birden fazla işaretlenemez. Birden fazla işaretlenebilen bir annotation (repeatable annotation) bildirimi reflection konusunda ele alınacaktır.*
>
>*Bir annotation'ın hangi sentaktik elemanlara eklenebileceği* `Target` *isimli bir annotation ile, annotatation eklenerek yapılabilir.* `Target` *annotation'ının value isimli* `ElementType[]` *türünden attribute'u vardır.* `ElementType` *bir enum sınıfıdır ve sabitleri ilgili annotataion'ın eklenebileceği sentaktik elemanları temsil eder. Bir annotation Target annotation'ı ile işaretlenmezse tüm sentaktik elemanlara eklenebilir. target annotation'ı yalnızca annotation'lara eklenebilen bir annotation'dır. Target annotation'ı için bu durum da Target annotation'ı eklenerek sağlanmıştır.*
>
>*Bir annotation'ın ele alınması anlamında 3 tane kategorisi vardır:* `RUNTIME`, `CLASS`, `SOURCE`*. Bu kategoriye retention denilmektedir. Bir annotation için kategori,* `Retention` *isimli bir annotation ile belirlenir. Retention annotation'ının* `RetentionPolicy` *isimli enum sınıfı türünden value isimli bir attribute'u vardır. Kategori RetentionPolicy enum sınıfının sabitleri ile belirlenebilir. Bu sabitler RUNTIME, CLASS ve SOURCE ismindedir. Bu sabitlerin anlamları şu şekildedir:*
>
>- **RUNTIME:** Çalışma zamanında da kullanılabilmek tasarlanmış bir annotation belirtir. Bu annotation'lar işaretlendiği sentaktik elemanla birlikte byte code'a da eklenir. Bir sentaktik elemanın RUNTIME annotation ile işaretlenmiş olup olmadığına çalışma zamanında nasıl bakılacağı "reflection" konusunda ele alınacaktır.
>- **CLASS:** Derleme zamanında (ya da kabaca build zamanında) bakılabilen. Ancak, çalışma zamanında bakılamayan annotation belirtir. Bu annotation işaretlendiği sentaktik elemanla birlikte byte code'a yazılır. Bir annotation Retention annotation'ı ile işaretlenmezse default olarak bu kategoride kabul edilir
>- **SOURCE:** Derleyicinin işaretlenen sentaktik elemanla birlikte byte code'a eklemediği bir annotation belirtir. Bu kategorideki annotation'a şüphesiz çalışma zamanında bakılamaz.
>
>**_Anahtar Notlar:_** `CLASS` ve `SOURCE` annotation'ların nasıl ele alındığı "Java ile Uygulama 2" kursunda incelenecektir.

>*Aşağıdaki örneği inceleyiniz*

```java
package org.csystem.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@YourAnnotation()
class Sample {
    @TheirAnnotation
    private int m_value;

    @YourAnnotation
    @MyAnnotation(value = 10, message = "This is a test")
    @OurAnnotation({"xxxx", "yyy"})
    public void foo(@TheirAnnotation int a)
    {
        //...
    }

    @YourAnnotation(45)
    @MyAnnotation(20)
    @OurAnnotation("this is another test")

    public void bar()
    {
        //...
    }
}

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
@interface TheirAnnotation {

}

@Retention(RetentionPolicy.CLASS)
@interface OurAnnotation {
    String [] value();
}

@Retention(RetentionPolicy.SOURCE)
@interface YourAnnotation {
    int value() default 0;
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String message() default  "";
    int value();
}
```

>*Java'da bir grup standart annotation bulunur. Bunlardan önemli bazıları şunlardır:*
>    - Target
>    - Retention
>    - FunctionalInterface
>    - Deprecated
>    - Override
>    - Inherited
>    - Repeatable
>    - Documented
>    - SuppressWarnings
>    - ...

>`FunctionalInterface` *annotation'ı bir arayüzün fonksiyonel olarak kullanışacağı durumda o arayüze işaretlenir. Bu annoation bir RUNTIME annotation'dır. Bu annotation ile işaretlenmiş bir arayüzün bir ve yalnız bir tane abstract metodu olmalıdır. Aksi durumda error oluşur. Bu annotation bir RUNTIME annotation'ı olmasına karşında derleyici tarafından da bakılır. Örneğin java.util.function paketi içerisindeki tüm annotation'lar ve Runnable annotation'ı FunctionalInterface annotation'ı ile işaretlenmiştir. Bu annotation ile işaretlenmemiş ancak bir ve yalnız bir tane abstract metodu arayüzler de söz konusudur. Bu durumda programcı genel olarak şu şekilde düşünmelidir: "FunctionalInterface ile işaretlenmiş bir arayüzün fonksiyonel olarak kullanıldığı durumlar olmalıdır. Eğer bir arayüzün bir ve yalnız bir tane abstract metodu var ve FunctionalInterface ile işaretlenmemişse (eğer bir tasarım hatası yoksa) bu arayüz genel fonksiyonel kullanılmamaktadır". Bu anlamda bu annotation ile işaretlemenin okunabililiği/algılanabilirliği olumlu etkilediği de söylenebilir. Ayrıca bu annotation ile işaretlenmiş arayüzler için derleyici çeşitli optimizasyonlar yapabilmektedir.*

>*Deprectaed annotation'ı ile deprecated olan bir sentaktik eleman işaretlenebilir. Bu annotation bir RUNTIME annotation'ı olmasına karşın build aşamasında derleyiciler ve kod analizi araçları gibi araçlar tarafından da bakılır. Genel olarak derleyiciler ve static kod analizi araçları Deprecated işaretlenmiş sentaktik elemanlar için uyarı mesajları verirler. Deprecated annotation'ına Java 9 ile birlikte forRemoval ve since isimli iki attribute eklenmiştir. forRemoval boolean türdendir ve deprectad olan sentaktik elemanın ileride artık olmayacağını belirtmek için kullanılır. since String türdendir genel olarak buraya deprecated olan versiyon yazılır. Deprecated annotation'ı deprected bir sentaktik eleman için mutlaka kullanılmalı ve deprecated olmanın nedeni ve yerine kullanılabilecekler de dökumante edilmelidir.*

>*Override annotation'ı SOURCE bir annotation'dır ve yalnızca metot bildirimlerinde kullanılabilir. Bu annotation'ın bir attribute'u yoktur. Bu annotation metodu override edilip edilemeyeceğinin derleme zamanında kontrol edilmesi için kullanılır. Bu annotation ile işaretlenmiş bir metodun taban sınıfta sanal karşılığının bulunması gerekir. Aksi durumda error oluşur. Override annotation'ı da override edilen bir metotta kesinlikle kullanılmalıdır. Özellikle programcının yaptığı değişikliklerin türemiş sınıflarda da fark edilebilmesi ve düzeltilebilmesi için gerekir. Ayrıca bu annotation okunabilirliği/algılanabilirliği de artırır*

>*Reflection: Java derleyicisi byte code'a metadata denilen bilgiler yazar. Örneğin bir sınıf için veri elemanları, veri elemanlarının erişim belirleyicileri, isimleri, static olup olmadıkları ve türleri gibi bilgiler byte code'da bulunur. reflection genel olarak çalışma zamanında metadata'lara göre işlem yapmak olarak tanımlanabilir. Örneğin bir metodun bir sınıfta yazılmış olmasına göre (dikkat override etme değil) işlem yapılması gereken bir durumda kodun ilgili sınıfta o metodun var olup olmadığını anlaması reflection ile yapılır. Ya da örneğin bir framework'ün bir sınıfın bir annotation ile işaretlenmiş metotlarını elde edebilmesi için yine reflection kullanması gerekir.*
>
>*Reflection kullanılarak elde edilen metadata bilgileri ile çalışma zamanında çeşitli işlemler yapılabilir. Örneğin private bölümdeki bir ctor'a erişilip o ctor ile nesne yaratılması sağlanabilir.*
>
>*Reflection'ın en temel sınıfı* `Class<T>` *isimli bir sınıftır. Bu sınıf java.lang paketi içerisinde bildirilmiştir. Java'da çalışma zamanında kullanılan her tür için (temel türler de dahil) bir* `Class<T>` *nesnesi yaratılır. Yani her türe karşılık gelen bir* `Class<T>` *nesnesi vardır. Bu nesneler tür başına bir tanedir. Aynı tür için ikinci bir tane yaratılmaz. Bu nesnenin referansı çalışma zamanında Java programcısı tarafından elde edilebilir. Bu nesneler JVM tarafından optimize bir biçimde yaratılır. Yani, genel olarak kullanılmayan türler için* `Class<T>` *nesneleri yaratılmaz.*
>
>Bir türe ilişkin `Class<T>` nesnesine ilişkin referans 3 şekilde elde edilebilir:
>
>1. Tür ismi ve nokta operatörü kullanarak `class` anahtar sözcüğü ile (class expression)
>2. `Class` sınıfının `forName` static metodu ile
>3. `Object` sınıfının `getClass` metodu ile
>
>**_Anahtar Notlar:_** Bu 3 yöntem genel olarak birbirine alternatif değildir. Duruma göre herhangi bir tanesi kullanılır.

>*class expression*

```java
package org.csystem.app;

import org.csystem.util.array.ArrayUtil;
import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        var clsInt = int.class;
        var clsString = String.class;
        var clsArrayUtil = ArrayUtil.class;
        var clsIntArray = int[].class;

        Console.writeLine(clsInt.getName());
        Console.writeLine(clsString.getName());
        Console.writeLine(clsArrayUtil.getName());
        Console.writeLine(clsIntArray.getName());
    }
}
```

>`Class` *sınıfının* `forName` *metodu parametresi ile aldığı* `UDT` *ismine ilişkin tür varsa onun Class nesnesi referansına geri döner. Yoksa* `ClassNotFoundException` *fırlatır. Bu metot ile temel türlere ilişkin Class referansı doğrudan elde edilemez*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        try {
            var cls = Class.forName(Console.readString("Input type:"));

            Console.writeLine("Name:%s", cls.getName());
        }
        catch (ClassNotFoundException ex) {
            Console.writeErrLine("Message:%s", ex);
        }
    }
}
```

>`Object` *sınıfının* `getClass` *metodu. Aşağıdaki sınıflar Java ile Nesne Yönelimli Programlama Kursu'nda yazılmıştır*

```java
package org.csystem.app.generator;

import org.csystem.generator.ObjectArrayGenerator;
import org.csystem.math.geometry.Point;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.string.StringUtil;

import java.util.Scanner;

public class ObjectArrayGeneratorDemoApp {
    //String, Integer, Boolean, Double, Character, Point, int [], String []
    private static void doForObjects(Object [] objects)
    {
        for (Object object : objects) {
            String typeStr = object.getClass().getName();

            System.out.println("-----------------------------------------------------------");
            System.out.printf("Type:%s%n", typeStr);

            if (object instanceof String str)
                System.out.printf("str:%s, upper:%s%n", str, str.toUpperCase());
            else if (object instanceof Integer) {
                int val = (int)object;

                System.out.printf("%d * %d = %d%n", val, val, val * val);
            }
            else if (object instanceof Double) {
                double val = (double)object;

                System.out.printf("%f + %f = %f%n", val, val, val + val);
            }
            else if (object instanceof Boolean) {
                boolean flag = (boolean)object;

                System.out.printf("flag = %b%n", flag);
            }

            else if (object instanceof Character) {
                char ch = (char)object;

                System.out.printf("ch = %c%n", ch);
            }
            else if (object instanceof Point point)
                System.out.printf("Distance to origin of %s is %f%n", point.toString(), point.distance());
            else if (object instanceof int [] a) {
                ArrayUtil.print(a, " ", " -> ");
                System.out.println(ArrayUtil.sum(a));
            }
            else if (object instanceof String [] str) {
                ArrayUtil.print(str);
                System.out.println(StringUtil.join(str, '-'));
            }

            System.out.println("-----------------------------------------------------------");
        }
    }

    public static void run()
    {
        ObjectArrayGenerator generator = new ObjectArrayGenerator();
        Scanner kb = new Scanner(System.in);

        System.out.print("Input count:");
        doForObjects(generator.createObjects(kb.nextInt()));
    }

    public static void main(String[] args)
    {
        run();
    }
}
```

```java
package org.csystem.app.generator;

import org.csystem.generator.ObjectArrayGenerator;
import org.csystem.math.geometry.Point;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;

public class ObjectArrayListGeneratorDemoApp {
    //String, Integer, Boolean, Double, Character, Point, int [], String []
    private static void doForObjects(ArrayList<Object> objects)
    {
        Console.writeLine("Size = %d", objects.size());
        for (Object object : objects) {
            String typeStr = object.getClass().getName();

            Console.writeLine("-----------------------------------------------------------");
            Console.writeLine("Type:%s", typeStr);

            if (object instanceof String str)
                Console.writeLine("str:%s, upper:%s", str, str.toUpperCase());
            else if (object instanceof Integer) {
                int val = (int)object;

                Console.writeLine("%d * %d = %d", val, val, val * val);
            }
            else if (object instanceof Double) {
                double val = (double)object;

                Console.writeLine("%f + %f = %f", val, val, val + val);
            }
            else if (object instanceof Boolean) {
                boolean flag = (boolean)object;

                Console.writeLine("flag = %b", flag);
            }

            else if (object instanceof Character) {
                char ch = (char)object;

                Console.writeLine("ch = %c", ch);
            }
            else if (object instanceof Point point)
                Console.writeLine("Distance to origin of %s is %f", point.toString(), point.distance());
            else if (object instanceof int [] a) {
                ArrayUtil.print(a, " ", " -> ");
                Console.writeLine(ArrayUtil.sum(a));
            }
            else if (object instanceof String [] str) {
                ArrayUtil.print(str);
                Console.writeLine(StringUtil.join(str, '-'));
            }

            Console.writeLine("-----------------------------------------------------------");
        }
    }

    public static void run()
    {
        ObjectArrayGenerator generator = new ObjectArrayGenerator();
        doForObjects(generator.createUntilZero());
    }

    public static void main(String[] args)
    {
        run();
    }
}
```

```java
package org.csystem.generator;

import org.csystem.math.geometry.Point;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ObjectArrayGenerator {
    private final RandomGenerator m_randomGenerator = new Random();

    //String, Integer, Boolean, Double, Character, Point, int [], String []
    private Object createObject()
    {
        return switch (m_randomGenerator.nextInt(8)) {
            case 0 -> StringUtil.generateRandomTextTR(m_randomGenerator, m_randomGenerator.nextInt(5, 11));
            case 1 -> m_randomGenerator.nextInt(-128, 127);
            case 2 -> m_randomGenerator.nextBoolean();
            case 3 -> (double) m_randomGenerator.nextInt(-10, 10);
            case 4 -> (char)(m_randomGenerator.nextInt(26) + (m_randomGenerator.nextBoolean() ? 'A' : 'a'));
            case 5 -> Point.createCartesian(m_randomGenerator.nextDouble(-100, 100), m_randomGenerator.nextDouble(-100, 100));
            case 6 -> ArrayUtil.generateRandomArray(m_randomGenerator, m_randomGenerator.nextInt(5, 20), 0, 100);
            default -> StringUtil.generateRandomTextsTR(m_randomGenerator, m_randomGenerator.nextInt(5, 8), 5, 11);
        };
    }

    public Object [] createObjects(int count)
    {
        Object [] objects = new Object[count];

        for (int i = 0; i < count; ++i)
            objects[i] =  createObject();

        return objects;
    }

    public ArrayList<Object> createUntilZero()
    {
        ArrayList<Object> objects = new ArrayList<>();

        while (true) {
            Object o = createObject();

            if (o instanceof Integer val && val == 0)
                break;

            objects.add(o);
        }

        return objects;
    }
}
```

>*Bir tür için* `Class` *nesnesi **bir kez** yatratılır. Her istendiğinde o nesnenin referansı verilir*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        try {
            var typeStr = Console.readString("Input type:");
            var cls1 = Class.forName(typeStr);
            var cls2 = Class.forName(typeStr);

            Console.writeLine(cls1 == cls2 ? "Same object" : "Different objects");
        }
        catch (ClassNotFoundException ex) {
            Console.writeErrLine("Message:%s", ex);
        }
    }
}
```

>**Hatırlatma:** Bir program çalıştırılırken geçilen yazısal ifadelere "komut satırı argümanları (command line arguments) denir. Komut satırı argümanları main metodunun parametre değişkeni olan String dizisinden elde edilebilir. Programa hiç argüman geçilmezse dizi sıfır elemanlı olur.*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        for (var arg : args)
            Console.writeLine(arg);
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        if (args.length != 3) {
            Console.Error.writeLine("Wrong number of arguments");
            System.exit(1);
        }

        try {
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);
            var random = new Random();

            for (var i = 0; i < count; ++i)
                Console.writeLine(StringUtil.generateRandomTextEN(random, random.nextInt(min, bound)));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments!...");
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.Random;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(args.length, 3, "wrong number of arguments");

        try {
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);
            var random = new Random();

            for (var i = 0; i < count; ++i)
                Console.writeLine(StringUtil.generateRandomTextEN(random, random.nextInt(min, bound)));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments!...");
        }
    }
}
```

>`Class` *sınıfının* `getDeclaredXXXs` *metotları ile türün tüm elemanlarına erişim belirleyiciden bağımsız olarak erişilebilir. Bu metotlar ile taban sınıfın hiçbir bölümüne erişilemez. Bu metotlar ilgili elemana ilişkin metadata'sını temsil eden sınıf türünden dizi referansına geri dönerler. Örneğin* `getDeclaredMethods` *metodu ilgili ait ilişkin metotların metadata'larını içeren* `Method[]` *referansına geri döner. Eğer ilgili türün çağrılan metoda ilişkin elemanı yoksa, length'i sıfır olan dizi referansı elde edilir.* `Class` *sınıfının* `getXXXs` *metotları ile taban sınıf da dahil olmak üzere yalnıza public bölüme erişilebilir. Bu metotların sonu s olmayan (yani tekil) isme sahip olanları ile specific bir elemana erişilebilir.*
>
>`~/src/Projects/007-DemoTypeDeclaredElementsApp` ve `DemoTypeElementsApp` projelerini inceleyiniz

>*Aşağıdaki demo örnekte singleton olarak yazılmış bir sınıfın ctor'una çalışma zamanında erişilmiş ve nesne yaratılmıştır. Sınıfın lazy implementation olarak yazıldığına dikkat ediniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.demo.Singleton;

import java.lang.reflect.InvocationTargetException;

class Application {
    public static void run(String[] args)
    {

        try {
            var cls = Singleton.class;
            var ctor = cls.getDeclaredConstructor(int.class);

            ctor.setAccessible(true);
            var s = (Singleton)ctor.newInstance(10);
            ctor.setAccessible(false);

            Console.writeLine("Value:%d", s.getValue());
        }
        catch (NoSuchMethodException | SecurityException | InvocationTargetException | InstantiationException
               | IllegalAccessException ex) {
            Console.Error.writeLine("%s, %s", ex.getClass().getSimpleName(), ex.getMessage());
        }
    }
}
```

```java
package org.csystem.app.demo;

public class Singleton {
    private static Singleton ms_instance;
    private int m_value;

    private Singleton(int value)
    {
        m_value = value;
    }

    public static Singleton getInstance(int value)
    {
        if (ms_instance == null)
            ms_instance = new Singleton(value);

        return ms_instance;
    }

    public int getValue()
    {
        return m_value;
    }

    public void setValue(int value)
    {
        m_value = value;
    }
}
```

>*Aşağıdaki demo örnekte `singleton` olarak yazılmış enum class'ın ctor'una erişilip nesne **yaratılamadığına** dikkat ediniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.demo.Singleton;

import java.lang.reflect.InvocationTargetException;

class Application {
    public static void run(String[] args)
    {
        try {
            var cls = Singleton.class;
            var ctor = cls.getDeclaredConstructor();

            ctor.setAccessible(true);
            var s = (Singleton)ctor.newInstance();
            ctor.setAccessible(false);

            s.setValue(10);

            Console.writeLine("Value:%d", s.getValue());
        }
        catch (NoSuchMethodException | SecurityException | InvocationTargetException | InstantiationException
               | IllegalAccessException ex) {
            Console.Error.writeLine("%s, %s", ex.getClass().getSimpleName(), ex.getMessage());
        }
    }
}
```

```java
package org.csystem.app.demo;

public enum Singleton {
    INSTANCE;
    private int m_value;

    Singleton()
    {

    }

    public int getValue()
    {
        return m_value;
    }

    public void setValue(int value)
    {
        m_value = value;
    }
}
```

>*Bir annotation default olarak sentaktik bir elemana birden fazla eklenemez. Bir annotation'ın birden fazla eklenebilmesi için aşağıdaki adımlardan geçilmesi gerekir:*
>1. Annotion, `Repeatable` annotation'ı ile işaretlenir. Repeatable annotation'ının value attribute'u bir Class'ın bir annotation açılımı türündendir. value elemanı için geçilebilecek en tipik ifade `<annotation ismi>.class` ifadesidir.
>2. `Repeatable` annotation'ının value attribute'una verilen annotation'ın Repeatable annotation'ı ile işaretlenen annotation dizi türünden value isimli attribute'u olmalıdır.
>
>*Repeatable olarak işaretlenen annotation ile Repeatable annotation'ına verilen annotation'ın Target ve Retention'ları nasıl olmalıdır? Genel olarak aynı yapılsa da, Repeatable annotation'ına verilen annotation'ın Target kümesi, Repeatable annotation'ı ile işaretlenen annotation'ın Target bilgilerinin bir alt kümesi olması gerekir. Repeatable annotation'ı ile işaretlenen annotation'ın Repeatable annotation'ına verilen annotation'dan daha az erişilir olabilir. Bu anlamda retention için en yükseğinden düşüğüne doğru şu erişilebilirlik hiyerarşisi söylenebilir:*
>1. RUNTIME
>2. CLASS
>3. SOURCE
>
>*Tüm bu detaylara karşın pratikte (best practice) Target ve Retention bilgileri genel olarak aynı olur. Repeatable bir annotation ile birden fazla işaretleme işlemi, o repeatable annotation'a işaretlenen Repeatable annotation'ına verilen annotation ile de yapılabilir. Bunlar tamamen aynı anlamdadır.*
>
>*Aşağıdaki demo örnekte `foo` ve `bar` metotlarına ikişer tane* `Command` *annotation'ı eklenmiş olur. Örneği inceleyiniz*

```java
package org.csystem.app;

import java.lang.annotation.*;

class Sample {
    @Command("foo")
    @Command
    public void foo()
    {
        //...
    }

    @Commands({@Command, @Command("bar")})
    public void bar()
    {
        //...
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Commands.class)
@interface Command {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Commands {
    Command [] value();
}
```

>`~/src/Projects/009-CommandPromptApp` projesini ve `~/src/Libraries/ConsoleLib` içerisideki `CommandPrompt` sınıfı ve ilgili annotation'ları inceleyiniz

>**_Anahtar Notlar:_** Reflection, göreli yavaş bir işlemdir. Programcının reflection kullanması için bir gerekçesi olmalıdır. Reflection dışındaki bir çözüm yerine reflection kullanmak performansı olumsuz etkileyebilir. Benzer şekilde reflection yerine başka bir çözüme de gitmek performansı ve karmaşıklığı artırabilir. Java programcısı domain'e göre uygun kararı vermelidir

>*IDE'ler kendi içerisinde farklı terminolojiler kullanırlar. Örneğin Eclipse'de bir **"workspace"** kavramı vardır ve projeler bir **"workspace"** altında toplanırlar. IntelljIDEA'da bir proje açıldığında aslında bir workspace açılmış olur. Bu durumda proje altında uygulamalar **"module"** olarak isimlendirilir. Özetle Eclipse ve IntelliJIDEA için kavramlar şunlardır:*
>
>| Eclipse   | IntelliJ |
>|-----------|----------|
>| workspace | project  |
>| project   | module   |

>`for-each` *döngü deyimi aslında yapay bir döngü deyimidir. Programcı* `for-each` *döngü deyimini yazdığında derleyici tarafından bir kod üretilir. Bir sınıfın* `for-each` *döngü deyimi ile dolaşılabilmesi için **(iterable)** yine* `Java 5` *ile eklenen* `Iterable<T>` *arayüzünü desteklemesi gerekir. Aksi durumda **error** oluşur.*
>
>**_Anahtar Notlar:_** Bir veri yapısına ait elemanların, veri  yapısı içerisinde nasıl tutulduktan bağımsız olarak dolaşılabilmesi için kullanılan tasarım kalıbına (design pattern) "iterator" denir. Yani aslında veriler bir iterator yardımıyla tutulur. Iterator ile her adımda mantıksal bir sonraki veriye erişilir. Böylelikle programcı verilerin nasıl tutulduğundan bağımsız dolaşılan kodlar yazabilir.
>
>**_Anahtar Notlar:_** Diziler sentaks olarak for-each döngü deyimi ile kullanılabilir. Bu anlamda diziler `Iterable<T>` arayüzünü destekleyen türler değildir.
>
>`Iterable` arayüzünün `iterator` isimli bir tane abstract metodu vardır. Bu metot `Iterator<T>` arayüz referansına geri döner. Bu arayüzün `Java 8`'den önce 3 tane, `Java 8` ile birlikte 2 tane abstract metodu bulunmaktadır. `Java 8`'den önce `remove` isimli metot da abstract bir metottu, `Java 8` ile birlikte default metot kavramı eklendiği için bu metot `UnsupportedOperationException` fırlatacak şekilde default metot yapılmıştır. Duruma göre programcı override edebilir. `Java 8` ile eklenen ve yine default olan `forEachRemaining` metodu ileride ele alınacaktır. Java Language Specification'a göre bir türe ilişkin referans ile (dizi türü hariç) `for-each` döngü deyimi kullanıldığında aşağıdaki metotları çağrılacağı şekilde yaklaşık bir kod üretilir. Demo örnekteki `for-each` döngü deyimi için:

    Iterator<String> iter = texts.iterator();
    String s;

    while (iter.hasNext()) {
        s = iter.next();
        Console.writeLine(s);
    }
>*Şüphesiz bu döngü çeşitli şekillerde de yazılabilir. Yine şüphesiz programcının böyle bir döngü için for-each kullanması gerekir ancak for-each'in bu yapısını da bilmesi gerekir.*
>
>`~/src/GeneratorLib` kütüphanesindeki sınıfları ve test kodlarını inceleyiniz

>`org-csystem-generator` kütüphanesinin `11.0.0` sürümündeki sınıflar

```java
/*-------------------------------------------------------------
    FILE		: RandomDoubleGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	RandomDoubleGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.random;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;

public class RandomDoubleGenerator implements Iterable<Double> {
    private final RandomGenerator m_randomGenerator;
    private final double m_origin;
    private final double m_bound;
    private final int m_count;

    private static class RandomDoubleGeneratorIterator implements Iterator<Double> {
        final RandomDoubleGenerator doubleGenerator;
        int count;

        public RandomDoubleGeneratorIterator(RandomDoubleGenerator doubleGenerator)
        {
            this.doubleGenerator = doubleGenerator;
        }

        @Override
        public boolean hasNext()
        {
            return count < doubleGenerator.m_count;
        }

        @Override
        public Double next()
        {
            if (!hasNext())
                throw new NoSuchElementException("Can not generate a value!...");

            ++count;

            return doubleGenerator.m_randomGenerator.nextDouble(doubleGenerator.m_origin, doubleGenerator.m_bound);
        }
    }

    private RandomDoubleGenerator(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        m_randomGenerator = randomGenerator;
        m_origin = origin;
        m_bound = bound;
        m_count = count;
    }

    public static RandomDoubleGenerator of(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        return new RandomDoubleGenerator(randomGenerator, origin, bound, count);
    }

    @Override
    public Iterator<Double> iterator()
    {
        return new RandomDoubleGeneratorIterator(this);
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: RandomIntGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 2nd Mar 2024

	RandomIntGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.random;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;

public class RandomIntGenerator implements Iterable<Integer> {
    private final RandomGenerator m_randomGenerator;
    private final int m_origin;
    private final int m_bound;
    private final int m_count;

    private RandomIntGenerator(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        m_randomGenerator = randomGenerator;
        m_origin = origin;
        m_bound = bound;
        m_count = count;
    }

    public static RandomIntGenerator of(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        return new RandomIntGenerator(randomGenerator, origin, bound, count);
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return new Iterator<>() {
            int count;

            @Override
            public boolean hasNext()
            {
                return count < m_count;
            }

            @Override
            public Integer next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("Can not generate a value!...");

                count++;

                return m_randomGenerator.nextInt(m_origin, m_bound);
            }
        };
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: DoubleRange.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	DoubleRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.DoubleUnaryOperator;

public class DoubleRange implements Iterable<Double> {
    private final double m_a;
    private final double m_b;
    private final DoubleUnaryOperator m_unaryOperator;
    private class DoubleRangeIterator implements Iterator<Double> {
        double value = m_a;

        @Override
        public boolean hasNext()
        {
            return value <= m_b;
        }

        @Override
        public Double next()
        {
            if (!hasNext())
                throw new NoSuchElementException("No such element!...");

            var result = value;
            value = m_unaryOperator.applyAsDouble(value);

            return result;
        }
    }

    private DoubleRange(double a, double b, DoubleUnaryOperator unaryOperator)
    {
        m_a = a;
        m_b = b;
        m_unaryOperator = unaryOperator;
    }

    public static DoubleRange of(double a, double b, double delta)
    {
        if (delta <= 0)
            throw new IllegalArgumentException(String.format("Delta must be positive:%.20f", delta));

        return of(a, b, val -> val + delta);
    }

    public static DoubleRange of(double a, double b, DoubleUnaryOperator unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %f, b= %f", a, b));

        return new DoubleRange(a, b, unaryOperator);
    }

    @Override
    public Iterator<Double> iterator()
    {
        return new DoubleRangeIterator();
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: IntRange.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 2nd Mar 2024

	IntRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.IntUnaryOperator;

public class IntRange implements Iterable<Integer> {
    private final int m_a;
    private final int m_b;
    private final IntUnaryOperator m_unaryOperator;

    private IntRange(int a, int b, IntUnaryOperator unaryOperator)
    {
        m_a = a;
        m_b = b;
        m_unaryOperator = unaryOperator;
    }

    public static IntRange of(int a, int b)
    {
        return of(a, b, 1);
    }

    public static IntRange of(int a, int b, int step)
    {
        if (step <= 0)
            throw new IllegalArgumentException(String.format("Step must be positive:%d", step));

        return of(a, b, val -> val + step);
    }

    public static IntRange of(int a, int b, IntUnaryOperator unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %d, b= %d", a, b));

        return new IntRange(a, b, unaryOperator);
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return new Iterator<>() {
            int value = m_a;

            @Override
            public boolean hasNext()
            {
                return value <= m_b;
            }

            @Override
            public Integer next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No such element!...");

                var result = value;
                value = m_unaryOperator.applyAsInt(value);

                return result;
            }
        };
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: IntRangeLong.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 2nd Mar 2024

	IntToLongRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.LongUnaryOperator;

public class IntToLongRange implements Iterable<Long> {
    private final int m_a;
    private final int m_b;
    private final LongUnaryOperator m_unaryOperator;

    private IntToLongRange(int a, int b, LongUnaryOperator unaryOperator)
    {
        m_a = a;
        m_b = b;
        m_unaryOperator = unaryOperator;
    }

    public static IntToLongRange of(int a, int b)
    {
        return of(a, b, 1);
    }

    public static IntToLongRange of(int a, int b, int step)
    {
        if (step <= 0)
            throw new IllegalArgumentException(String.format("Step must be positive:%d", step));

        return of(a, b, val -> val + step);
    }

    public static IntToLongRange of(int a, int b, LongUnaryOperator unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %d, b= %d", a, b));

        return new IntToLongRange(a, b, unaryOperator);
    }

    @Override
    public Iterator<Long> iterator()
    {
        return new Iterator<>() {
            long value = m_a;

            @Override
            public boolean hasNext()
            {
                return value <= m_b;
            }

            @Override
            public Long next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No such element!...");

                var result = value;

                value = m_unaryOperator.applyAsLong(value);

                return result;
            }
        };
    }
}
```

>`org-csystem-generator` kütüphanesinin `12.0.0` sürümündeki sınıflar

```java
/*-------------------------------------------------------------
    FILE		: BaseGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator;

import java.util.function.Supplier;

public abstract class BaseGenerator<T> implements Iterable<T> {
    protected Supplier<T> supplier;
    protected String noSuchElementExceptionMessage;

    protected BaseGenerator()
    {
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: Generator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Generator<T> extends BaseGenerator<T> {
    protected UnaryOperator<T> unaryOperator;
    protected Predicate<T> predicate;
    protected Generator()
    {
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<>() {
            T value = supplier.get();

            @Override
            public boolean hasNext()
            {
                return predicate.test(value);
            }

            @Override
            public T next()
            {
                if (!hasNext())
                    throw new NoSuchElementException(noSuchElementExceptionMessage);

                var result = value;
                value = unaryOperator.apply(value);

                return result;
            }
        };
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: ValueGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class NValueGenerator<T> extends BaseGenerator<T> {
    protected Supplier<T> valueSupplier;
    protected String noSuchElementExceptionMessage;
    protected long count;

    protected NValueGenerator()
    {
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<>() {
            int count;

            @Override
            public boolean hasNext()
            {
                return count < NValueGenerator.this.count;
            }

            @Override
            public T next()
            {
                if (!hasNext())
                    throw new NoSuchElementException(noSuchElementExceptionMessage);

                ++count;

                return valueSupplier.get();
            }
        };
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: RandomDoubleGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	RandomDoubleGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.random;

import org.csystem.generator.NValueGenerator;

import java.util.random.RandomGenerator;

public class RandomDoubleGenerator extends NValueGenerator<Double> {
    private RandomDoubleGenerator(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        super.valueSupplier = () -> randomGenerator.nextDouble(origin, bound);
        super.noSuchElementExceptionMessage = "Can not generate a value!...";
        super.count = count;
    }

    public static RandomDoubleGenerator of(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        return new RandomDoubleGenerator(randomGenerator, origin, bound, count);
    }

}
```

```java
/*-------------------------------------------------------------
    FILE		: RandomIntGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 23rd Mar 2024

	RandomIntGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.random;

import org.csystem.generator.NValueGenerator;

import java.util.random.RandomGenerator;

public class RandomIntGenerator extends NValueGenerator<Integer> {
    private RandomIntGenerator(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        super.valueSupplier = () -> randomGenerator.nextInt(origin, bound);
        super.noSuchElementExceptionMessage = "Can not generate a value!...";
        super.count = count;
    }

    public static RandomIntGenerator of(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        return new RandomIntGenerator(randomGenerator, origin, bound, count);
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: DoubleRange.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	DoubleRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import org.csystem.generator.Generator;

import java.util.function.UnaryOperator;

public class DoubleRange extends Generator<Double> {
    private final double m_a;
    private final double m_b;

    private DoubleRange(double a, double b, UnaryOperator<Double> unaryOperator)
    {
        m_a = a;
        m_b = b;
        this.unaryOperator = unaryOperator;
        predicate = val -> val <= m_b;
        supplier = () -> m_a;
        noSuchElementExceptionMessage = "No such element!...";
    }

    public static DoubleRange of(double a, double b, double delta)
    {
        if (delta <= 0)
            throw new IllegalArgumentException(String.format("Delta must be positive:%.20f", delta));

        return of(a, b, val -> val + delta);
    }

    public static DoubleRange of(double a, double b, UnaryOperator<Double> unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %f, b= %f", a, b));

        return new DoubleRange(a, b, unaryOperator);
    }

}
```

```java
/*-------------------------------------------------------------
    FILE		: IntRange.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	IntRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import org.csystem.generator.Generator;

import java.util.function.UnaryOperator;

public class IntRange extends Generator<Integer> {
    private final int m_a;
    private final int m_b;

    private IntRange(int a, int b, UnaryOperator<Integer> unaryOperator)
    {
        m_a = a;
        m_b = b;
        this.unaryOperator = unaryOperator;
        predicate = val -> val <= m_b;
        supplier = () -> m_a;
        noSuchElementExceptionMessage = "No such element!...";
    }

    public static IntRange of(int a, int b)
    {
        return of(a, b, 1);
    }

    public static IntRange of(int a, int b, int step)
    {
        if (step <= 0)
            throw new IllegalArgumentException(String.format("Step must be positive:%d", step));

        return of(a, b, val -> val + step);
    }

    public static IntRange of(int a, int b, UnaryOperator<Integer> unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %d, b= %d", a, b));

        return new IntRange(a, b, unaryOperator);
    }
}
```

```java
/*-------------------------------------------------------------
    FILE		: IntRangeLong.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 23rd Mar 2024

	IntToLongRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import org.csystem.generator.Generator;

import java.util.function.UnaryOperator;

public class IntToLongRange extends Generator<Long> {
    private final int m_a;
    private final int m_b;

    private IntToLongRange(int a, int b, UnaryOperator<Long> unaryOperator)
    {
        m_a = a;
        m_b = b;
        this.unaryOperator = unaryOperator;
        predicate = val -> val <= m_b;
        supplier = () -> (long)m_a;
        noSuchElementExceptionMessage = "No such element!...";
    }

    public static IntToLongRange of(int a, int b)
    {
        return of(a, b, 1);
    }

    public static IntToLongRange of(int a, int b, int step)
    {
        if (step <= 0)
            throw new IllegalArgumentException(String.format("Step must be positive:%d", step));

        return of(a, b, val -> val + step);
    }

    public static IntToLongRange of(int a, int b, UnaryOperator<Long> unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %d, b= %d", a, b));

        return new IntToLongRange(a, b, unaryOperator);
    }
}
```

>**Optional Sınıfları:**
>
>*Programlamada sıklıkla karşımıza çıkan bir durum vardır: Bir koşul sağlandığında bir bilgiye elde etmek isteriz. Örneğin, T.C. Kimlik numarasına bir kişinin bilgilerini veren bir metot yazacak olalım. Bu metot parametre olarak aldığı kimlik numarasında bir kişi olmadığı durumda ne dönecektir? Şüphesiz, böylesi bir metot muhtemel bir sınıf türünden referansa geri dönecektir. Bu durumda bulunamadığı koşulda `null` adrese geri dönülebilir. Ancak `null` adrese geri dönülmesi metodu çağıran programcı açısından fark edilmeden kullanımda* `NullPointerException` *oluşumuna da yol açabilecektir. Yine örneğin bir metot exception fırlatmadan yazıyı sayıya çevirmek isterse, geri dönüş değeri olarak ne dönecektir? Eğer* `int` *türüne geri dönerse geçersiz değer için hangi değere geri dönecektir? Şüphesiz, bu metot* `Integer` *referansına geri dönebilir ve dönüştürülememesi durumunda `null` değere geri döner. Bu da görece olarak okunabilir **olmaz**. Üstelik yine `null` adres döndürülmüş olur. Yine örneğin, bir kişinin adı, ikinci adı ve soyadı bilgileri tutulduğunda ikinci adı olmayan kişiler için bu bilgi `null` olarak veya boş string tutulabilir. Bu da yine iyi bir tasarım **değildir**.*
>
>*Programlamada böylesi durumlarda genel adı **"nullable type"** olan (buradaki null terimi null adres anlamında değildir. Olmama kavramını temsil eder) **"optional"** sınıfları kullanılabilir.* `Java 8` *ile birlikte 4 tane optional sınıfı JavaSE'ye eklenmiştir:*

    Optional<T>
    OptionalInt
    OptionalDouble
    OptionalLong
>*Optional sınıflarının* `empty` *metotları, boş bir optional oluşturmak için kullanılır.*
>
>*Optional sınıflarının ctor'ları private'dır. Optional sınıflarının* `of` *metotları ile dolu bir optional, ilgili değer verilerek yaratılabilir.*
>
>*Optional sınıflarının* `getXXX` *metotları ile ilgili optional nesnesi içerisindeki veri elde edilebilir. Boş bir optional nesnesi için* `getXXX` *metotları* `NoSuchElementException` *fırlatırlar.*
>
>*Optional sınıflarının* `isPresent` *isimli metodu ile nesnenin dolu olup olmadığı test edilebilir.* `Optional` *sınıflarının* `isEmpty` *isimli metodu ile nesnenin boş olup olmadığı test edilebilir.* `isEmpty` *metodu* `Java 11` *ile eklenmiştir.*
>
>*Optional sınıflarının* `ifPresent` *metodu **bir callback** alır ve eğer optional dolu ise callback'i çağırır.* `ifPresentOrElse` *metodu **iki tane callback** alır birinci callback dolu ise çağrılır, ikinci callback ise boş ise çağrılır. Optional sınıflarının* `ifPresentOrElse` *metodu* `Java 9` *ile eklenmiştir.*
>
>`Optional` sınıflarının `orElse` metodu optional dolu ise içerisindeki değeri, boş ise parametresi ile aldığı değeri döndürür. `orElseThrow` metodunun parametresiz overload'u boş bir optional için `NoSuchElementException` fırlatır. `orElseThrow` metodunun `Supplier<? extends Throwable>` parametreli overload'u ile istenilen bir exception sınıfının  fırlatılması sağlanabilir. orElseGet metodu optional'ın boş olması durumunda değerin elde edileceği supplier'ı callback olarak alır. `Optional<T>` sınıfının or metodu, optional boş ise parametresi ile aldığı callback'den elde edilen `Optional<T>`'ye geri döner.
>
>*Optional sınıflarının* `map` *metotları ile bir optional'dan **başka** bir optional elde edilebilir.*
>
>`Optional<T>` *sınıfının* `filter` *metodu, optional parametresi aldığı koşulu (predicate) sağlıyorsa dolu, sağlşamıyorsa boş optional nesnesine geri döner.*
>
>`Optional<T>` *sınıfının* `ofNullable` *metodu parametresi ile null referans alırsa boş optional, null dişı bir referans alırsa referans ile birlikte dolu optional'a geri döner.*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);
            var optInt = generator.findFirst(NumberUtil::isPrime);

            if (optInt.isPresent())
                Console.writeLine("First prime number is:%d", optInt.getAsInt());
            else
                Console.writeLine("No prime number found");
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);
            var optInt = generator.findFirst(NumberUtil::isPrime);

            if (optInt.isEmpty())
                Console.writeLine("No prime number found");
            else
                Console.writeLine("First prime number is:%d", optInt.getAsInt());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);
            var optInt = generator.findFirst(NumberUtil::isPrime);

            optInt.ifPresentOrElse(val -> Console.writeLine("First prime number is:%d", val),
                    () -> Console.writeLine("No prime number found"));
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);
            var optInt = generator.findFirst(NumberUtil::isPrime);

            optInt.ifPresent(val -> Console.writeLine("First prime number is:%d", val));
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            try {
                var count = Console.readUInt("Input count:");
                if (count == 0)
                    break;

                var min = Console.readUInt("Input origin:");
                var bound = Console.readUInt("Input bound:");
                var generator = RandomIntGenerator.of(new Random(), min, bound, count);
                var optInt = generator.findFirst(NumberUtil::isPrime);
                var value = optInt.orElse(-1);

                Console.writeLine("Value:%d", value);
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Invalid values!...");
            }
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.NoSuchElementException;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            try {
                var count = Console.readUInt("Input count:");
                if (count == 0)
                    break;

                var min = Console.readUInt("Input origin:");
                var bound = Console.readUInt("Input bound:");
                var generator = RandomIntGenerator.of(new Random(), min, bound, count);
                var optInt = generator.findFirst(NumberUtil::isPrime);
                var value = optInt.orElseThrow();

                Console.writeLine("Value:%d", value);
            }
            catch (NoSuchElementException ignore) {
                Console.writeLine("No prime number found!...");
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Invalid values!...");
            }
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.InputMismatchException;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            try {
                var count = Console.readUInt("Input count:");
                if (count == 0)
                    break;

                var min = Console.readUInt("Input origin:");
                var bound = Console.readUInt("Input bound:");
                var generator = RandomIntGenerator.of(new Random(), min, bound, count);
                var optInt = generator.findFirst(NumberUtil::isPrime);
                var value = optInt.orElseThrow(InputMismatchException::new);

                Console.writeLine("Value:%d", value);
            }
            catch (InputMismatchException ignore) {
                Console.writeLine("No prime number found!...");
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Invalid values!...");
            }
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        while (true) {
            try {
                var count = Console.readUInt("Input count:");
                if (count == 0)
                    break;

                var min = Console.readUInt("Input origin:");
                var bound = Console.readUInt("Input bound:");
                var generator = RandomIntGenerator.of(random, min, bound, count);
                var optInt = generator.findFirst(NumberUtil::isPrime);
                var value = optInt.orElseGet(() -> random.nextInt(-99, 0));

                Console.writeLine("Value:%d", value);
            }
            catch (Throwable ex) {
                Console.Error.writeLine("Invalid values!...");
            }
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.sensor.entity.Sensor;
import org.csystem.app.sensor.factory.SensorFactory;
import org.csystem.util.thread.ThreadUtil;

class Application {
    public static void run(String[] args)
    {
        var factory = new SensorFactory();

        while (true) {
            Console.writeLine("------------------------------------------------");
            var optSensor = factory.findFirst(s -> s.getHost().contains("127"));

            if (optSensor.isPresent()) {
                Console.writeLine(optSensor.get());
                optSensor.map(Sensor::getHost).ifPresent(Console::writeLine);
            }
            else
                Console.writeLine("Not found!...");

            Console.writeLine("------------------------------------------------");
            ThreadUtil.sleep(1000);
        }
    }
}
```

```java
package org.csystem.app.sensor.entity;

public class Sensor {
    private int m_id;
    private String m_name;
    private String m_host;
    private int m_port;

    public Sensor(int id, String name, String host, int port)
    {
        m_id = id;
        m_name = name;
        m_host = host;
        m_port = port;
    }

    public int getId()
    {
        return m_id;
    }

    public void setId(int id)
    {
        m_id = id;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getHost()
    {
        return m_host;
    }

    public void setHost(String host)
    {
        m_host = host;
    }

    public int getPort()
    {
        return m_port;
    }

    public void setPort(int port)
    {
        m_port = port;
    }

    //...

    @Override
    public String toString()
    {
        return String.format("%d, %s, %s:%d", m_id, m_name, m_host, m_port);
    }
}
```

```java
package org.csystem.app.sensor.factory;

import org.csystem.app.sensor.entity.Sensor;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.random.RandomGenerator;

public class SensorFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    private String createRandomHostAddress()
    {
        return String.format("%d.%d.%d.%d", m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(256),
                m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(1, 256));
    }

    private Sensor createRandomSensor()
    {
        var id = m_randomGenerator.nextInt(1000);
        var name = StringUtil.generateRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(5, 41));
        var host = createRandomHostAddress();
        var port = m_randomGenerator.nextInt(1024, 65536);

        return new Sensor(id, name, host, port);
    }

    public Optional<Sensor> findFirst(Predicate<Sensor> predicate)
    {
        var count = m_randomGenerator.nextInt(1, 100);

        while (count-- > 0) {
            var sensor = createRandomSensor();

            if (predicate.test(sensor))
                return Optional.of(sensor);
        }

        return Optional.empty();
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.sensor.entity.Sensor;
import org.csystem.app.sensor.factory.SensorFactory;
import org.csystem.util.thread.ThreadUtil;

class Application {
    public static void run(String[] args)
    {
        var factory = new SensorFactory();

        while (true) {
            Console.writeLine("------------------------------------------------");
            var optSensor = factory.findFirst(s -> s.getHost().contains("127"));

            optSensor.map(Sensor::getHost).ifPresentOrElse(Console::writeLine, () -> Console.writeLine("Not found!..."));
            Console.writeLine("------------------------------------------------");
            ThreadUtil.sleep(1000);
        }
    }
}
```

```java
package org.csystem.app.sensor.entity;

public class Sensor {
    private int m_id;
    private String m_name;
    private String m_host;
    private int m_port;

    public Sensor(int id, String name, String host, int port)
    {
        m_id = id;
        m_name = name;
        m_host = host;
        m_port = port;
    }

    public int getId()
    {
        return m_id;
    }

    public void setId(int id)
    {
        m_id = id;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getHost()
    {
        return m_host;
    }

    public void setHost(String host)
    {
        m_host = host;
    }

    public int getPort()
    {
        return m_port;
    }

    public void setPort(int port)
    {
        m_port = port;
    }

    //...

    @Override
    public String toString()
    {
        return String.format("%d, %s, %s:%d", m_id, m_name, m_host, m_port);
    }
}
```

```java
package org.csystem.app.sensor.factory;

import org.csystem.app.sensor.entity.Sensor;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.random.RandomGenerator;

public class SensorFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    private String createRandomHostAddress()
    {
        return String.format("%d.%d.%d.%d", m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(256),
                m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(1, 256));
    }

    private Sensor createRandomSensor()
    {
        var id = m_randomGenerator.nextInt(1000);
        var name = StringUtil.generateRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(5, 41));
        var host = createRandomHostAddress();
        var port = m_randomGenerator.nextInt(1024, 65536);

        return new Sensor(id, name, host, port);
    }

    public Optional<Sensor> findFirst(Predicate<Sensor> predicate)
    {
        var count = m_randomGenerator.nextInt(1, 100);

        while (count-- > 0) {
            var sensor = createRandomSensor();

            if (predicate.test(sensor))
                return Optional.of(sensor);
        }

        return Optional.empty();
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.sensor.factory.SensorFactory;
import org.csystem.util.thread.ThreadUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var factory = new SensorFactory();

        while (true) {
            Console.writeLine("------------------------------------------------");
            var optSensor = factory.findFirst(s -> s.getHost().contains("127"));
            var port = random.nextInt(1024, 65536);

            optSensor = optSensor.filter(s -> s.getPort() < port);
            Console.writeLine("Por:%d", port);
            optSensor.ifPresentOrElse(Console::writeLine, () -> Console.writeLine("No sensor found!..."));
            Console.writeLine("------------------------------------------------");
            ThreadUtil.sleep(1000);
        }
    }
}
```

```java
package org.csystem.app.sensor.entity;

public class Sensor {
    private int m_id;
    private String m_name;
    private String m_host;
    private int m_port;

    public Sensor(int id, String name, String host, int port)
    {
        m_id = id;
        m_name = name;
        m_host = host;
        m_port = port;
    }

    public int getId()
    {
        return m_id;
    }

    public void setId(int id)
    {
        m_id = id;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getHost()
    {
        return m_host;
    }

    public void setHost(String host)
    {
        m_host = host;
    }

    public int getPort()
    {
        return m_port;
    }

    public void setPort(int port)
    {
        m_port = port;
    }

    //...

    @Override
    public String toString()
    {
        return String.format("%d, %s, %s:%d", m_id, m_name, m_host, m_port);
    }
}
```

```java
package org.csystem.app.sensor.factory;

import org.csystem.app.sensor.entity.Sensor;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.random.RandomGenerator;

public class SensorFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    private String createRandomHostAddress()
    {
        return String.format("%d.%d.%d.%d", m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(256),
                m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(1, 256));
    }

    private Sensor createRandomSensor()
    {
        var id = m_randomGenerator.nextInt(1000);
        var name = StringUtil.generateRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(5, 41));
        var host = createRandomHostAddress();
        var port = m_randomGenerator.nextInt(1024, 65536);

        return new Sensor(id, name, host, port);
    }

    public Optional<Sensor> findFirst(Predicate<Sensor> predicate)
    {
        var count = m_randomGenerator.nextInt(1, 100);

        while (count-- > 0) {
            var sensor = createRandomSensor();

            if (predicate.test(sensor))
                return Optional.of(sensor);
        }

        return Optional.empty();
    }
}
```

>`Iterable<T>` *arayüzünün* `forEach` *default metodu sırasıyla tüm değerler için aldığı callback'i çağırır. Bu metot tipik olarak for-each döngü deyiminin fonksiyonel kullanım karşılığıdır. Bu metot default olarak for-each döngü deyimi kullanılarak implemente edilmiştir. Bu metot iterable nesne üzerinde belirli koşullar (concurrent modification policy) sağlanmamışsa belirsiz (unspecified) bir davranış sunar. Bu koşullar ileride ele alınacaktır*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);

            generator.forEach(val -> Console.write("%d ", val));
            Console.writeLine();
        }
    }
}
```

>`Iterator<T>` *arayüzünün* `forEachRemaining` *metodu iteratörün kalınan yerinden itibaren dolaşımı sağlar. Bu metot da yine bir **callback** alır*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.generator.random.RandomIntGenerator;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var count = Console.readInt("Input count:");
            if (count <= 0)
                break;

            var min = Console.readInt("Input origin:");
            var bound = Console.readInt("Input bound:");
            var generator = RandomIntGenerator.of(new Random(), min, bound, count);
            var iter = generator.iterator();

            if (iter.hasNext()) iter.next();
            if (iter.hasNext()) iter.next();

            iter.forEachRemaining(val -> Console.write("%d ", val));
            Console.writeLine();
        }
    }
}
```

>*Anımsanacağı gibi türemiş sınıf türünden bir referans taban sınıf türünden bir referansa doğrudan atanabilir. Buna* `upcasting` *denir. Generic bir sınıfın türemiş sınıf açılımı, aynı generic sınıfın taban sınıf açılımına doğrudan **atanamaz**. Buna **"invariant/invariance"** özellik denir. Doğrudan atama işleminin yapılabilmesi için generic sınıfın taban sınıf açılımında* `? extends` *sentaksı kullanılmalıdır. Bu sentaks ile bir **üst sınır** belirlenmiş olur. Doğrudan atama işleminin bu şekilde yapılması durumuna **"covariant/covariance"** özellik denir.*
>
>*Generic bir sınıfın taban sınıf açılımı, aynı generic sınıfın türemiş sınıf açılımına doğrudan atanamaz. Bunun yapılabilmesi için türemiş sınıf açılımında* `? super` *sentaksı kullanılmalıdır. Bu sentaks ile bir **alt sınır** belirlenmiş olur. Doğrudan atama işleminin bu şekilde yapılması durumuna **"contravariant/contravariance"** özellik denir.*
>
>*Yukarıdaki durumlar arayüzler içinde benzer şekilde söz konusudur. `covariant` ve `contravariant` özelliklerin diğer detayları ileride ele alınacaktır.*

>*Aşağıdaki demo örnekte `invariant` durumdan dolayı **error** oluşur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var ints = new ArrayList<Integer>();
        var doubles = new ArrayList<Double>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;
            ints.add(val);
            doubles.add(val * 1.654);
        }

        Util.print(ints); //error
        Util.print(doubles); //error
    }
}

class Util {
    public static void doWork(ArrayList<Number> numbers, Consumer<Number> consumer)
    {
        numbers.forEach(consumer);
    }

    public static void print(ArrayList<Number> numbers)
    {
        doWork(numbers, Console::writeLine);
    }
}
```

>*Aşağdaki demo örnekte `covariance` özellikten dolayı **doğrudan atama gerçekleşir***

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var ints = new ArrayList<Integer>();
        var doubles = new ArrayList<Double>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;
            ints.add(val);
            doubles.add(val * 1.654);
        }

        Util.print(ints);
        Util.print(doubles);
    }
}

class Util {
    public static void doWork(ArrayList<? extends Number> numbers, Consumer<Number> consumer)
    {
        numbers.forEach(consumer);
    }

    public static void print(ArrayList<? extends Number> numbers)
    {
        doWork(numbers, Console::writeLine);
    }
}
```

>*Aşağdaki demo örnekte `contravariance` özellikten dolayı **doğrudan atama gerçekleşir***

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayList;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var numbers = new ArrayList<Number>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            numbers.add(val);
        }

        Util.print(numbers);
    }
}

class Util {
    public static void print(ArrayList<? super Integer> ints)
    {
        ints.forEach(Console::writeLine);
    }
}
```

>`Collection<E>` arayüzü `Iterable<T>` arayüzünden türetilmiştir. Neredeyse tüm collection sınıfların Collection parametreli bir ctor'u vardır. Ayrca `Collection<T>` arayüzünün Collection parametreli `addAll` metodu da bulunmaktadır. `Collection<E>` arayüzü adeta bir collection olma anlaşmasıdır (contract). Örneğin `Collection<E>` arayüzünü implemente eden bir sınıf `addAll` metodu ile başka bir `Collection<E>` arayüzünü implemente eden collection sınıftaki verileri alabilir. Yani `Collection` arayüzünü implemente eden sınıflar arasında uygun açılıma göre veri transferi yapılabilir.
>
>`Collection<E>` arayüzünün `add` metodu ilgili collection'a ekleme yapmak için kullanılır. Bu metodun geri dönüş değeri `boolean` türdendir. `add` metodu, collection sınıfın algoritmasına göre ekleme işleminin başarı durumuna geri döner. Örneğin, `ArrayList`'in `add` metodu her zaman true dönecek şekilde override edilmiştir. Ancak örneğin, ileride göreceğimiz `HashSet<T>` sınıfının add metodu aynı elemandan collection içerisinde varsa false değerine geri dönecek şekilde override edilmiştir. `HashSet<T>` collection sınıfı ileride ele alınacaktır.
>
>`Collection<E>` arayüzünün `addAll` metodu `Collection<? super E>` parametrelidir. Bu metot tipik olarak başka bir collection sınıfın elemanlarının kolaylıkla eklenmesi için kullanılır.
>
>`Collection<E>` arayüzünün `clear` metodu collection içerisinde tutulan elemanların tamamını silmek için kullanılır.
>
>`Collection<E>` arayüzünün `contains` metodu tipik olarak bir elemanın collection içerisinde olup olmadığını test etmek için kullanılır. Bu metot null değeri aranmıyorsa equals metodunu çağırır.
>
>`Collection<E>` arayüzünün `isEmpty` metodu collection'ın boş olup olmadığını test etmek için kullanılır.
>
>`Collection<E>` arayüzünün `remove` metodu parametresi ile aldığı Object referansına göre var olan elemanı siler. Yine hangi elemanı sileceğini eğer null değeri aranmıyorsa equals metoduna bakarak anlar.
>
>`Collection<E>` arayüzünün `size` metodu ilgili collection'da tutulan eleman sayısına geri döner.
>
>`Collection<E>` arayüzünün `toArray` metotları ile collection içerisindeki elemanlardan oluşan dizi elde edilebilir.
>
>`Collection<E>` arayüzünün bazı metotları konular içerisinde ele alınacaktır.

>Aşağıdaki demo örnekte `TreeSet<E>` collection'ı içerisindeki elemanlar `ArrayList<E>` collection'ına `addAll` metoduyla eklenmiştir. `TreeSet<E>` collection sınıfı ileride ele alınacaktır. Demo örnekte collection'lar arasındaki veri transferinin **polimorfik** olduğuna odaklanınız

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var numberSet = new TreeSet<Integer>();
        var numberList = new ArrayList<>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            numberSet.add(val);
        }

        numberList.add(1000);
        numberList.addAll(numberSet);

        numberList.forEach(val -> Console.write("%d ", val));
        Console.writeLine();
        numberSet.forEach(val -> Console.write("%d ", val));
    }
}
```

>`Collection<E>` arayüzünden pek çok türetilmiştir. Bu arayüzlerin bazıları aslında collection sınıfları kategorize etmektedir. `Collection<E>` arayüzünden türetilen ve collection sınıfları kategorize eden bazı arayüzler ve özetle anlamları şunlardır:
>
>`List<E>`: Aralarında **öncelik-sonralık** ilişkisi olan collection sınıfların arayüzüdür. Bu arayüzü implemente eden collection sınıflara liste tarzı collection sınıflar da denilmektedir. Örneğin, `ArrayList` sınıfı bu arayüzü implemente etmiştir.
>
>`Set<E>`: **Küme** tarzı collection sınıfların arayüzüdür. Bu arayüzler Matematik'teki küme kavramını temsil ederler. Örneğin, eklenen elemanların her birinden birer tane olmasını ve elemanların sıralı **(sorted)** olarak tutulmasını sağlayan `TreeSet<E>` collection sınıfı bu arayüzü implemente etmiştir.
>
>`Queue<E>`: **Kuyruk** tarzı collection sınıfların arayüzüdür. Örneğin bağlı liste veri yapısını(linked list) temsil eden `LinkedList<E>` collection sınıfı bu arayüzü de implemente eder.
>
>`Deque<E>`: **Baştan ve sondan** büyüyebilen (double ended queue) collection sınıfların arayüzüdür. Örneğin baştan ve sondan **dinamik** olarak büyüyebilen diz veri yapısını temsil eden `ArrayDeque<E>` collection sınıfı bu arayüzü
implemente etmiştir.
>
>*Yukarıdaki arayüzler dışında* `Collection` *sınıfından türetilmiş arayüzler bulunmaktadır. Burada yalnızca yukarıdaki arayüzler ve onları implemente eden çok kullanılan sınıflar ele alınacaktır.*

>`List<E>` *arayüzünü implemente eden sınıflardan bazıları şunlardır:*

    ArrayList<E>
    Vector<E>
    LinkedList<E>
    Stack<E>
>Bu arayüzde `Collection<E>` arayüzüne ek olarak listeye ilişkin metotlar vardır. Örneğin indeks parametreli `get` abstract metodu `Collection<E>` arayüzünde yoktur, bu arayüzde bulunur.
>
>Anımsanacağı gibi `ArrayList<E>` ve `Vector<E>` **dinamik** büyüyen dizi veri yapısını temsil eden `Collection` sınıflardır. Zorunlu olmadıkça `Vector<E>` sınıfı yerine `ArrayList<E>` sınıfı kullanılmalıdır. Bu durum `Vector<E>` sınıfının dokümanlarında da belirtilmiştir. Detaylar şu aşamada önemsizdir.
>
>JavaSE'de bir grup **adaptör** collection sınıf bulunmaktadır. Bir collection sınıfı kullanarak yazılmış collection sınıflara genel olarak "adapter collection class" denilmektedir. `Stack` sınıfı `Vector` sınıfından türetilmiş ve **LIFO** sistemine göre çalışan bir collection sınıftır. Bu anlamda `Stack<E>` veri yapısı `Vector<E>` sınıfı gibi de davranır. Şüphesiz stack kullanmanın amacı ile stack'in vector gibi kullanılması çoğu zaman anlamsızdır. Stack veri yapısına ilişkin genel işlemler şunlardır: `push`, `pop`. 
>
>`push` stack'e ekleme yapmak anlamında (mantıksal olarak sona ekleme), `pop` ise sondaki veriyi silme anlamında kullanılır. JavaSE'deki `Stack<E>` sınıfının `pop` metodu son elemanı siler ve son elemana ilişkin referansa geri döner. `Stack<E>` sınıfının `push` metodu da eklenen elemana ilişkin referansı döndürür. `Stack` sınıfının `peek` metodu son push edilen elemana ilişkin referansa geri döner ancak elemanı silmez. `pop` ve `peek` metotları stack boş ise `EmptyStackException` fırlatır. `Stack` sınıfının `empty` isimli metodu stack'in boş olup olmadığını test etmek için kullanılabilir. `Stack` sınıfına ayrıca `search` isimli bir metot da eklenmiştir. `search` metodu arama yapmak için kullanılır. Bulursa bulduğu elemanın sıra numarasına geri döner. Bu sıra numarası stack'in en tepesinden (yani son eklenen elemana göre) 1'den başlar. Eleman bulunamazsa metot -1 değerine geri döner. Stack sınıfının yalnızca default ctor'u vardır.
>
>`List<E>` arayüzünü destekleyen önemli bir collection sınıf da `LinkedList<E>` sınıfıdır. Bu sınıf tipik olarak bir bağlı listeyi (linked list) temsil eder. Bağlı liste, aralarında öncelik sonralık ilişkisi olan ancak bellekte peş peşe gelmek zorunda olmayan liste tarzı bir veri yapısıdır. Bu veri yapısında tutulan elemanlardan **"node"**'larda tutulur. Bir node içerisinde elemanla birlikte bir sonraki elemanın bulunduğu node'un adresi de tutulur. Bağlı listeler genel olarak **"doubly"** ve **"singly"** olarak iki gruba ayrılır. Doubly linked list'lerde bir node içerisinde kendisinden önce gelen elemanın bulunduğu node'un adresi de tutulur. Singly linked list'lerde yalnızca sonraki elemanın bulunduğu node'un adresi tutulur.
>
>JavaSE'de `LinkedList<E>` sınıfı yalnızca liste tarzı bir collection sınıf değildir. Aynı zamanda başka arayüzleri de destekleyerek daha yetenekli hale getirilmiştir.
>
>*Bağlı Listelere Neden Gereksinim Duyulur?*
>- Bağlı listelerde elemanların bellekte peş peşe olarak bulunması gerekmez. Böylece peş peşe bellek ihtiyacının **karşılanamayabileceği** durumda bağlı liste tercih edilir. Peş peşelik bölünmeye **(fragmentation)** yol açar. Bölünme de bellek verimini düşürme eğilimindedir. Özellikle, heap'de tahsis edilen diziler (ki Java'da diziler hep heap'de tahsis edilir) söz konusu olduğunda bağlı listeler daha verimli olma eğilimindedir. Bu durum Java'da çok karşımıza çıkmasa da aşağı seviyeli bazı uygulamalar bilinmesinde fayda vardır.
>- Çok sayıda delete işleminin yapıldığı durumlarda bağlı listeler tercih edilebilir. Örneğin, bir dizide bir elemanı silme işlemi kaydırma yapılacağından, bağlı listede silme işlemine göre maliyetlidir.
>- JavaSE'de `LinkedList<E>` sınıfı `List<E>` dışında başka arayüzleri de desteklediği için daha yeteneklidir denebilir. Örneğin, `LinkedList<E>` `Deque<E>` arayüzünü de desteklediği için "double ended queue (deque)" olarak da kullanılabilmektedir. Deque veri yapısı ve `Deque<E>` arayüzü ileride ele alınacaktır.
>
>*Bağlı Listelerle Dizilerin Karşılaştırılması:*
>- Belli bir indeksteki elemana erişim dizilerde O(1) karmaşıklıktadır ancak bağlı listelerde belirli bir indekteki elemana erişmek O(n) karmaşıklıktadır. Bu nedenle elemana erişimin çok fazla yapıldığı sistemlerde normal dizler yada dinamik büyüyen diziler tercih edilmelidir.
>- Node'u bilinen bir elemanın önüne ya da gerisine insert etme işlemi bağlı listelerde `O(1)` karmaşıklıktadır ancak dizilerde insert işlemi `O(n)` karmaşıklıktadır. Tabii, tekli bağlı listelerde bilinen bir node'un öncesine insert işlemi de `O(n)` karmaşıklıktadır. O halde insert işleminin yoğun yapıldığı durumlarda bağlı listeler tercih edilir.
>- Node'u bilinen bir elemanın silinmesi bağlı listelerde `O(1)` karmaşıklıktadır ancak dizilerde `O(n)` karmaşıklıktadır. Tabii tekli bağlı listelerde silinecek düğümün değil ondan önceki düğümün adresi bilinmelidir.
>
>**_Anahtar Notlar:_** JavaSE'de `LinkedList<E>` sınıfının düğümlerine programcı erişemez. Dolayısıyla bu sınıf için düğümün adresinin bilinmesi doğrudan mümkün değildir. `List<E>` interface'i dolayısıyla insert işlemi de index yoluyla yapılır.
>- Başa eleman eklemek dizilerde `O(n)` karmaşıklıktadır ancak bağlı listelerde `O(1)` karmaşıklıktadır. Benzer şekilde sona ekleme işlemi de diziler `O(n)`, bağlı listelerde `O(1)` karmaşıklıktadır.
>- Dizilerin peşpeşe alana gereksinimi bazı durumlarda dezavantajlı olabilmektedir. Bağlı listeler peşpeşe alana gereksinim duymazlar.
>- Bağlı listelerin bellekte toplam kapladığı alan dizilere göre fazladır. Ancak unutulmamalıdır ki bölünme bundan çok daha fazla belleğin kullanım dışı kalmasına yol açan bir etken olabilmektedir. Dinamik büyüyen dizi veri yapıları için `capacity` kavramı söz konusu olduğundan daha fazla yer kaplama söz konusu olabilmektedir.
>
>`LinkedList<E>` sınıfı `Deque<E>` arayüzünü de implemente etmiştir. `Deque<E>` arayüzü baştan ve sondan ekleme ve silme yapılabilen veri yapısını (double ended queue) temsil eder.
>
>JavaSE'de tipik olarak First In First Out (FIFO) kuyruk yapısının temsil eden ve `Collection<E>` arayüzünden türetilmiş `Queue<E>` arayüzü vardır. `FIFO` kuyruk sistemi elemanın sona eklenip baştan alınması anlamında olduğundan bu arayüzü destekleyen veri yapıları maliyet açısından bu yapıya uygundur. Yani, veri yapısının ilk elemanın alınması dolayısıyla silinmesinin maliyetli olabileceği  durumlarda implemente edilmez. Örneğin `ArrayList` ve `Vector` sınıfları bu arayüzü impelemente etmezler.

>`Queue<E>` *arayüzünün önemli bazı metotları şunlardır:*
>
>`add, offer` -> Kuyruğa eleman eklemek için kullanılırlar. `add` metodu fixed size kuyruk sistemleri için `IllegalStateException` fırlatır, `offer` metodu ise bu durumda false değerine geri döner.
>
>`element, peek` -> Kuyruğun başındaki elemanı silmeden döndürürler. Kuyruk boş ise `element` metodu `NoSuchElementException` fırlatır, `peek` metodu bu durumda `null` değerine geri döner.
>
>`remove, poll` -> Kuyruğun başındaki elemanı döndürürler ve aynı zamanda silerler. Kuyruk boş ise `remove` metodu `NoSuchElementException` fırlatır, `poll` metodu bu durumda `null` değerine geri döner.

>**_Anahtar Notlar:_** `Queue<T>` arayüzü tipik olarak `FIFO` kuyruk sistemleri için kullanılsa da başka şekilde çalışan kuyruk sistemleri de söz konusu olabilir. Bu anlamda örneğin add metodu için dokümanlarda insert fiili kullanılmıştır.
>
>İki taraftan da sonlanabilen kuyruk veri yapıları `Deque<T>` arayüzü ile temsil edilir (Double ended queue). `Deque<T>` arayüzü `Queue<T>` arayüzünden türetilmiştir. `Deque<T>` arayüzünün de kendine özgü deque'in başı (head) ile işlem yapan
>
>       addFirst
>       offerFirst
>       removeFirst
>       pollFirst
>       getFirst
>       peekFirst
>ve sonu (tail) ile işlem yapan
>
>       addLast
>       offerLast
>       removeLast
>       pollLast
>       getLast
>       peekLast
>metotları vardır. Bu metotlar ve aralarındaki ilişkiler `Queue<T>` arayüzünün metotları ve aralarındaki ilişkiler gibidir. `Deque` veri yapıları hem `FIFO` hem de `LIFO` olarak kullanılabilmektedir. Örneğin iki yarışmacı her bir yarışı bitirdiklerindeki birinci yarışmanın sonuçlarını hep başa, ikinci yarışmacının sonuçlarını hep sona ekleyecek olalım. Bu durumda eğer `ArrayList` ya da `Vector` gibi bir veri yapısı kullanırsak, sona eklemek maliyetli olmasa da başa eklemek maliyetlidir. Bu durumda bu senaryo için bu veri yapıları uygun değildir. Tipik olarak deque veri yapısı kullanılabilir. Java'nın collection kütüphanesinde `Deque<T>` arayüzünü dolayısıyla `Queue<T>` arayüzünü impelemente etmiş olan sınıflar içerisinde `ArrayDeque<E>` ve `LinkedList<E>` sınıfları çok fazla kullanılmaktadır.
>
>`ArrayDeque<E>` sınıfı, `ArrayList`'in deque olarak çalışabilen yani baştan sondan capacity değeri kullanarak büyüyebilen veri yapısı olarak düşünülebilir. Bu veri yapısındaki pek çok işlem **"amortized constant time cost"** olarak yapılır. `remove`, `removeFirstOccurrence`, `removeLastOccurrence`, `contains`, `iterator` ve `remove` gibi metotlar `O(n)` karmaşıklıkta çalışırlar. `ArrayDeque<E>` sınıfı her ne kadar `ArrayList<E>`'ye benzetilebilse de liste tarzı bir collection sınıf değildir. Dolayısıyla `List<E>` arayüzünü implemente etmemiştir.

>*Aşağıdaki demo örnekte bir stack'in elemanları dolaşılmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;
import java.util.Stack;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var stack = new Stack<Integer>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            Console.write("%d ", val);
            stack.push(val);
        }

        Console.writeLine();

        while (!stack.empty())
            Console.write("%d ", stack.pop());

        Console.writeLine();
    }
}
```

>*Aşağıdaki demo örnekte bir stack'in elemanları dolaşılmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var stack = new Stack<Integer>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            Console.write("%d ", val);
            stack.push(val);
        }

        Console.writeLine();

        try {
            while (true)
                Console.write("%d ", stack.pop());
        }
        catch (EmptyStackException ignore) {
            Console.writeLine();
        }
    }
}
```

>`~/src/SampleCodes/CodingChallenges` projesindeki `CSDArrayListStack<E>` sınıfı ve test kodlarını inceleyiniz

>*Aşağıdaki demo örnekte `**` ile belirtilen id deyiminin doğru kısmı en azından `get` metodu dolaysıyla döngüsel olarak çalışmaktadır. Yani `O(n)` karmaşıklıktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.LinkedList;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var list = new LinkedList<>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            list.add(val);
        }

        Console.writeLine(list);
        if (!list.isEmpty()) { //**
            var index = random.nextInt(list.size());
            Console.writeLine("Value at %d is %d", index, list.get(index));
        }
        else
            Console.writeLine("Linked list is empty!...");
    }
}
```

>**_Sınıf Çalışması:_** null değeri tutulamayan yani null değeri eklenmeye çalışıldığında `IllegalArgumentException` fırlatan `CSDArrayListNotNullable` collection sınıfını yazınız
>
>**Açıklamalar:**
>
>- Bu collection sınıfı `ArrayList` sınıfından türetilecektir
>- Çoklu veri eklemede bir tane bile null değer varsa yine ekleme yapılmayacak ve exception fırlatılacaktır
>- Sınıfın `ArrayList` sınıfının içerisinde bulunan tüm ctor'ları olacaktır
>`~/src/SampleCodes/CodingChallenges` projesini inceleyiniz

>**_Sınıf Çalışması:_** Yalnızca `Object` sınıfından türetilen ve **dinamik** olarak büyüyebilen `CSDStack` sınıfını yazınız
>
>**Açıklamalar:**
>
>- Sınıfın public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz
>- JavaSE'nin `Stack<E>` sınıfı kullanılmayacaktır
>`~/src/SampleCodes/CodingChallenges` projesini inceleyiniz

>**_Sınıf Çalışması:_** Eleman sayısını ctor ile alan ve stack dolduğunda `RuntimeException` sınıfından türetilmiş `FullStackException` fırlatan `CSDBoundedStack` sınıfını yazınız ve test ediniz.
>
>Sınıfın public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz.
>`~/src/SampleCodes/CodingChallenges` projesini inceleyiniz

>**_Sınıf Çalışması:_** Yalnızca `Object` sınıfından türetilen, `Queue<E>` arayüzünü implemente eden ve **dinamik** olarak büyüyebilen `CSDQueue` sınıfını yazınız ve test ediniz
>
>Sınıfın public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz
>`~/src/SampleCodes/CodingChallenges` projesini inceleyiniz

>**_Sınıf Çalışması:_** Eleman sayısını ctor ile alan ve queue dolduğunda `RuntimeException` sınıfından türetilmiş `FullQueueException` fırlatan `CSDBoundedQueue` sınıfını yazınız ve test ediniz.
>
>Sınıfın public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz.
>`~/src/SampleCodes/CodingChallenges` projesini inceleyiniz

>**_Sınıf Çalışması:_** Tutulan en küçük elemanı `O(1)` karmaşıklıkta veren ve push ve pop işlemlerinin de en fazla `amortized O(1)` karmaşıklıkta çalıştığı `CSDMinStack<E>` sınıfını yazınız.
>
>Sınıfın public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz.
>`~/src/SampleCodes/CodingChallenges` projesini inceleyiniz

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.LinkedList;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var linkedList = new LinkedList<Integer>();
        var random = new Random();

        while (true) {
            var value = random.nextInt(-99, 100);

            if (value == 0)
                break;

            if (value < 0)
                linkedList.addFirst(value);
            else
                linkedList.addLast(value);
        }

        Console.writeLine(linkedList);

        while (!linkedList.isEmpty())
            Console.write("%d ", linkedList.pollFirst());
     }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayDeque;
import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var arrayDeque = new ArrayDeque<>();
        var random = new Random();

        while (true) {
            var value = random.nextInt(-99, 100);

            if (value == 0)
                break;

            if (value < 0)
                arrayDeque.addFirst(value);
            else
                arrayDeque.addLast(value);
        }

        Console.writeLine(arrayDeque);

        while (!arrayDeque.isEmpty())
            Console.write("%d ", arrayDeque.pollFirst());
     }
}
```

>`Comparable<T>`, Java'da karşılaştırma işleminin anlamlı olduğu sınıflar tarafından override edilmesi tavsiye edilen (hatta iyi bir tasarım açısından kesinlikle yapılması gereken) bir arayüzdür. Bu arayüzünün `compareTo` isimli bir tane abstract metodu vardır. Bu arayüz her ne kadar fonksiyonel bir arayüz olarak kullanılabilse de `FunctionalInterface` annotation'ı ile **işaretlenmemiştir**. Çünkü bu arayüzün tasarımı (yani contract'ı) fonksiyonel olarak kullanılma pratiğinde değildir. Bu arayüz JavaSE'de çeşitli sınıflar ve metotlar tarafından kullanılmak üzere istenir. `Compareable<T>` arayüzünün `compareTo` metodunun algoritması şu şekildedir:
>
>       int result = a.compareTo(b);
>için
>
>1. result > 0 <=> a mantıksal olarak b'den büyüktür.
>2. result == 0 <=> a mantıksal olarak b'ye eşittir
>3. result < 0 <=> a mantıksal olarak b'den küçüktür.
>
>Bu şekilde, karşılaştırılabilen nesnelere ilişkin sınıflar `Compareble<T>` arayüzünü desteklerler. Örneğin `String` sınıfı tamsayı türlerine ve gerçek sayı türlerini ilişkin sarmalayan sınıflar, `BigDecimal`, `BigInteger` gibi sınıflar bu arayüzü destekler.
>
>**_Anahtar Notlar:_** `Comparable<R>` arayüzünü destekleyen sınıflar için genel olarak **"mutually comparable"** terimi kullanılır.
>
>`Comparable<T>` arayüzünün desteklenmediği veya destekleniş biçiminden (yani `compareTo` metodunun yaptığı işten) farklı olarak karşılaştırma işlemlerinde kullanılan `Comparator<T>` arayüzü vardır. Bu arayüz fonksiyoneldir. Karşılaştırma işlemini `Comparable<T>` arayüzü dışında yapan generic metotlar, karşılaştırma kriteri için bu arayüz referansını **callback** olarak alırlar. Yani, bir metot `Comparator<T>` arayüzünü parametre olarak alıyorsa, programcıdan karşılaştırma kriterini alıyordur anlamına gelir. Bu arayüzün abstract metodu dışında da kullanılan pek çok static ve default metodu da `Java 8` ve sonrasında eklenmiştir.

>*JavaSE'de collection'lar ile çeşitli yararlı işlemlerin yapılabilmesini sağlayan* `Collections` *isimli utility bir sınıf bulunmaktadır.*
>
>Collections sınıfının `max` ve `min` metotları `Collection<E>` arayüzü alarak o collection'ın en büyük ve en küçük elemanına geri döner. Bu metotlar boş bir collection için `NoSuchElementException` fırlatırlar. Bu metotlar büyük veya küçük olma kavramını nasıl anlayacaklardır? İşte bu metotların tek parametreli overload'larının generic tür parametrelerinin açılımda `Comparable<T>` arayüzünü destekleyen türlerden olması gerekir. Aksi durumda error oluşur. Peki, bu metotlar ile `Compareble<T>` arayüzünü desteklemeyen bir sınıf duruma göre karşılaştırılmak istense ne yapılacaktır? Benzer şekilde `Comparable<T>` arayüzünü destekleyen bir sınıf için `compareTo` dışında bir karşılaştırma kriteri nasıl verilecektir? İşte bu metotların `Comparator<T>` parametreli overload'ları bulunur. Bu overload'lar karşılaştırma kriterini parametre olarak alırlar. Bu metotlar açılımda `Comparable<T>` arayüzüne bakmazlar.
>
>`Collections` sınıfının `sort` metodu bir `List<T>`'yi sıralamak için kullanılabilir. Burada sıralama algoritması doğrudan belirtilmese de, algoritması pek çok problem için uygun olacak şekilde seçilir. Sıralama işlemi doğal olarak (natural sort order) yani artan sırada **(ascending)** olacak şekilde yapılır. sort metodunun tek parametreli overload'u generic türün `Comparable<T>` açılımını desteklemesi durumunda kullanılabilir. Aksi durumda error oluşur. sort metodunun da `Comparator<T>` parametreli overload'u bulunur. Bu metot'da sıralama işleminde sıralama kriterini belirlemek için kullanılabilir. sort metodunun stable olduğu garanti edilmiştir. `List<E>` arayüzünün de Java 8 ile eklenen `Comparator<T>` parametreli bir sort metodu vardır. Sıralama kriterinin verildiği durumda bu metodun kullanılması önerilir. `Arrays` sınıfının da generic olarak bildirilmiş ve `Comparator<T>` alan sort metotları vardır. `Arrays` sınıfının `Object []` parametreli sort metotları ilgili nesneye ilişkin sınıfın `Comparable<T>` arayüzünü destekleyip desteklemediğine bakar. Eğer desteklemiyorsa exception oluşur. `Arrays` sınıfının sort metodunun temel türden dizi parametreli overload'ları sıralama işlemini yalnızca artan sırada yapar.
>
>**_Anahtar Notlar:_** Sıralama işlemleri genel olarak iki gruba ayrılabilir: stable, unstable. stable sort işleminde aynı olan elemanların sıralamada birbirlerine göre konumlarının değişmeyeceği garanti altındadır. Unstable sort işlemi ise bunu garanti etmez, yani algoritması gereği aynı olan elemanların yerlerini değiştirebilir.
>
>`Collections` arayüzünün binarySearch metotları sıralı bir dizi üzerinde **"binary search"** algoritması ile arama işlemi yapar. Şüphesiz bu metot sıralama işlemi yapmaz. Dizinin sıralı olarak verilmesi çağıran programcının sorumluluğundadır. Sırasız bir diz için elde edilecek sonu. belirsizdir. Anımsanacağı gibi bu algoritma `O(logN)` karmaşıklıktadır. Bu metot sıralama işlemi için ascending order varsayımıyla çalışır. Metot aranan eleman bulunamaması durumunda negatif bir değere geri döner.
>
>**_Anahtar Notlar:_** `Collections` sınıfının diğer metotları kullanıldıkça ele alınacaktır. Bunlar için dokümantasyondan faydalanılabilir.

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Collections;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 10);

        fractions.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var minFraction = Collections.min(fractions);
            var maxFraction = Collections.max(fractions);

            Console.writeLine("Minimum:%s%nMaximum:%s", minFraction, maxFraction);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}
```

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Collections;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 10);

        fractions.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var minFraction = Collections.min(fractions, (a, b) -> a.getNumerator() - b.getNumerator());
            var maxFraction = Collections.max(fractions, (a, b) -> a.getNumerator() - b.getNumerator());

            Console.writeLine("Minimum:%s%nMaximum:%s", minFraction, maxFraction);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}
```

>*Yukarıdaki örnek* `Comparator` *arayüzünün* `comparingInt` *metodu ile de yapılabilir*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 10);

        fractions.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var minFraction = Collections.min(fractions, Comparator.comparingInt(Fraction::getNumerator));
            var maxFraction = Collections.max(fractions, Comparator.comparingInt(Fraction::getNumerator));

            Console.writeLine("Minimum:%s%nMaximum:%s", minFraction, maxFraction);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}
```

>*Aşağıdaki örnekte payı küçük olan kesir büyük olarak karşılaştıralacak şekilde ele alınmıştır*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 10);

        fractions.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var minFraction = Collections.min(fractions, (a, b) -> b.getNumerator() - a.getNumerator());
            var maxFraction = Collections.max(fractions, (a, b) -> b.getNumerator() - a.getNumerator());

            Console.writeLine("Minimum:%s%nMaximum:%s", minFraction, maxFraction);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}
```

>*Yukarıdaki örnek* `Comparator` *arayüzünün* `reverseOrder` *metodu ile yapılabilir*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 10);

        fractions.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var minFraction = Collections.min(fractions, Comparator.reverseOrder());
            var maxFraction = Collections.max(fractions, Comparator.reverseOrder());

            Console.writeLine("Minimum:%s%nMaximum:%s", minFraction, maxFraction);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}
```

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.ComplexFactory;
import org.csystem.math.Complex;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

class Application {
    public static void run(String[] args)
    {
        var factory = new ComplexFactory();
        var numbers = factory.createRandomComplexNumbersUntilZero(-10, 10);

        numbers.forEach(Console::writeLine);
        Console.writeLine("-----------------------------------------");
        try {
            var min = Collections.min(numbers, Comparator.comparingDouble(Complex::getNorm));
            var max = Collections.max(numbers, Comparator.comparingDouble(Complex::getNorm));

            Console.writeLine("Minimum:%s%nMaximum:%s", min, max);
        }
        catch (NoSuchElementException ignore) {
            Console.writeLine("No fraction generated!...");
        }
     }
}
```

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Collections;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 11);
        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine("\n---------------------------------------------------------------------------------------");
        Collections.sort(fractions);
        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine();
    }
}
```

>*Aşağıdaki demo örnekte* `Comparable<T>` *arayüzünü destekleyen* `Fraction` *sınıfından oluşan bir liste azalan sırada **(descending)** olacak şekilde sıraya dizilmiştir.*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Comparator;
import java.util.Collections;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 11);
        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine("\n---------------------------------------------------------------------------------------");
        Collections.sort(fractions, Comparator.reverseOrder());
        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine();
    }
}
```

>*Aşağıdaki demo örnekte* `Comparable<T>` *arayüzünü destekleyen* `Fraction` *sınıfından oluşan bir liste azalan sırada **(descending)** olacak şekilde sıraya dizilmiştir.*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Comparator;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionsUntilZero(-10, 11);
        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine("\n---------------------------------------------------------------------------------------");
        fractions.sort(Comparator.reverseOrder());
        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine();
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Arrays;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionArrayUntilZero(Console.readInt("Input count:"), -10, 10);

        Arrays.stream(fractions).forEach(f -> Console.write("%s ", f));
        Console.writeLine("\n-----------------------------------------");
        Arrays.sort(fractions);
        Arrays.stream(fractions).forEach(f -> Console.write("%s ", f));
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;

import java.util.Arrays;
import java.util.Comparator;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createRandomFractionArrayUntilZero(Console.readInt("Input count:"), -10, 10);

        Arrays.stream(fractions).forEach(f -> Console.write("%s ", f));
        Console.writeLine("\n-----------------------------------------");
        Arrays.sort(fractions, Comparator.reverseOrder());
        Arrays.stream(fractions).forEach(f -> Console.write("%s ", f));
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*
>
>`~/src/Libraries/CSDLibraries/MathLib` kütüphanesini inceleyiniz

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.Collections;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var fractions = factory.createSortedRandomFractions(-10, 10);

        fractions.forEach(f -> Console.write("%s ", f));
        Console.writeLine();
        int index = Collections.binarySearch(fractions, new Fraction(0));

        if (index >= 0)
            Console.writeLine("%d -> %s", index, fractions.get(index));
        else
            Console.writeLine("Not found!...");
    }
}
```

>*JavaSE'ye Java 7 ile birlikte* `Objects` *isimli utility bir sınıf eklenmiştir. Bu sınıfın temel amacı bir takım kontrollerin kolay bir biçimde yapılabilmesini sağlamaktır. Bu sınıf eklendikten sonra var olan diğer sınıfların hemen hepsi ilgili kontrolleri yaparken bu sınıfı kullanmakta ve dokümantasyonda da bu sınıfın ilgili metodunu çağrıldığını söylemektedir.*
>
>*Bu sınıfın* `equals` *metodu iki tane* `Object`*'in mantıksal eşitlik karşılaştırmasını yapar. Bu metodun aldığı argümanların her ikisi de null ise true değerine geri döner, birinci argüman null dışı ise, birinci parametreye ilişkin referans ile equals metodunu çağırır ve geri dönüş değerine geri döner. Bunlar dışında false değerine geri döner.*
>
>*Bu sınıfın* `compare` *metodu aldığı* `Comparator<T>` *fonksiyonel arayüzü ile birinci ve ikinci parametresi ile aldığı referansları mantıksal karşılaştırır. Duruma göre klasik comparison değerlerine geri döner*
>
>*Sınıfın diğer metotları örnekler içerisinde kullanılarak ele alınacaktır*

>`SuppressWarnings` *annotation'ı bazı warning durumlarını kaldırmak amaçlı kullanılır. SuppressWarnings ile her warning kaldırılamaz. Java Language Specification'a (JLS) göre SuppressWarnings ile aşağıdaki warning'ler kaldırılabilir:*
>
>`unchecked`: *Yapılan işlemin kontrol edilmesi gerektiği ancak programcının kontrol etmediğinde verilen warning mesajını kaldırmak için kullanılır. Programcı kontrol işleminin gerekmediğine emin olduğunda kullanabilir.*
>
>`deprecation`: *Deprecated olan bir warning'i kaldırmak için kullanılabilir.*
>
>`removal`: *İleride silinecek olan bir sentaktik elemanın kullanımında verilen warning'in kaldırılması için kullanılır. Tipik olarak Deprecated annotation'ının forRemoval attribute'unun true olması durumunda verilen uyarının kaldırılması için kullanılır.*
>
>`preview`: *Dile ya da bir kütüphaneye eklenmiş olan bir özelliğin veya ilgili kütüphane elemanının (class, package, method vs.) henüz daha "preview" aşamasında olması durumunda verilen warning'in kaldırılması için kullanılır.*
>
>*Bu warning'ler* `String []` *türden value attribute'u ile verilebilir. JLS bu durumu esnek bırakmıştır. Yani buradaki warning'lerin kaldırılabilir olması zorunludur. Ancak ek olarak başka warning'ler de eklenmiş olabilir. Her ne şekilde olursa programcı bu warning'leri kaldırırken kesinlikle emin olmalıdır. Bu durum tamamen programcının sorumluluğudur. Yani kısaca bu annotation dikkatli kullanılmalıdır*

>**Hash Tabloları ile Arama (Hashing)**
>
>*Hash tabloları (hash table) ile arama "indeksli arama" ile "sıralı arama"nın bir orta noktası gibidir. Bu yöntemde bir dizi açılır. Anahtar değer, bir fonksiyona sokularak dizi indeksine dönüştürülür ve eleman o indekse yerleştirilmek istenir. Örneğin kişileri TC kimlik numaralarına göre bir hash tablosuna yerleştirmek isteyelim. Bunun için 100 elemanlı bir dizi açmış olalım. TC kimlik numarası 11 basamaklı büyük bir numaradır. Biz bu numaradan [0, 99] arasında bir indeks elde etmek isteyelim. Bu indeksi elde eden fonksiyona "hash fonksiyonu" denilmektedir. Örneğin 100'e bölümünden elde edilen kalan basit hash fonksiyonu olarak kullanılabilir. Bu durumda 41106234567 TC numaralı kişi bu dizinin 67'inci indeksine yerleştirilmek istenecektir. Eleman aranırken de aynı biçimde anahtardan aynı hash fonksiyonuyla dizi indeksi elde edilir ve o indekse bakılmak istenir.*
>
>*Şüphesiz yukarıda açıklanan yöntemde her zaman bir çakışma (collision) olasılığı vardır. Örneğin 23445623267 TC kimlik numaralı kişi için de aynı indeks ele edilecektir. İşte hash tablosu yöntemi çakışma durumundaki stratejiye göre iki temel alt gruba ayrılmaktadır: Açık adresleme yöntemi (Open addressing), Ayrı zincir oluşturma (Separate chaining).*
>
>*Açık adresleme yöntemi de kendi aralarında pek çok alt yönteme ayrılmaktadır:*
>- Linear probing
>- Quadratic probing
>- Double probing
>
>*En çok kullanılan çakışma çözüm yöntemi (collision resolution methods) "ayrı zincir oluşturma" yöntemidir.*
>
>**Ayrı Zincir Oluşturma Yöntemi**
>
>*Bu yöntemde N elemanlı bir dizi açılır. Fakat dizide elemanlar tutulmaz. Bu bir bağlı liste dizisidir. Yani dizinin her elemanı bir bağlı listenin head göstericisini tutar. Eleman yerleştirileceği zaman hash fonksiyonuna sokulur. Buradan bir dizi indeksi elde edilir. Sonra eleman bu indeksteki bağlı listeye eklenir. Arama yapılırken de benzer biçimde önce anahtar hash fonksiyonuna sokulur. Oradan elde edilen indeksteki bağlı listede doğrusal arama yapılır.*
>
>*Bu yöntemde eleman eklemek sabit karmaşıklığa sahip bir işlemdir. Arama ise doğrusal karmaşıklıkta gözükmekle birlikte az sayıda elemana sahip dizi içerisinde yapılacağı için çok etkindir. Gerçekten de Knuth bağlı listelerdeki eleman sayısı ortalama 10'u geçmedikten sonra bu yöntemi süper bir yöntem olarak nitelendirmektedir. Eğer bağlı listedeki elemanların sayısı çok fazla olursa (yani tablo küçük kalırsa) yöntem doğusal aramaya O(N) yaklaşmaya başlar. O halde tablo uzunluğunu baştan iyi öngörmek gerekir. Örneğin ilgili sisteme ortalama 10000 eleman yerleştirilecekse bizim tabloyu 1000 civarında tutmamız uygun olur. Hash tabloları özellikle işletim sistemlerinin çekirdek (kernel) kodlamalarında çok sık karşımıza çıkmaktadır. Örneğin Linux'ta cache sistemlerinde arama yapılırken hep ayrı zincirli hash tabloları kullanılmıştır. Örneğin Linux'un buffer cache (disk cache) mekanizmasını düşünelim. Burada dosya fonksiyonları okunacak yerin disk blok adresini hesapladıktan sonra onun buffer cache içerisinde olup olmadığına bakmak ister. Blok adresleri birer tamsayıyla belirtilir. Örneğin read fonksiyonu okunacak dosya parçasınının diskin 181317'inci bloğunda olduğunu hesaplasın. Bu blok acaba cache'te midir? İşte Linux bu değeri anahtar yaparak bir hash tablosunda arama yapar. Eğer bulursa doğrudan hiç disk okuması yapmadan bilgiyi oradan alarak verir. Benzer biçimde dizin girişleri (dentry cache), inode elemanları (inode cache) hep bu biçimde cache sistemleri içerisinde saklanmaktadır.*
>
>**Hash Fonksiyonu Nasıl Olmalıdır?**
>
>*Bilindiği gibi hash fonksiyonu anahtarı dizi indeksine dönüştürmektedir. İyi bir hash fonksiyonunun şu özelliklere sahip olması beklenir:*
>- Tabloya yaydırmayı iyi yapmalıdır. Yani örneğin anahtar değerleri yanlı olsa bile hash fonksiyonun tabloya yansız bir biçimde eşit miktarda dağıtım yapabilecek yetenekte olması arzu edilir.
>- Hash fonksiyonunun hızlı olması istenir. Çünkü eleman ekleme ve arama işlemlerinde devreye girmektedir.
>
>*Sayıdan sayı elde eden, yazıdan sayı elde eden kaliteli hash fonksiyonları için Internet'te araştırma yapılabilir. Yazıdan hash code elde eden basit bir hash fonksiyonu şu şekilde yazılabilir:*
>
>        public static int hash(String str)
>        {
>            int result = 8128;
>            int len = str.length();
>
>            for (int i = 0; i < len; ++i)
>                result = ((result << 5) + result) + str.charAt(i);
>
>            return Math.abs(result % len);
>        }
>**Hash Tablolarında Açık Adresleme (Open Addressing)**
>
>*Yukarıda da belirtildiği gibi açık adresleme yöntemi pek çok alt yönteme ayrılmaktadır. En çok kullanılan açık adresleme yöntemi "doğrusal yoklama (linear probing)" denilen yöntemdir. Bu yöntemde anahtardan hash fonksiyonuyla bir tablo indeksi elde edilir. O indeksteki eleman (bucket) boşsa yerleştirme oraya yapılır. Doluysa sırasıyla elemanlar üzerinde ilerlenerek ilk boş yer bulunur. Eleman ilk boş yere eklenir. Arama yapılırken aynı biçimde yine dizi indeksi elde edilir ve elemanlar sırasıyla gözden geçirilir. Tabii ilk boş eleman görüldüğünde artık durulabilir. Bu yöntemde tablonun geniş açılması önemlidir. Eğer tablo küçük kalırsa bu durumda tabloda boş elemanlar azalır. Bu da hem eleman eklerken hem de ararken zaman kaybettirir.*
>
>**_Anahtar Notlar:_** Hashing, burada bir özet olarak ele alınmıştır. Detaylar "Java ile Uygulama Geliştirme 2" kursunda ele alınacaktır.
>
>**_Anahtar Notlar:_** JavaSE'de genel olarak, isminde "Hash" geçen veri yapıları hashing yöntemini kullanırlar.

>*Java'da bir sınıfa ilişkin bir "hash code" bilgisi bir convention olarak Object sınıfının hashCode metodu ile elde edilir. Object sınıfının hashCode metodu hash değerini nesnenin adresine göre verir. Bu durumda programcı ilgili sınıf için hashCode metodunu override ederek ilgili türden nesne için "hash code" değerinin ne olduğunu belirler. Java'da equals metodunun override edilmesi gereken sınıflar için hashCode metodu da override edilmelidir. Benzer şekilde hashCode metodunun override edilmesı gereken sınıflar için equals metodu da override edilmelidir Yani, equals ve hashCode metotları kesinlikle birlikte override edilmelidir. Bu metotların override edilmesi gereken sınıflara genel olarak "value class" ya da "data class" denilmektedir. Örneğin, String sınıfının her iki metodu da override edilmiştir. Ya da örneğin sarmalayan sınıfların da her iki metodu override edilmiştir. Genellikle bu tarz sınıflarda bu iki metot ile birlikte toString metodu da override edilir.*
>
>*Peki Java'da kaliteli hash code oluşturmak için ne yapılmalıdır? Bu işlem şüphesiz programcı tarafından çeşitli yöntemlerle yapılabilir. Ancak, Objects sınıfının hasCode ve hash metotları ile çoğu zaman (hatta neredeyse her zaman) uygun olacak şekilde kaliteli hash code'lar üretilebilir. Objects sınıfının hash metodun Object türünden varargs parametrelidir ve birden fazla değerden hash code elde etmek için kullanılabilir. hashCode metodu ise Object parametrelidir. Bir tane değerden hash code elde etmek için kullanılabilir. hash metodunun dökumantasyona göre bir tane değerden hash code elde etmek için hash metodu yerine hasCode metodu kullanılması önerilmektedir.*

>`Set<T>` arayüzü Matematik'teki küme kavramını ilişkin veri yapısını temsil eden collection sınıfların arayüzüdür.
>
>Anımsanacağı gibi Matematik'te kme kavramı iki tane temel özelliğe sahiptir:
>    1. Bir kümede eleman tekrarı olmaz. Yani bir elemandan birden fazla bulunamaz.
>    2. Bir kümenin elemanlarının  genel olarak, eklenmesi anlamında yani öncelik sonralık anlamında sırasının önemi yoktur.
>
>İşte `Set<T>` arayüzü küme kavramının bu özelliklerini temsil eden collection sınıfların arayüzüdür. `Set<T>` arayüzü `Collection<T>` arayüzünden türetilmiştir. `Set<T>` arayüzünü implemente eden ve en çok kullanılan iki tipik sınıf `TreeSet<T>` ve `HashSet<T>` collection sınıflarıdır.
>
>`TreeSet<T>` collection sınıfı elemanları doğal sıralı olarak (natural sort order) tutar. `TreeSet<T>` arayüzünün default ctor'u ve Collection parametreli ctor'u eklenen elemanların `Comparable<T>` arayüzünü desteklemesini bekler. Aksi durumda ekleme işleminde (yani örneğin add metotlarının çağrılması durumunda) ClassCastException fırlatılır. `TreeSet<T>` arayüzün `Comparator<T>` parametreleri ctor'u sıralama işlemi için çağıracağı callback'i alır. `TreeSet<T>` collection sınıfı eklenen elemanın aynı olup olmadığını tipik olarak ilgili türün equals metodunu çağırarak belirler. `TreeSet<T>` collection sınıfı aslında `Set<T>` arayüzünü dolaylı olarak yani `Set<T>` arayüzünden de türetilmiş `NavigableSet<T>` arayüzünü desteklediği için destekler. `NavigableSet<T>` arayüzünün `Set<T>` arayüzü dışında çeşitli metotları da bulunmaktadır. `TreeSet<T>` sınıfının `Comparable<T>` arayüzü kullanılarak elemanları sıralayan ctor'ları ile nesne yaratıldığında null değer eklenmesi durumunda exception oluşur.
>
>`HashSet<T>` collection sınıfı elemanları her hangi bir sırada tutar. Bu sınıf, eklenen elemanın var olup olmadığının testini equals ve hashCode metotlarına bakarak tespit eder. Eklenen ya da aranan bir eleman için equals metodu true dönen ve hashCode'u aynı olan bir eleman var kabul edilir. `HashSet<T>` collection sınıfı için iteratif olarak elde edilmesi durumunda hangi sırada geleceği belirli değildir. `HashSet<T>`, elemanların tutuluş sırasını çeşitli durumlarda değiştirebilmektedir. Zaten `HashSet<T>` collection kullanan programcı bunu bilerek bu sınıfı seçer. Yani tipik olara `HashSet<T>`, set olması ve hızlı işlem yapması durumları için tercih edilir. `HashSet<T>` sınıfına ilişkin "load factor" burada ele alınmayacaktır. `HashSet<T>` null değer tutabilmektedir.

>*Aşağıdaki demo örnekte Complex sınıfı "comparable" olmadığından exception oluşur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.ComplexFactory;
import org.csystem.math.Complex;

import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new ComplexFactory();
        var set = new TreeSet<Complex>();

        while (set.add(factory.createRandomComplexNumber(-10, 10)))
            ;

        set.forEach(Console::writeLine);
    }
}
```

>*Yukarıdaki demo örnek aşağıdaki gibi* `Comparator<T>` *parametreli ctor kullanılarak yapılabilir. Örnekte* `TreeSet<T>` *içerisindeki elemanlar norm değerine göre artan sırada tutulmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.ComplexFactory;
import org.csystem.math.Complex;

import java.util.Comparator;
import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new ComplexFactory();
        var set = new TreeSet<>(Comparator.comparingDouble(Complex::getNorm));

        while (set.add(factory.createRandomComplexNumber(-10, 10)))
            ;

        set.forEach(Console::writeLine);
    }
}
```

>*Aşağıdaki demo Örnekte* `TreeSet<T>` *içerisindeki elemanlar norm değerine göre azalan sırada tutulmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.ComplexFactory;
import org.csystem.math.Complex;

import java.util.Comparator;
import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new ComplexFactory();
        var set = new TreeSet<Complex>(Comparator.comparingDouble(Complex::getNorm).reversed());

        while (set.add(factory.createRandomComplexNumber(-10, 10)))
            ;

        set.forEach(Console::writeLine);
    }
}
```

>*Aşağıdaki demo Örnekte* `TreeSet<T>` *içerisindeki elemanlar,* `Fraction` *sınıfın "comparable" olduğundan compareTo metodunun geri dönüş değerine göre sıralı, dolayısıyla azalan sırada tutulmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var set = new TreeSet<Fraction>();

        while (true) {
            var fractionOpt = factory.createRandomFraction(-10, 10);

            if (fractionOpt.isEmpty())
                continue;

            if (!set.add(fractionOpt.get()))
                break;
        }

        set.forEach(Console::writeLine);
    }
}
```

>*Aşağıdaki demo Örnekte* `TreeSet<T>` *içerisindeki elemanlar azalan sırada tutulmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.Comparator;
import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var set = new TreeSet<Fraction>(Comparator.reverseOrder());

        while (true) {
            var fractionOpt = factory.createRandomFraction(-10, 10);

            if (fractionOpt.isEmpty())
                continue;

            if (!set.add(fractionOpt.get()))
                break;
        }


        set.forEach(Console::writeLine);
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

>*Aşağıdaki demo Örnekte* `TreeSet<T>` *içerisindeki elemanlar azalan sırada tutulmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.random.lottery.NumericLottery;

import java.util.Random;

import static org.csystem.util.array.ArrayUtil.print;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var lottery = new NumericLottery(random);

        while (true) {
            var n = Console.readInt("Kaç tane kupon oynamak istersiniz:");

            if (n <= 0)
                break;

            print(2, lottery.getNumbers(n));
        }
    }
}
```

```java
package org.csystem.random.lottery;

import java.util.TreeSet;
import java.util.random.RandomGenerator;

public class NumericLottery {
    private final RandomGenerator m_randomGenerator;

    public NumericLottery(RandomGenerator randomGenerator)
    {
        m_randomGenerator = randomGenerator;
    }

    public int [] getNumbers()
    {
        var numbers = new int [6];
        var set = new TreeSet<Integer>();

        while (set.size() != 6)
            set.add(m_randomGenerator.nextInt(1, 50));

        var i = 0;

        for (var val : set)
            numbers[i++] = val;

        return numbers;
    }

    public int [][] getNumbers(int count)
    {
        int [][] numbers = new int[count][];

        for (int i = 0; i < count; ++i)
            numbers[i] = getNumbers();

        return numbers;
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.FractionFactory;
import org.csystem.math.Fraction;

import java.util.HashSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new FractionFactory();
        var set = new HashSet<Fraction>();

        while (true) {
            var fractionOpt = factory.createRandomFraction(-10, 10);

            if (fractionOpt.isEmpty())
                continue;

            if (!set.add(fractionOpt.get()))
                break;
        }
        set.forEach(Console::writeLine);
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.ComplexFactory;
import org.csystem.math.Complex;

import java.util.HashSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new ComplexFactory();
        var set = new HashSet<Complex>();

        while (set.add(factory.createRandomComplexNumber(-10, 10)))
            ;

        set.forEach(Console::writeLine);
    }
}
```

>**_Sınıf Çalışması:_** Parametresi ile aldığı bir yazının tüm karakterlerinin farklı olup olmadığını test eden `areAllUnique` isimli metodu `UtilLib` içerisindeki `StringUtil` sınıfına ekleyiniz

>**_Sınıf Çalışması:_** Parametresi ile aldığı generic türden bir dizinin tüm elemanlarının farklı olup olmadığını test eden `areAllUnique` isimli metodu `UtilLib` içerisindeki `ArrayUtil` sınıfı içerisinde yazınız. Metodun temel türden diziler için overload'larını da ekleyiniz

>**_Sınıf Çalışması:_** Parametresi ile aldığı generic türden bir `Colletion`'ın tüm elemanlarının farklı olup olmadığını test eden `areAllUnique` isimli metodu `UtilLib` içerisindeki ArrayUtil sınıfı içerisinde yazınız

>**_Sınıf Çalışması:_** Parametresi ile aldığı generic türden iki `Iterable`'ın kesişimi (aynı olanlar), farkı (farklı olan elemanları) ve birleşimini (ortak elemanlardan bir tane olacak şekilde), tüm elemanların birleşimini `Collection` olarak döndüren sırasıyla `intersection`, `except`, `union` ve `unionAll` metotlarını `UtilLib` içerisindeki `CollectionUtil` sınıfı içerisinde yazınız

>`PriorityQueue<T>` *sınıfı öncelikli olan elemanları kuyruğun başına çeker. Bu sınıf* `Queue<T>` *arayüzünü implemente etmiştir. Önceliklendirme işlemini default olarak* `Comparable<T>` *arayüzünü kullanarak yapar. Eğer açılıma ilişkin tür* `Comparable<T>` *arayüzünü desteklemiyorsa ve PriorityQueue nesnesi default olarak* `Comparable<T>` *arayüzünü kullanıyorsa ClassCastException fırlatılır.* `PriorityQueue<T>` *nesnesi* `Comparator<T>` *parametreli ctor ile yaratılmışsa karşılaştırma işlemini aldığı callback ile yapar. Bu veri yapısının kullanımına tipik bir olarak, "banka müşterilerinin sahip oldukları kredi kartı veya hesap seçeneceğine işlem önceliğine alınması verilebilir". Bu veri yapısı işletim sistemlerinin "process management" kısmında çeşitli process'lerin önceliklendirilmesi için de kullanılmaktadır.*

>`~/src/Projects/010-DemoBankCustomerQueue` projesini inceleyiniz

>*JavaSE'de* `Map<K, V>` *arayüzünü destekleyen bir grup collection sınıf vardır.* `Map<K, V>`  *arayüzü herhangi bir arayüzden türetilmemiştir. Bu arayüzü destekleyen veri yapılarına (collections) literatürde "dictionary" de denilmektedir. Bu tarz collection sınıflarda bir anahtar ve karşılık geldiği değer vardır. Anahtar değeri bir "tekil (unique)" olarak tutulur. Bir anahtar için yeni bir değer verildiğinde, aynısından ikinci bir anahtar eklenmez. Eski anahtara karşılık gelen değer değiştirilir. Bu collection sınıflarda tipik olarak anahtar değeri set collection sınıfları olarak tutulur. Anahtarın var olup olmadığı kontrolü için equals metodu kullanılır. Eğer collection sınıf anahtar değere ilişkin set için hash veri yapısı da kullanıyorsa equals ile beraber hashCode metodu da kullanılır.*
>
>`Map<K, V>` *arayüzünün put  metodu ile anahtara karşılık gelen değer eklenebilir. put metodu anahtar daha önceden eklenmişse değerini günceller ve eski değere geri döner. Eğer anahtar daha önce eklenmemişse ekleme işleminden sonra null değerine geri döner. Bu durumda put metodunun döndürdüğü değerin null olması, eğer collection null değer de tutuyorsa anahtarın olmadığı anlamına gelmiyor olabilir.* `Map<K, V>` *arayüzünün containsKey metodu ile bir anahtarın daha önceden var olup olmadığı test edilebilir. Bu durumda bir anahtarın var olup olmadığına bakılması için en iyi ve en okunabilir yöntem containsKey metodunu kullanmaktır.*
>
>`Map<K, V>` *arayüzünün keySet isimli metodu anahtarlara ilişkin* `Set<K>` *collection referansına geri döner.* `Map<K, V>` *arayüzünün values isimli metodu ile tüm değerlere ilişkin* `Collection<V>` *referansı elde edilebilir.* `Map<K, V>` *arayüzünün get metodu ile parametresi ile aldığı anahtara karşılık gelen değer elde edilir. Bu metot da eğer anahtara ilişkin değer yoksa veya anahtara ilişkin değer null ise null değerine geri döner. Yine tipik olarak anahtarın varlığı containsKey isimli metot ile test edilebilir.*
>
>`Map<K, V>` *arayüzünü implemente eden collection sınıflar içerisinde en çok kullanılanları* `HashMap<K, V>` ve `TreeMap<K, V>` *sınıflarıdır*. `HashMap<K, V>` ve `TreeMap<K, V>` *sınıfları, anahtarlara ilişkin* `Set<K>` *collectionlar için sırasıyla* `HashSet<K>` ve `TreeSet<K>` *sınıflarını kullanırlar*

>`~/src/Projects/011-DemoCarCameraSimulation` projesini inceleyiniz

>**_Sınıf Çalışması:_** Aşağıdaki açıklamalara göre `IStringMap<T>` arayüzünü destekleyen `StringMap<T>` sınıfını yazınız.
>
>**Açıklamalar:**
>- `StringMap<T>` sınıfının public bölümünü değiştirmeden istediğiniz eklemeyi yapabilirsiniz
>- `IStringMap<T>` arayüzünde bir değişiklik yapılmayacaktır. Bu arayüz anahtarı string olan bir map collection sınıfları için implemente edilebilecektir.
>- `IStringMap<T>` arayüzünün abstract metotları şu şekildedir:
>   - `count`: "String map" içerisindeki anahtar eleman sayısına geri dönecektir.
>   - `addElement`: Metot parametresi ile aldığı anahtar ve değeri ekleyecektir. Eğer anahtar daha önce varsa true, yoksa false değerine geri dönecektir. Anahtar değerinin null olması veya blank string olması durumunda `IllegalArgumentException` fırlatacaktır.
>   - `removeElement`: Metot parametresi aldığı anahtar varsa ve silebilirse true, aksi durumda false değerine geri dönecektir. Anahtar değerinin null veya blank string olması durumunda `IllegalArgumentException` fırlatacaktır.
>   - `getValue(String key)`: Metot parametresi ile aldığı anahtara karşılık gelen değeri Optional olarak geri dönecektir. Anahtar değerinin null olması veya blank string olması durumunda `IllegalArgumentException` fırlatacaktır.
>   - `getValue(String key, T defaultValue)`: Metot parametresi ile aldığı anahtara karşılık gelen değeri döndürecektir. Anahtar yoksa ikinci parametresi ile aldığı değere geri dönecektir. Anahtar değerinin null olması veya blank string olması durumunda `IllegalArgumentException` fırlatacaktır.
>
>`IStringMap<T>` arayüzü ve `StringMap` sınıfının public bölümü şu şekildedir:

```java
package org.csystem.collection;

import java.util.Optional;

public interface IStringMap<T> {
    int count();
    boolean addElement(String key, T value);
    boolean removeElement(String key);
    Optional<T> getValue(String key);
    T getValue(String key, T defaultValue);
}
```

```java
package org.csystem.collection;

import java.util.Optional;

public class StringMap<T> implements IStringMap<T> {
    @Override
    public int count()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public boolean addElement(String key, T value)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public boolean removeElement(String key)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Optional<T> getValue(String key)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public T getValue(String key, T defaultValue)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
```

>**_Sınıf Çalışması:_** Parametresi ile aldığı iki tane yazının anagram olup olmadığını test eden `areAnagram` isimli metodu `StringUtil` sınıfı içerisinde yazınız. 
>
>**Anagram:** Bir yazının harflerinin yerleri değiştirilecek, diğer yazı elde edilebiliyorsa bu iki yazıya anagram denir.
>
>Örneğin:
>
>       "para" ve "arap"
>       "brat" var "bart"

>**Collection'lara ilişkin özet:**
>
>**Arayüzler:**
>- `Iterable<E>`: Genel dolaşıma ilişkin arayüz
>- `Collection<E>`: Collection olma anlaşması. Tipik olarak collection'lar veri transferinde kullanılır.
>- `List<E>`: Liste tarzı collection sınıfların arayüzü
>- `Queue<E>`: Kuyruk veri yapılarının arayüzü
>- `Deque<E>`: Baştan ve sondan büyütülebilen kuyruk veri yapılarının arayüzü
>- `Set<E>`: Küme tarzı veri yapılarının arayüzü
>- `Map<K, V>`: Sözlük tarzı veri yapılarının arayüzü
>
>**Sınıflar:**
>- `ArrayList<E>`: Dinamik büyüyen dizi veri yapısı
>- `Vector<E>`: Dinamik büyüyen dizi veri yapısı. Gerekmedikçe `ArrayList<E>` tercih edilmelidir
>- `LinkedList<E>`: Çift (doubly) bağlı lite veri yapısı
>- `Stack<E>`: LIFO kuyruk (stack) veri yapısı. `Vector<E>` sınıfından türetildiği unutulmamalıdır
>- `ArrayDeque<E>`: Baştan ve sondan "amortized constant time cost" olarak büyüyebilen dizi veri yapısı
>- `PriorityQueue<E>`: Öncelikli olarak çalışan kuyruk sistemi
>- `TreeSet<E>`: Elemanları sıralı (sorted) olarak tutan küme tarzı veri yapısı
>- `HashSet<E>`: Hash tablosu kullanan küme tarzı veri yapısı
>- `TreeMap<K, V>`: Anahtar değerleri tipik olarak `TreeSet<E>` olarak tutan sözlük tarzı veri yapısı
>- `HashMap<K, V>`: Anahtar değerleri tipik olarak `HashSet<E>` olarak tutan sözlük tarzı veri yapısı

>**Stream API:** *Stream API Java 8 ile eklenmiştir. Buradaki stream kavramı, I/O stream'lerden farklıdır. Stream'ler bir veri kaynağı (data source) üzerinde hızlı ve kolay biçimde işlemler yapılmasını sağlar. Stream'lerin temel özellikleri şunlardır:*
>- Stream'ler veri tutmazlar.
>- Stream'ler kaynak veri üzerinde değişiklik yapmazlar.
>- Stream'ler fonksiyonel programlama tekniğine uygun olarak tasarlanmışlardır. Bu anlamda çeşitli metotları fonksiyonel arayüzler ile callback alacak biçimde tasarlanmışlardır.
>- Stream'ler bir diziyi, bir collection sınıfı veya I/O Channel'ı kaynak veri olarak kullanabilir. Yani bunlardan "stream" elde edilebilir.
>- Stream API içerisinde çeşitli işlemleri "paralel (parallel computing)" yapabilen "parallel stream"'ler de bulunur.
>- Stream işlemleri zincir (fluent) biçiminde yapılabilir.
>
>*Stream işlemleri (stream operations/operators) iki gruba ayrılır: terminal, intermediate*
>
>- `Terminal Operations`: Çağrıldıklarında tüm zincir çalıştırılır
>- `Intermediate Operations`: Terminal işlemi olmadan yapılamayan işlemlerdir (lazy evaluation). Bu işlemlere ilişkin metotlar "stream" referanslarına geri dönerler. Bu metotlar eğer callback alıyorlarsa bu callback'ler bu metotlar çağrıldığında arka planda çağrılmaz. Terminal işlemine ilişkin bir metot çağrıldığında önceden ara işlemlerde belirlenen callback'ler ile işlem yapılır. Yani callback'ler bu aşamada çağrılır
>
>*Stream kullanılırken yazılan zincire içsel olarak "stream pipeline" denir. Bir "stream pipleline" sıfır ya da daha fazla ara işlem ve en fazla bir tane terminal işlemi içerebilir. Şüphesiz terminal işlemi içermeyen bir "stream pipeline" yazılabilse de bir etkisi olmaz.*
>
>*Bir stream, bir pipeline ile kullanılır. Yani bir stream elde edildiğinde açılır (open) ve terminal işlemi sonrasında kapatılır (close).*
>
>*Stream referansları collection sınıflar için* `Collection<E>` *arayüzünün stream metodu ile elde edilebilir.*
>
>*Stream interface'leri* `AutoCloseable` *arayüzünden türetilmiştir. Terminal işlemleri genel olarak bir stream için close işlemini yapar. Yani pipeline içerisinde terminal işlemi yapılmış bir stream referansı ile tekrar pipeline oluşturulamaz.*
>
>*Stream API için temel arayüzler şunlardır:*

    Stream<T>
    IntStream
    DoubleStream
    LongStream
>*Bir stream referansı genel olarak aşağıdakilerden biri ile elde edilebilir:*
>    - `Collection<E>` arayüzünün `stream` metodu ile `Stream<E>` elde edilebilir
>    - Bir diziden stream `Arrays` sınıfının `stream` metotlarından elde edilebilir
>    - Stream, IntStream, DoubleStream ve LongStream arayüzlerinin static bazı metotları ile elde edilebilir. Bunlardan bazıları şunlardır: `of`, `generate`, `iterate` vb.
>    - Intermediate işlemlere ilişkin metotlardan elde edilebilir
>
>*O zaman stream arayüzlerinin metotları için şunlar genel olarak söylenebilir:*
>    - Static olan ve bir stream arayüz referansına geri dönen metotlar pipeline başlatmak içindir.
>    - Non-static olan ve bir stream arayüz referansına geri dönen metotlar intermediate işlemlere ilişkindir
>    - Non-static olan ve bir stream arayüz referansına geri dönmeyen metotlar terminal işlemlere ilişkindir
>    - Ve diğerleri

>`Collection<E>` *arayüzünün default olarak bildirilmiş stream metodu ile bir collection sınıftan stream elde edilebilir. generic türden, double türden, int türden ve long türden bir diziden stream elde etmek için stream arayüzlerinin* `vararg` *parametreli of static metotları kullanılabilir. Ayrıca* `Arrays` *sınıfının* `stream` *metotları kullanılarak da ilgili türden dizilerden stream referansları elde edilebilir.*
>
>*Stream arayüzlerinin* `filter` *metotları aldıkları predicate fonksiyonel arayüzlerine göre koşulu uyanları içeren stream referansına geri dönerler. Döndürülen, stream referansları filter'in çağrıldığı referans ile aynı türdendir.*
>
>*Stream arayüzlerinin* `forEach` *metotları aldıkları **consumer callback**'e ilişkin işlemleri tüm elemanlar için yapar. Bu metot bir **terminal** metodudur ve geri dönüş değeri yoktur. Bu metot ile birlikte stream pipeline'a ilişkin tüm intermediate işlemler yapılarak elde edilen elemanlar consumer arayüzlerine ilişkin* `accept` *metotlarına argüman olarak geçirilir*

>*Aşağıdaki demo örnekte verilen stok miktarından daha fazla sayıda olan ürünler listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory, int stock)
    {
        factory.PRODUCTS.stream().filter(p -> p.getStock() > stock).forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(pf -> dataExistsCallback(pf, Integer.parseInt(args[1])), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte verilen stok miktarından daha fazla sayıda olan ürünler listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory, int stock)
    {
        factory.PRODUCTS.stream().filter(p -> p.getStock() > stock).forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(pf -> dataExistsCallback(pf, Integer.parseInt(args[1])), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte verilen minStock ve maxStock sayıları arasında kalan miktara sahip ürünler listelenmektedir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory, int minStock, int maxStock)
    {
        factory.PRODUCTS.stream()
                .filter(p -> minStock <= p.getStock() && p.getStock() <= maxStock)
                .forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var minStock = Integer.parseInt(args[1]);
            var maxStock = Integer.parseInt(args[2]);
            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(pf -> dataExistsCallback(pf, minStock, maxStock), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte stokta bulunan ürünler listelenmektedir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory)
    {
        factory.PRODUCTS.stream().filter(p -> p.getStock() > 0).forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");
            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte verilen minStock ve maxStock sayıları arasında kalan miktara sahip ürünler listelenmektedir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory, int minStock, int maxStock)
    {
        factory.PRODUCTS.stream()
                .filter(p -> minStock <= p.getStock())
                .filter(p -> p.getStock() <= maxStock)
                .forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var minStock = Integer.parseInt(args[1]);
            var maxStock = Integer.parseInt(args[2]);
            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(pf -> dataExistsCallback(pf, minStock, maxStock), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte satış fiyatı, verilen minPrice ve maxPrice arasında kalan ürünler listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void dataExistsCallback(ProductFactory factory, BigDecimal minPrice, BigDecimal maxPrice)
    {
        factory.PRODUCTS.stream()
                .filter(p -> p.getPrice().compareTo(minPrice) >= 0)
                .filter(p -> p.getPrice().compareTo(maxPrice) <= 0)
                .forEach(Console::writeLine);
    }

    public static void dataNotExistsCallback()
    {
        Console.Error.writeLine("No data in file!...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var minPrice = new BigDecimal(args[1]);
            var maxPrice = new BigDecimal(args[2]);

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(pf -> dataExistsCallback(pf, minPrice, maxPrice), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte doğum tarihi, verilen minDate ve maxDate arasında kalan çalışanlar listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffNameAgeDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            Arrays.stream(factory.getStaffAsArray())
                    .filter(s -> s.getBirthDate().isAfter(minDate))
                    .filter(s -> s.getBirthDate().isBefore(maxDate))
                    .forEach(Console::writeLine);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte doğum tarihi, verilen minDate ve maxDate arasında kalan çalışanlar listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffNameAgeDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getBirthDate().isAfter(minDate))
                    .filter(s -> s.getBirthDate().isBefore(maxDate))
                    .forEach(Console::writeLine);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin* `mapXXX` *metotları ilgili stream' ilişkin verilerden başka bir stream elde eder (mapping). Bu metotlar genel olarak yeni bir stream referansına geri dönerler. Bu metotlar tipik olarak "Function grubu" fonksiyonel arayüz referansları ile callback alırlar*

>*Aşağıdaki demo örnekte doğum tarihi, verilen minDate ve maxDate arasında kalan çalışanların isimleri listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffNameAgeDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getBirthDate().isAfter(minDate))
                    .filter(s -> s.getBirthDate().isBefore(maxDate))
                    .map(StaffInfo::getName)
                    .forEach(Console::writeLine);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte doğum tarihi, verilen minDate ve maxDate arasında kalan çalışanların yaşları listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffNameAgeDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getBirthDate().isAfter(minDate))
                    .filter(s -> s.getBirthDate().isBefore(maxDate))
                    .mapToDouble(StaffInfo::getAge)
                    .forEach(Console::writeLine);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Bir ya da birden fazla varlığın (entity), başka bir varlık ile temsil edilmesi durumunda dönüştürülmüş olan yeni varlığa* `Data Transfer Object (DTO)` *denir. Bu önemli bir pattern'dir ve NYPT'de varlıklar sınıflar olarak düşünülebilir. Örneğin bir öğrencinin bilgileri ve aldığı dersleri tek bir nesne olarak elde edilmek istenebilir. Bu durumda öğrencinin bilgileri* `Student` *sınıfı ile, aldığı dersler de* `Lecture` *sınıfı ile tutuluyorsa yeni elde edilecek* `StudentInfo` *sınıfı içerisinde öğrencinin bilgileri ve aldığı derslere ilişkin bir veri yapısı da bulunabilir. Bu anlamda* `StudentInfo` *bir DTO'dur. Bazı programcılar DTO sınıfların isimlendirilmesinde DTO'u son ek olarak sınıfa eklerler.*

>*Aşağıdaki demo örnekte doğum tarihi, verilen minDate ve maxDate arasında kalan çalışanlara bilgiler DTO olarak elde edilmiş ve listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffNameAgeDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getBirthDate().isAfter(minDate))
                    .filter(s -> s.getBirthDate().isBefore(maxDate))
                    .map(s -> new StaffNameAgeDTO(s.getName(), s.getAge()))
                    .forEach(Console::writeLine);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte doğum tarihi, verilen minDate ve maxDate arasında kalan çalışanlara bilgiler DTO olarak elde edilmiş ve listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffNameRestDayDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getBirthDate().isAfter(minDate))
                    .filter(s -> s.getBirthDate().isBefore(maxDate))
                    .map(s -> new StaffNameRestDayDTO(s.getName(), s.getRestDay()))
                    .forEach(Console::writeLine);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin* `count` *metodu ile ilgili stream'e ilişkin toplam veri/bilgi sayısına geri döner. Bu metodun geri dönüş değeri türü long'tur*

>*Aşağıdaki demo örnekte işe giriş tarihi, verilen minDate ve maxDate arasında kalan çalışanların sayısı elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            var count = Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getEntryDate().isAfter(minDate))
                    .filter(s -> s.getEntryDate().isBefore(maxDate))
                    .count();

            Console.writeLine("Count:%d", count);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin* `reduce` *metotları stream'e ilişkin verilerin kümülatif olarak bir işleme sokulmasını sağlar ve bu işlemin sonucunu döndürür. Bu metodun "binary operator" parametreli overload'u aldığı callback ile tüm verileri işleme sokar. Bu anlamda ilk değer olarak stream'in ilk elemanını alır. Stream boş olabileceğinden bu overload, optional referansına geri döner. İki parametreli overload'u ilk değeri alır. Bu durumda bu metot doğrudan ilgili işlemin sonucuna geri döner.*
>
>`IntStream`, `LongStream` ve `DoubleStream` arayüzlerinin* `sum` *metodu ile ilgili stream' eilişkin elemanların toplamı bulunabilir.*

>*Aşağıdaki demo örnekte işe giriş tarihi, verilen minDate ve maxDate arasında kalan çalışanların isimleri aralarına* `-` *konarak elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            var opt = Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getEntryDate().isAfter(minDate))
                    .filter(s -> s.getEntryDate().isBefore(maxDate))
                    .map(StaffInfo::getName)
                    .reduce((r, n) -> String.format("%s - %s", r, n));

            opt.ifPresentOrElse(Console::writeLine, () -> Console.writeLine("Not staff that satisfies the condition!..."));
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte işe giriş tarihi, verilen minDate ve maxDate arasında kalan çalışanların isimleri aralarına* `-` *konarak elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.StaffFactory;
import org.csystem.util.datasource.staff.StaffInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var minDate = LocalDate.parse(args[1], formatter);
            var maxDate = LocalDate.parse(args[2], formatter);

            var factory = StaffFactory.loadFromTextFile(args[0]);

            var names = Stream.of(factory.getStaffAsArray())
                    .filter(s -> s.getEntryDate().isAfter(minDate))
                    .filter(s -> s.getEntryDate().isBefore(maxDate))
                    .map(StaffInfo::getName)
                    .reduce("Names", (r, n) -> String.format("%s - %s", r, n));

            Console.writeLine(names);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte stokta bulunan, birim fiyatı, verilen minPrice ve maxPrice arasında kalan ürünlerin stok toplamı elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;
import org.csystem.util.datasource.product.ProductInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory, BigDecimal minPrice, BigDecimal maxPrice)
    {
        var totalStock = factory.PRODUCTS.stream()
                .filter(p -> p.getStock() > 0)
                .filter(p -> p.getPrice().compareTo(minPrice) > 0 && p.getPrice().compareTo(maxPrice) < 0)
                .mapToLong(ProductInfo::getStock)
                .reduce(0, Long::sum);
        Console.writeLine("Total stock:%d", totalStock);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var minPrice = new BigDecimal(args[1]);
            var maxPrice = new BigDecimal(args[2]);

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(f -> dataExistsCallback(f, minPrice, maxPrice), Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte stokta bulunan, birim fiyatı, verilen minPrice ve maxPrice arasında kalan ürünlerin stok toplamı elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;
import org.csystem.util.datasource.product.ProductInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory, BigDecimal minPrice, BigDecimal maxPrice)
    {
        var totalStock = factory.PRODUCTS.stream()
                .filter(p -> p.getStock() > 0)
                .filter(p -> p.getPrice().compareTo(minPrice) > 0 && p.getPrice().compareTo(maxPrice) < 0)
                .mapToLong(ProductInfo::getStock)
                .sum();
        Console.writeLine("Total stock:%d", totalStock);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var minPrice = new BigDecimal(args[1]);
            var maxPrice = new BigDecimal(args[2]);

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(f -> dataExistsCallback(f, minPrice, maxPrice), Application::dataNotExistsCallback);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*IntStream ve LongStream arayüzlerinin range metotları, parametresi ile aldığı a ve b değerleri için* `[a, b)` *aralığındaki sayılara ilişkin stream referansına geri döner.* `rangeClosed` *metotları ise parametresi ile aldığı a ve b değerleri için* `[a, b]` *aralığındaki sayılara ilişkin stream referansına geri döner. Bu metotlar ile tipik olarak birer birer artan tipik döngü oluşturulabilir*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            IntStream.range(Integer.parseInt(args[0]), Integer.parseInt(args[1]))
                    .forEach(i -> Console.write("%d ", i));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            IntStream.rangeClosed(Integer.parseInt(args[0]), Integer.parseInt(args[1]))
                    .forEach(i -> Console.write("%d ", i));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Bazı stream'ler sonsuz işlem yaparlar. Bu tarz stream'lere infinite stream de denilmektedir. Sonsuz stream'leri sonlandırmak için bazı ara işlemlere yönelik metotlar vardır. Ya bazı sonsuz stream'ler üreten metotların koşul parametreleri ile ilgili stream sonlandırılabilir. Stream arayüzülerinin generate metotları parametresi ile aldığı supplier callback ile verilen değerlere ilişkin sonsuz stream üretir.* `limit` *metodu parametresi ile aldığı sayı kadar elemanını bulunduğu stream referansına geri döner. Tipik olarak sonsuz stream'lerde belirli sayıda işlem yapmak için kullanılabilir*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);

            IntStream.generate(() -> random.nextInt(min, bound))
                    .limit(count)
                    .forEach(a -> Console.write("%d ", a));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;
import java.util.stream.DoubleStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var min = Double.parseDouble(args[1]);
            var bound = Double.parseDouble(args[2]);

            DoubleStream.generate(() -> random.nextDouble(min, bound))
                    .limit(count)
                    .forEach(a -> Console.write("%f%n", a));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.Random;
import java.util.stream.Stream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);
            Stream.generate(() -> StringUtil.generateRandomTextEN(random, random.nextInt(min, bound)))
                    .limit(count)
                    .forEach(Console::writeLine);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var random = new Random();
            var min = Integer.parseInt(args[0]);
            var bound = Integer.parseInt(args[1]);

            IntStream.generate(() -> random.nextInt(min, bound))
                    .takeWhile(a -> !NumberUtil.isPrime(a))
                    .forEach(a -> Console.write("%d ", a));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);

            IntStream.generate(() -> random.nextInt(min, bound))
                    .filter(NumberUtil::isPrime)
                    .limit(count)
                    .forEach(a -> Console.write("%d ", a));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var min = Integer.parseInt(args[1]);
            var bound = Integer.parseInt(args[2]);

            IntStream.generate(() -> random.nextInt(min, bound))
                    .limit(count)
                    .filter(NumberUtil::isPrime)
                    .forEach(a -> Console.write("%d ", a));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin* `iterate` *metotları ile tipik olarak iterasyon işlemleri yapılır. Bu metotlar sonsuz stream olarak çalışırlar. Bu metotların iki parametreli overload'ları birinci parametresi ile aldıkları başlangıç (seed) değerinden itibaren ikinci parametresi ile aldıkları "unary operator" callable değerine göre iterasyon yapar. 3 parametreli overload'ları birinci parametresi ile aldıkları başlangıç değerinden itibaren ikinci parametresi ile aldıkları predicate sağlandığı sürece üçüncü parametresi ile aldıkları callback'e ilişkin iterasyonu yapar*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var count = Integer.parseInt(args[0]);
            var step = Integer.parseInt(args[1]);

            IntStream.iterate(0, i -> i + step)
                    .limit(count)
                    .forEach(i -> Console.write("%d ", i));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");
            var a = Integer.parseInt(args[0]);
            var b = Integer.parseInt(args[1]);
            var step = Integer.parseInt(args[2]);

            IntStream.iterate(a, i -> i <= b, i -> i + step)
                    .forEach(i -> Console.write("%d ", i));

            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
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
```

>*Stream arayüzlerinin* `anyMatch`, `noneMatch` ve `allMatch` *isimli metotları aldıkları predicate callback'e ilişkin koşula göre `boolean` türüne geri dönerler.* `anyMatch` *metodu aldığı predicate'a ilişkin koşula uyan en az bir tane eleman varsa true aksi durumda false değerine geri döner. `noneMatch` metodu, hiç bir eleman aldığı predicate'a ilişkin koşula uymuyorsa true, aksi durumda false değerine geri döner. `allMatch` metodu, tüm elemanlar aldığı predicate'a ilişkin koşula uyuyorsa true, aksi durumda false değerine geri döner. Aslında bu 3 metot birbirleri yerine kullanılabilir ancak okunabilirlik/algılanabilirlik açısından en uygunu seçilmelidir*

>*Aşağıdaki demo örnekte varsa stokta bulunmayan ürünler listelenmiş, tüm ürünler stoktaysa uygun mesaj verilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        if (products.stream().anyMatch(p -> p.getStock() <= 0)) {
            Console.writeLine("Products not in stock:");
            products.stream().filter(p -> p.getStock() <= 0).forEach(Console::writeLine);
        }
        else
            Console.writeLine("All products in stock!...");
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte varsa stokta bulunmayan ürünler listelenmiş, tüm ürünler stoktaysa uygun mesaj verilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        if (products.stream().noneMatch(p -> p.getStock() <= 0))
            Console.writeLine("All products in stock!...");
        else {
            Console.writeLine("Products not in stock:");
            products.stream().filter(p -> p.getStock() <= 0).forEach(Console::writeLine);
        }
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte varsa stokta bulunmayan ürünler listelenmiş, tüm ürünler stoktaysa uygun mesaj verilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        if (products.stream().allMatch(p -> p.getStock() > 0))
            Console.writeLine("All products in stock!...");
        else {
            Console.writeLine("Products not in stock:");
            products.stream().filter(p -> p.getStock() <= 0).forEach(Console::writeLine);
        }
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin* `findFirst` *metotları ilgili Stream'in ilk elemanına geri döner. Stream boş olabileceği için bu metotların geri dönüş değerleri ilgili optional sınıfı türündendir*

>*Aşağıdaki demo örnekte stokta bulunmayan ilk ürünün bilgileri listelenmiştir. Tüm ürünler stokta ise uygun mesaj verilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        products.stream()
                .filter(p -> p.getStock() <= 0)
                .findFirst()
                .ifPresentOrElse(Console::writeLine, () -> Console.writeLine("All products are in stock!..."));
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzülerinin* `takeWhile` *metodu aldığı predicate callback'e ilişkin koşul'un gerçeklendiği sürece stream elde edilir. takeWhile metodu tipik while döngü deyime benzetilebilir.*

>*Aşağıdaki demo örnekte int türü sınırları içerisinde rassal olarak üretilen ilk asal sayıya kadar olan sayılar listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;
import java.util.stream.IntStream;

class Application {

    public static void run(String[] args)
    {
        var random = new Random();

        IntStream.generate(random::nextInt).takeWhile(NumberUtil::isNotPrime).forEach(a -> Console.write("%d ", a));
        Console.writeLine();
    }
}
```

>*Aşağıdaki demo örnekte klavyeden sıfır girilene kadar alınan int türden sayıların toplamı bulunmuştur. Sınır taşması gözardı edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.IntStream;

class Application {
    public static void run(String[] args)
    {
        var total = IntStream.generate(() -> Console.readInt("Bir sayı giriniz:")).takeWhile(v -> v != 0).sum();

        Console.writeLine("Toplam:%d", total);
    }
}

+
```

>*Aşağıdaki demo örnekte int türden bir dizi içerisinde ki ilk asal sayıya kadar olan sayılar listelenmiştir. Dizi elemanları bir dosyadan okunmaktadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NumberFactory;
import org.csystem.util.numeric.NumberUtil;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");
            var factory = NumberFactory.loadFromTextFile(args[0]);
            var numbers = factory.getNumbers();

            Arrays.stream(numbers).takeWhile(NumberUtil::isNotPrime).forEach(a -> Console.write("%d ", a));
            Console.writeLine();
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte klavyeden elma girilene kadar alınan yazılar aralarında `-` konarak birleştirilmiştir. İleride daha iyisi yine stream API kullanılarak yapılacaktır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.stream.Stream;

class Application {
    public static void run(String[] args)
    {
        var str = Stream.generate(() -> Console.readString("Bir yazı giriniz:"))
                .takeWhile(s -> !"elma".equals(s)).reduce("", (r, s) -> r + s + "-");

        Console.writeLine(str.substring(0, str.length() - 1));
    }
}
```

>*Aşağıdaki demo örnekte üretilen asal olmayan sayıların toplamı bulunurken sayılar da listelenmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var random = new Random();
            var a = Integer.parseInt(args[0]);
            var b = Integer.parseInt(args[1]) + 1;

            var total = IntStream.generate(() -> random.nextInt(a, b)).takeWhile(NumberUtil::isNotPrime)
                    .peek(v -> Console.write("%d ", v)).sum();

            Console.writeLine("%nTotal:%d", total);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte stokta bulunan ürünler listenmiş ve hepsi satıldığında kar zarar durumu hesaplanmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        var total = products.stream()
                .filter(p -> p.getStock() > 0)
                .peek(Console::writeLine)
                .map(p -> p.getPrice().subtract(p.getCost()).multiply(BigDecimal.valueOf(p.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Console.writeLine("Total:%s", total);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte stokta bulunmayan ancak sipariş edilmiş olan (stok miktarı negatif olan) ürünler listenmiş ve tedarik edilip satıldığında kar zarar durumu hesaplanmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        var total = products.stream()
                .filter(p -> p.getStock() < 0)
                .peek(Console::writeLine)
                .map(p -> p.getPrice().subtract(p.getCost()).multiply(BigDecimal.valueOf(p.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add).abs();

        Console.writeLine("Total:%s", total);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin* `distinct` *metotları stream'e ilişkin elemanlardan tekrarlı olanlardan bir tane olacak şekilde stream elde edilmesini sağlar.* `Stream<T>` *arayüzünün distinct metodu aynı olup olmama durumu için* `equals` ve `hashCode` *metotlarını kullanır. Bu metot sıralı stream'ler (ordered stream) aynı olan elemanlardan stream içerisinde önce olanı alacağını garanti eder (stable), sıralı olmayan stream'ler (unordered stream) için bunu garanti etmez. Sıralı ve sırasız stream'ler ileride ayrıca ele alınacaktır*

>*Aşağıdaki demo örnekte stokta bulunmayan ancak sipariş edilmiş olan (stok miktarı negatif olan) ürünler listenmiş ve tedarik edilip satıldığında kar zarar durumu hesaplanmıştır. Aynı ürünlerden yalnızca bir tanesi alınmıştır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory)
    {
        var products = factory.PRODUCTS;

        var total = products.stream()
                .filter(p -> p.getStock() < 0)
                .distinct()
                .peek(Console::writeLine)
                .map(p -> p.getPrice().subtract(p.getCost()).multiply(BigDecimal.valueOf(p.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add).abs();

        Console.writeLine("Total:%s", total);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(Application::dataExistsCallback, Application::dataNotExistsCallback);
        }
        catch (DateTimeParseException ignore) {
            Console.Error.writeLine("Invalid date value(s)!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örnekte, komut satırından alınan count kadar yine komut satırından alınan sınırlar arasında birbirinden farklı rassal sayılar elde edilmiştir. Örnekte sınırlar içerisindeki tüm elemanlar üretildiğinde hala üretilmesi gerekiyorsa yani count değeri sınırlar içerisinde üretilebilecek tekrarsız değerlerin sayısından büyükse sonsuz döngü oluşur*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(3, args.length, "wrong number of arguments!...");

        try {
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var a = Integer.parseInt(args[1]);
            var b = Integer.parseInt(args[2]) + 1;

            IntStream.generate(() -> random.nextInt(a, b))
                    .distinct()
                    .limit(count)
                    .forEach(v -> Console.write("%d ", v));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(3, args.length, "wrong number of arguments!...");

        try {
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var a = Integer.parseInt(args[1]);
            var b = Integer.parseInt(args[2]) + 1;

            IntStream.generate(() -> random.nextInt(a, b))
                    .limit(count)
                    .distinct()
                    .forEach(v -> Console.write("%d ", v));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Stream arayüzlerinin sorted metotları, stream'e ilişkin elemanların doğal sıralanmış (natural sort order) stream'ini elde etmek için kullanılır.* `Stream<T>` *arayüzünün parametresiz sorted metodu tipik olarak* `Comparable<T>` *arayüzüne göre işlem yapar. Bu arayüzün Comparator parametreli metodu tipik olarak sıralama kriterini callback olarak alır*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(3, args.length, "wrong number of arguments!...");

        try {
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var a = Integer.parseInt(args[1]);
            var b = Integer.parseInt(args[2]) + 1;

            IntStream.generate(() -> random.nextInt(a, b))
                    .distinct()
                    .limit(count)
                    .sorted()
                    .forEach(v -> Console.write("%d ", v));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Stream API içerisinde bazı terminal işlemleri elde edilen stream'e ilişkin elemanlardan başka bir veri yapısı elde etmeye yöneliktir. Bu anlamda bir Stream'den bir dizi ya da bir collection gibi veri yapıları elde edilebilir. Şüphesiz bu durumda artık tutulan elemanlar da kopyalanmış olur. Bu işlemler dışında stream içerisinde elde edilen kaynağa ilişkin elemanların genel olarak kopyaları çıkartılmaz. Bu sebeple stream işlemleri oldukça efektif çalışma eğilimindedir*

>*Stream arayüzlerinin* `toArray` *metotları ile ilgili stream'e ilişkin elemanlardan bir dizi elde edilebilir. IntStream, LongStream ve Double sınıflarının* `toArray` *metotları birer tanedir ve parametresizdir. Bu metotlar sırasıyla int türden long türden ve double türden dizi referansına geri dönerler.* `Stream<T>` *arayüzünün iki tane* `toArray` *metodu vardır.*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(3, args.length, "wrong number of arguments!...");

        try {
            var random = new Random();
            var count = Integer.parseInt(args[0]);
            var a = Integer.parseInt(args[1]);
            var b = Integer.parseInt(args[2]) + 1;

            var numbers = IntStream.generate(() -> random.nextInt(a, b))
                    .distinct()
                    .limit(count)
                    .sorted()
                    .peek(v -> Console.write("%d ", v))
                    .toArray();

            Console.writeLine();

            Arrays.stream(numbers).forEach(v -> Console.write("%d ", v));
            Console.writeLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>`Stream<T>` *arayüzünün parametresiz* `toArray` *metodu stream'e ilişkin elemanlardan oluşan* `Object[]` *referansına geri döner. Yani bu metot* `Object` *türden bir dizi referansına geri döner. İçsel olarak üretilen dizi genel olarak Stream'in açılımına yönelik  olmayacağından (yani örneğin* `Stream<String>` ise `String[]` *oluşturulmayabileceğinden) bu diziyi ilgili türden diziye cast işlemi* `ClassCastException` *oluşumuna yol açar. Bu durumda programcı ilgili dizinin elemanlarını dolaşırken her bir elemanı dinamik türler homojense doğrudan cast işlemi ile, heterojense tür kontrolü yaparak ilgili türe cast işlemi yapmalıdır*

>*Aşağıdaki demo örnekte stream ile elde edilen isimlerden içerisinde komut satırı argümanı ile alınan yazının case insensitive olarak geçtiği yazılar bir dizi olarak elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.datasource.factory.NameFactory;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream().map(String::toLowerCase).filter(s -> s.contains(text)).toArray();

            ArrayUtil.forEach(names, Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>`Stream<T>` *arayüzünün* `IntFunction<A[]>` *parametreli* `toArray` *metodu parametresi ile aldığı callback'e ilişkin dizi elde edilmesini sağlar. Bu durumda* `IntFunction<T>` *için* `apply` *metodunun parametresi ilgili elemanın indeks numarasıdır.* `apply` *metodu* `A[]` *(yani generic dizi) türüne geri döner. Bu durumda* `toArray` *generic metodu da callback ile elde edilen dizi referansına geri döner.*

>*Aşağıdaki demo örnekte stream ile elde edilen isimlerden içerisinde komut satırı argümanı ile alınan yazının case insensitive olarak geçtiği yazılar bir dizi olarak elde edilmiştir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.datasource.factory.NameFactory;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream().map(String::toLowerCase).filter(s -> s.contains(text)).toArray(String[]::new);

            ArrayUtil.forEach(names, Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.datasource.generator.random.ObjectArrayGenerator;

import java.util.Arrays;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(1, args.length, "wrong number of arguments!...");

        try {
            var count = Integer.parseInt(args[0]);
            var generator = new ObjectArrayGenerator();
            var objects = Arrays.stream(generator.createObjects(count))
                    .peek(Console::writeLine)
                    .filter(o -> !(o instanceof Double))
                    .toArray();

            Console.writeLine("###################################################");
            ArrayUtil.forEach(objects, Console::writeLine);

        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Stream'ler verilerin organizasyonu bakımından iki gruba ayrılır: ordered, unordered. ordered bir stream ile ara bir işlem yapıldığında dizilim değişmez. unordered stream'lerde ara bir işlemde dizilimin aynı kalacağı garanti değildir. Stream elde edilen bazı kaynaklar kendileri dizilim anlamında ordered olduklarından elde edilen stream'ler de ordered olarak alınır. Örneğin bir* `List<E>`*'den elde edilen bir stream ordered'dır. Ancak bir HashSet'den elde edilen stream ordered değildir. Stream arayüzlerinin unordered metodu ile (aslında BaseStream arayüzünün metodudur) stream ordered veya unordered bakımdan nasıl olursa olsun unordered bir stream elde edilir.*

>`A` *sınıfından* `B` *sınıfı türetilmiş olsun, X generic sınıfı için* `X<A>` *türüne* `X<B>` *türü explicit olarak bile atanamaz. Buna invariant/invariance denir. Bu anlamda generic türler default olarak invariant'dır. Bu işlemin doğrudan (implicit) yapılabilmesine ise covariant/covariance denir. Generic bir tür,* `? ve extends` *anahtar sözcüğü ile bildirilerek covariant yapılabilir. Java'da generic tür bildiriminde tür covariant yapılabilir. Bu işlemin tersine contravariant/contravariance denilmektedir. Bu da* `? ve super` *anahtar sözcüğü ile yapılabilir. Aşağıdaki demo örneği inceleyiniz. Dikkat edilirse contravariance bir üst sınır, contravariance ise bir alt sınır belirtir. Burada üst sınır açılıma ilişkin türden türemiş sınıflarını, alt sınır ise açılıma ilişkin türün taban sınıflarını belirtir*

```java
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        X<B> xB = new X<>();
        X<A> xA = new X<>();

        Sample.foo(xB);
        Sample.bar(xA);
    }
}

class Sample {
    public static void foo(X<? extends A> x) //covariance
    {
        //...
    }

    public static void bar(X<? super B> x) //contravariance
    {

    }
}

class A {
    //...
}

class B extends A {
    //...
}

class X<T> {
    //...
}
```

>*Referans dizileri covariance özelliğe sahiptir. Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.Arrays;

class Application {
    public static void run(String[] args)
    {
        Integer [] a = {1, 2, 3, 4, 5};
        Long [] b = {1L, 2L, 3L, 4L, 5L};
        Double [] c = {1., 2., 3., 4., 5.};

        Console.writeLine(Util.sum(a));
        Console.writeLine(Util.sum(b));
        Console.writeLine(Util.sum(c));
    }
}

class Util {
    public static Number sum(Number [] numbers)
    {
        return Arrays.stream(numbers).mapToDouble(Number::doubleValue).sum();
    }
}
```

>*Aşağıdaki demo örnekte `sum` metodunun parametresine ilişkin açılıma üstten sınır verilmeseydi error oluşurdu*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayList;
import java.util.List;

class Application {
    public static void run(String[] args)
    {
        List<Integer> iList = new ArrayList<>();
        List<Long> lList = new ArrayList<>();
        List<Double> dList = new ArrayList<>();

        //...

        Console.writeLine(Util.sum(iList));
        Console.writeLine(Util.sum(lList));
        Console.writeLine(Util.sum(dList));
    }
}

class Util {
    public static Number sum(List<? extends Number> numbers)
    {
        return numbers.stream().mapToDouble(Number::doubleValue).sum();
    }
}
```

>*Contravariance olarak belirlenmiş bir türe ilişkin değerler ancak elde edilebilir, değiştirilemez. Yani bu anlamda yazma (write/put) yapılamaz, okuma (read/get) yapılabilir. Contravariant için ise bu işlemlerin her ikisi de yapılabilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.DoubleStream;


class Util {
    public static void doWork(RandomGenerator randomGenerator, List<? extends Number> numbers, int count, double a, double b)
    {
        DoubleStream.generate(() -> randomGenerator.nextDouble(a, b)).limit(count).forEach(rv -> numbers.add(rv)); //error
        numbers.forEach(Console::writeLine);
    }

    public static void doSomething(RandomGenerator randomGenerator, List<? super Double> numbers, int count, double a, double b)
    {
        DoubleStream.generate(() -> randomGenerator.nextDouble(a, b)).limit(count).forEach(numbers::add);
        numbers.forEach(Console::writeLine);
    }
}
```


>`Stream<T>` *arayüzünün collect metodu ile bir Stream'in elemanlarından oluşan bir veri yapısı ya da değer elde edilebilir. collect metodu* `Collector<T, A, R>` *isimli generic bir arayüz referansını parametre olarak alır. Bu arayüzün birinci generic tür parametresi girdiye ilişkin yani Stream'in açılımına ilişkin türdür. İkinci generic tür parametresi accumulator olarak kullanılır. İçsel bir parametredir. Genel olarak Stream'i kullananları ilgilendirmez. Yazanları ilgilendirir. Üçüncü generic parametresi ise elde edilecek sonucun türüdür.* `Collector<T, A, R>` *elde etmek için tipik olarak Collectors utility sınıfının metotları kullanılır. collect metoduna Collectors sınıfının metotları geçilerek ilgili sonuç elde edilir. Collectors sınıfının metotları en çok yapılan işlemlere ilişkin metotlardır.*

>*Collectors sınıfının toList metodu ilgili stream'den* `List<E>` *elde etmek için kullanılır. Bu metottan elde edilen collection sınıfın immutable ya da mutable olup olmadığı garanti değildir. Genel olarak stream'e ilişkin kaynağa bağlıdır (implementation defined). Bu sebeple programcı, elde ettiği listede değişiklik yapmak isterse dikkatli olmalıdır. Hatta emin olmadıkça yapmamalıdır*
>
>`Java 10` *ile birlikte* `Collectors` *sınıfına* `toUnmodifiableList` *isimli bir metot eklenmiştir. Bu metottan elde edilen collection sınıf immutable/unmodifiable özelliktedir. Metot, stream'e ilişkin bir eleman null ise* `NullPointerException` *fırlatır. Yani bu collection null referans kabul etmez, elde edilen collection sınıfta değişiklik yapılamaz.*
>
>`Java 16` *ile birlikte* `Stream` *arayüzüne* `toList`, *default metot olarak eklenmiştir. Bu metot immutable/unmodifiable collection sınıfına geri döner. Bu metottan elde edilen* `List<T>` *türünden referans ile listede değişiklik yapılması durumunda* `UnsupportedOperationException` *fırlatılır.*
>
>*Bu durumda programcı* `Java 17` *ve sonrası ile çalışıyorsa* `Stream<T>` *arayüzünün toList metodunu çağırmalıdır. Programcı elde edilen collection sınıfta değişiklik yapmak isterse bu durumda ilgili elemanları da içeren yeni bir collection sınıf yaratabilir.*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NameFactory;

import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .collect(Collectors.toList());

            names.forEach(Console::writeLine);

        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NameFactory;

import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .collect(Collectors.toUnmodifiableList());

            names.forEach(Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NameFactory;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .toList();

            names.forEach(Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getClass().getName());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.collection.CollectionUtil;
import org.csystem.util.datasource.factory.NameFactory;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .toList();

            names.forEach(Console::writeLine);
            names = CollectionUtil.toModifiableList(names);
            names.add("ali");
            names.forEach(Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getClass().getName());
        }
    }
}
```

>`Collectors` *sınıfının* `toSet` *metodu ilgili stream'den* `Set<E>` *elde etmek için kullanılır. Bu metottan elde edilen collection sınıfın immutable ya da mutable olup olmadığı garanti değildir. Genel olarak stream'e ilişkin kaynağa bağlıdır (implementation defined). Bu sebeple programcı, elde ettiği set'te değişiklik yapmak isterse dikkatli olmalıdır. Hatta emin olmadıkça yapmamalıdır.*
>
>`Java 10` *ile birlikte* `Collectors` *sınıfına* `toUnmodifiableSet `*isimli bir metot eklenmiştir. Bu metottan elde edilen collection sınıf immutable/unmodifiable özelliktedir. Metot, stream'e ilişkin bir eleman null ise NullPointerException fırlatır. Yani bu collection null referans kabul etmez, elde edilen collection sınıfta değişiklik yapılamaz.*
>
>*Bu metotlar eşitlik kontrolü için equals ve hashCode metotlarını kullanırlar*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NameFactory;

import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();

            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .collect(Collectors.toSet());

            names.forEach(Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getClass().getName());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NameFactory;

import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "wrong number of arguments!...");

        try {
            var factory = NameFactory.loadFromTextFile(args[0]);
            var text = args[1].toLowerCase();
            var names = factory.NAMES.stream()
                    .map(String::toLowerCase)
                    .filter(s -> s.contains(text))
                    .collect(Collectors.toUnmodifiableSet());

            names.forEach(Console::writeLine);
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getClass().getName());
        }
    }
}
```

>`Collectors` *sınıfının* `toMap` *metotları tipik olarak* `Map<K, V>` *elde etmek için kullanılabilir. Bu metottan elde edilen collection sınıfın immutable ya da mutable olup olmadığı garanti değildir. Genel olarak stream'e ilişkin kaynağa bağlıdır (implementation defined). Bu sebeple programcı, elde ettiği set'te değişiklik yapmak isterse dikkatli olmalıdır. Hatta emin olmadıkça yapmamalıdır.*
>
>`Java 10` *ile birlikte Collectors sınıfına* `toUnmodifiableMap` *metotları eklenmiştir. Bu metotlardan elde edilen collection sınıf immutable/unmodifiable özelliktedir. Metot, stream'e ilişkin bir eleman null ise* `NullPointerException` *fırlatır. Yani bu collection null referans kabul etmez, elde edilen collection sınıfta değişiklik yapılamaz.*
>
>*Bu metotlar anahtarların eşitlik kontrolü için* `equals` ve `hashCode` *metotlarını kullanırlar*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;
import org.csystem.util.datasource.product.ProductConverter;
import org.csystem.util.datasource.product.ProductInfo;
import org.csystem.util.string.StringUtil;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory, String text)
    {
        var productMap = factory.PRODUCTS.stream()
                .filter(p -> StringUtil.containsIgnoreCase(p.getName(), text))
                .collect(Collectors.toMap(ProductInfo::getId, p -> new ProductConverter(p).toProductNameStockDTO()));

        productMap.keySet().forEach(k -> Console.writeLine("%d, %s", k, productMap.get(k)));
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(f -> dataExistsCallback(f, args[1]), Application::dataNotExistsCallback);
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>`IntStream`, `LongStream` ve `DoubleStream` *arayüzlerinin* `boxed` *metotları ilgili stream'den* `Stream<T>` *arayüzünün ilgili sarmalayan sınıf açılımı türünden Stream referansına geri döner. Bu durumda kutulama işlemi,* `mapToObj` *metodu yerine* `boxed` *metodu kullanılarak yapılabilir*

>*Aşağıdaki demo örnekte* `Integer` *türüne kutulama yapılmaktyadır*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NumberFactory;
import org.csystem.util.numeric.NumberUtil;

import java.io.IOException;
import java.util.Arrays;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");
            var factory = NumberFactory.loadFromTextFile(args[0]);
            var numbers = factory.getNumbers();

            var numberList = Arrays.stream(numbers)
                    .takeWhile(NumberUtil::isNotPrime)
                    .mapToObj(a -> a) //boxing
                    .toList();


            numberList.forEach(v -> Console.write("%d ", v));
            Console.writeLine();
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Yukarıdaki örnek* `boxed` *metodu kullanılarak yapılabilir*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NumberFactory;
import org.csystem.util.numeric.NumberUtil;

import java.io.IOException;
import java.util.Arrays;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");
            var factory = NumberFactory.loadFromTextFile(args[0]);
            var numbers = factory.getNumbers();

            var numberList = Arrays.stream(numbers)
                    .takeWhile(NumberUtil::isNotPrime)
                    .boxed()
                    .toList();


            numberList.forEach(v -> Console.write("%d ", v));
            Console.writeLine();
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.NumberFactory;
import org.csystem.util.numeric.NumberUtil;

import java.io.IOException;
import java.util.Arrays;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(1, args.length, "wrong number of arguments!...");
            var factory = NumberFactory.loadFromTextFile(args[0]);
            var numbers = factory.getNumbers();

            var numberList = Arrays.stream(numbers)
                    .takeWhile(NumberUtil::isNotPrime)
                    .mapToObj(v -> v * v)
                    .toList();

            numberList.forEach(v -> Console.write("%d ", v));
            Console.writeLine();
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>`IntStream`, `DoubleStream` ve `LongStream` *arayüzünün* `summaryStatistics` *metotları sırasıyla* `IntSummaryStatistics`, `DoubleSummaryStatistics` ve `LongSummaryStatistics` *türünden referansa geri dönerler. Bu sınıfların getAverage, getSum, getCount, getMin, getMax gibi metotları ile stream'e ilişkin istatistiksel bilgiler elde edilebilir*

>*Aşağıdaki demo örnekte klavyeden sıfır girilene kadar alınan int türden sayılara ilişkin çeşitli istatistiksel bilgiler elde edilmiştir*

```java
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
                    result.getSum(), result.getAverage(), result.getCount(), result.getMax(), result.getMin());
        else
            Console.writeLine("Hiç sayı girmediniz!...");
    }
}
```

>`Collectors` *sınıfının* `joining` *metotları yazı birleştime işlemlerinde kullanılır. Parametresiz joining metodu stream'e ilişkin yazıları doğrudan birleştirir. Tek parametreli (*`CharSequence` *parametreli) joining metodu stream'e ilişkin yazıların arasına (delimiter) parametresi ile aldığı yazıyı koyarak birleştirmek için kullanılır. 3 parametreli joining metodunun birinci parametresi ayraç, ikinci parametresi elde edilecek yazının başında bulunacak yazı, üçüncü parametresi elde edilecek yazının sonunda bulunacak yazıdır. Bu metotların paramtreleri* `CharSequence` *türündendir*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;
import org.csystem.util.datasource.product.ProductInfo;
import org.csystem.util.string.StringUtil;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory, String text)
    {
        var str = factory.PRODUCTS.stream()
                .map(ProductInfo::getName)
                .filter(n -> StringUtil.containsIgnoreCase(n, text))
                .collect(Collectors.joining());

        Console.writeLine(str);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(f -> dataExistsCallback(f, args[1]), Application::dataNotExistsCallback);
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;
import org.csystem.util.datasource.product.ProductInfo;
import org.csystem.util.string.StringUtil;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory, String text, String delimiter)
    {
        var str = factory.PRODUCTS.stream()
                .map(ProductInfo::getName)
                .filter(n -> StringUtil.containsIgnoreCase(n, text))
                .collect(Collectors.joining(delimiter));

        Console.writeLine(str);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(3, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(f -> dataExistsCallback(f, args[1], args[2]), Application::dataNotExistsCallback);
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.ProductFactory;
import org.csystem.util.datasource.product.ProductInfo;
import org.csystem.util.string.StringUtil;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void dataExistsCallback(ProductFactory factory, String text, String delimiter, String prefix, String suffix)
    {
        var str = factory.PRODUCTS.stream()
                .map(ProductInfo::getName)
                .filter(n -> StringUtil.containsIgnoreCase(n, text))
                .collect(Collectors.joining(delimiter, prefix, suffix));

        Console.writeLine(str);
    }

    private static void dataNotExistsCallback()
    {
        Console.writeLine("No product exists'...");
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(5, args.length, "wrong number of arguments!...");

            ProductFactory.loadFromTextFile(args[0])
                    .ifPresentOrElse(f -> dataExistsCallback(f, args[1], args[2], args[3], args[4]), Application::dataNotExistsCallback);
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.PersonFactory;
import org.csystem.util.datasource.people.Person;

import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var factory = PersonFactory.loadFromTextFile(args[0]);
            var threshold = Double.parseDouble(args[1]);
            factory.PEOPLE.stream()
                    .filter(p -> p.getAge() > threshold)
                    .collect(Collectors.minBy(Comparator.comparingDouble(Person::getAge)))
                    .ifPresentOrElse(Console::writeLine, () -> Console.writeLine("No person!..."));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid age threshold value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>`Collectors` *sınıfının* `partioningBy` *metodu aldığı callback'e ilişkin koşula uyan elemanları elde edilen* `Map<Boolean, List<T>>` *veri yapısının true anahtarına karşılık gelen listeye, koşula uymayan elemanları ise elde edilen* `Map<Boolean, List<T>>` *veri yapısının false anahtarına karşılık gelen listeye ekler.*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.PersonFactory;
import org.csystem.util.datasource.people.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static void printLess(Map<Boolean, List<Person>> map, double threshold)
    {
        if (!map.get(true).isEmpty()) {
            Console.writeLine("People less than %f:", threshold);
            map.get(true).forEach(Console::writeLine);
        }
        else
            Console.writeLine("No people less than %f", threshold);
    }

    private static void printGreaterOrEqual(Map<Boolean, List<Person>> map, double threshold)
    {
        if (!map.get(false).isEmpty()) {
            Console.writeLine("People greater or equal than %f:", threshold);
            map.get(false).forEach(Console::writeLine);
        }
        else
            Console.writeLine("No people greater or equal than %f", threshold);
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var factory = PersonFactory.loadFromTextFile(args[0]);
            var threshold = Double.parseDouble(args[1]);
            var map = factory.PEOPLE.stream()
                    .collect(Collectors.partitioningBy(p -> p.getAge() < threshold));

            printLess(map, threshold);
            printGreaterOrEqual(map, threshold);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid age threshold value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>`Collectors` *sınıfının* `groupingBy` *metotları aldıkları callback'e göre sınıflandırma (gruplama) işlemi yapar. Elde edilen* `Map<K, List<T>>` *veri yapısına ilişkin her bir anahtara (gruba) karşılık gelen elemanlar kullanılabilir*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.PersonFactory;
import org.csystem.util.datasource.people.MaritalStatus;
import org.csystem.util.datasource.people.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void print(Map<MaritalStatus, List<Person>> map, MaritalStatus maritalStatus)
    {
        if (!map.get(maritalStatus).isEmpty()) {
            Console.writeLine("'%s' people:", maritalStatus.toString().toLowerCase());
            map.get(maritalStatus).forEach(Console::writeLine);
        }
        else
            Console.writeLine("No '%s' person!...", maritalStatus.toString().toLowerCase());
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var factory = PersonFactory.loadFromTextFile(args[0]);
            var threshold = Double.parseDouble(args[1]);
            var map = factory.PEOPLE.stream()
                    .filter(p -> p.getAge() > threshold)
                    .collect(Collectors.groupingBy(Person::getMaritalStatus));

            print(map, MaritalStatus.SINGLE);
            print(map, MaritalStatus.MARRIED);
            print(map, MaritalStatus.DIVORCED);
            print(map, MaritalStatus.WIDOW);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid age threshold value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>`Iterable<E>` *arayüzünden stream elde etmek için tipik olarak StreamSupport isimli utility sınıf kullanılır. Bu sınıfın pek çok yararlı metodu vardır.* `Spliterator<T>` *parametreli* `stream` *metodu ile* `Iterable<E>` *arayüzünden stream elde edilebilir. Bu işlem için* `Iterable<E>` *arayüzünün splitirator metodu kullanılabilir. Bu metot* `Spliterator<E>` *referansına geri döner. stream metodunun ikinci parametresi stream'in paralel olup olmadığına ilişkin flag değeridir*

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.datasource.factory.PersonFactory;
import org.csystem.util.datasource.people.MaritalStatus;
import org.csystem.util.datasource.people.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void print(Map<MaritalStatus, List<Person>> map, MaritalStatus maritalStatus)
    {
        if (!map.get(maritalStatus).isEmpty()) {
            Console.writeLine("'%s' people:", maritalStatus.toString().toLowerCase());
            map.get(maritalStatus).forEach(Console::writeLine);
        }
        else
            Console.writeLine("No '%s' person!...", maritalStatus.toString().toLowerCase());
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var factory = PersonFactory.loadFromTextFile(args[0]);
            var threshold = Double.parseDouble(args[1]);
            var people = factory.getPeopleAsIterable();
            var map = StreamSupport.stream(people.spliterator(), false)
                    .filter(p -> p.getAge() > threshold)
                    .collect(Collectors.groupingBy(Person::getMaritalStatus));

            print(map, MaritalStatus.SINGLE);
            print(map, MaritalStatus.MARRIED);
            print(map, MaritalStatus.DIVORCED);
            print(map, MaritalStatus.WIDOW);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid age threshold value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

>*Aşağıdaki demo örneği inceleyiniz*

```java
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.collection.CollectionUtil;
import org.csystem.util.datasource.factory.PersonFactory;
import org.csystem.util.datasource.people.MaritalStatus;
import org.csystem.util.datasource.people.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void print(Map<MaritalStatus, List<Person>> map, MaritalStatus maritalStatus)
    {
        if (!map.get(maritalStatus).isEmpty()) {
            Console.writeLine("'%s' people:", maritalStatus.toString().toLowerCase());
            map.get(maritalStatus).forEach(Console::writeLine);
        }
        else
            Console.writeLine("No '%s' person!...", maritalStatus.toString().toLowerCase());
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var factory = PersonFactory.loadFromTextFile(args[0]);
            var threshold = Double.parseDouble(args[1]);
            var people = CollectionUtil.stream(factory.getPeopleAsIterable());
            var map = people
                    .filter(p -> p.getAge() > threshold)
                    .collect(Collectors.groupingBy(Person::getMaritalStatus));

            print(map, MaritalStatus.SINGLE);
            print(map, MaritalStatus.MARRIED);
            print(map, MaritalStatus.DIVORCED);
            print(map, MaritalStatus.WIDOW);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid age threshold value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
```

##### Records

>Java 14 ile birlikte preview olarak Java 16 ile birlikte de release olarak **record** eklenmiştir. Bu durumda LTS olarak en az 17 sürümü ile kullanılabilmektedir. Bir record da referans türüdür. Bu anlamda aslında belirli özelliklere sahip sınıflardır. Bir record immutable olarak bildirilmiş final bir sınıf, equals, hashCode ve toString metotlarının override edilmiş biçimde bildirilmiş olur. equals ve hashCode metotları tüm veri elemanlaro kullanılarak yazılmış olur. İstenirse programcı record içerisinde bu metotları override edebilir. record içerisinde veri elemanlarında sınıf isminde bildirilen değişken isimleri ile erişilebilir. Bir record herhangi sınıftan türetilemez ancak istediği kadar interface'i destekleyebilir.

>Aşağıdaki Device sınıfını ve record karşılığını inceleyiniz
```java
final class Device {  
    private final String m_name;  
    private final String m_host;  
    private final int m_port;  
  
    public Device(String name, String host, int port)  
    {  
        m_name = name;  
        m_host = host;  
        m_port = port;  
    }  
  
    public String name()  
    {  
        return m_name;  
    }  
  
    public String host()  
    {  
        return m_host;  
    }  
  
    public int port()  
    {  
        return m_port;  
    }  
  
    @Override  
    public int hashCode()  
    {  
        return Objects.hash(m_name, m_host, m_port);  
    }  
  
    @Override  
    public boolean equals(Object other)  
    {  
        return other instanceof Device d && d.m_name.equals(m_name) && d.m_host.equals(m_host) && d.m_port == m_port;  
    }  
  
    @Override  
    public String toString()  
    {  
        return "Device[name=%s, host=%s, port=%d]".formatted(m_name, m_host, m_port);  
    }  
}
```

```java
package org.csystem.app;  
  
import com.karandev.io.util.console.Console;  
  
class Application {  
    public static void run(String[] args)  
    {  
        var dev1 = new Device("Rain sensor", "192.168.124", 3030);  
        var dev2 = new Device("Rain sensor", "192.168.124", 3030);  
  
        Console.writeLine(dev1);  
        Console.writeLine(dev2);  
        Console.writeLine(dev1.equals(dev2));  
        Console.writeLine(dev1.hashCode());  
        Console.writeLine(dev2.hashCode());  
        Console.writeLine("%s, %s %d", dev1.name(), dev1.host(), dev1.port());  
        Console.writeLine("%s, %s %d", dev2.name(), dev2.host(), dev2.port());  
    }  
}  
  
record Device(String name, String host, int port) {  
}
```

>Aşağıdaki örnekte record'un toString metodu override edilmiştir

```java
package org.csystem.app;  
  
import com.karandev.io.util.console.Console; 
  
class Application {  
    public static void run(String[] args)  
    {  
        var dev1 = new Device("Rain sensor", "192.168.124", 3030);  
        var dev2 = new Device("Rain sensor", "192.168.124", 3030);  
  
        Console.writeLine(dev1);  
        Console.writeLine(dev2);  
        Console.writeLine(dev1.equals(dev2));  
        Console.writeLine(dev1.hashCode());  
        Console.writeLine(dev2.hashCode());  
        Console.writeLine("%s, %s %d", dev1.name(), dev1.host(), dev1.port());  
        Console.writeLine("%s, %s %d", dev2.name(), dev2.host(), dev2.port());  
    }  
}  
  
record Device(String name, String host, int port) {  
    @Override  
    public String toString()  
    {  
        return "%s, %s, %d".formatted(name, host, port);  
    }  
}
```

>Bir record için şu ctor'ları yazılabilir:
>1. Tüm veri elemanların parametre olarak alan ctor: Bu ctor'un parametre değişkenleri ile aynı isimde getter/accessor ve setter/mutator metotlar otomatik olarak yazılır. Bu ctor'a bazı kaynaklarda primary ctor da denilmektedir
>2. Canonical ctor: Bu ctor sınıf içerisinde { ve } olarak yazılır. Bu non-static initializer'a benzetilebilir ancak sentaks olarak record ismi de kullanılmalıdır. Ayrıca non-static initializer'dan farklı olarak arka planda yaratılan veri elemanları (back field) değerlerin ctor ile almış olur. Halbuki non-static initializer ctor'dan önce çağrılır. Burada tipik olarak ctor'a geçilen argümanlara ilişkin kontrol gibi işlemler yapılabilir
>3. Herhangi bir parametrik yapıda ctor: Bu ctor'lar içerisinde tüm elemanlara değer verilmelidir. Bu işlem `this ctor sentaksı` ile yapılmalıdır.

>Aşağıdaki örneği inceleyiniz

```java
package org.csystem.app;  
  
import com.karandev.io.util.console.Console;  
  
import java.io.Closeable;  
import java.io.Serializable;  
  
class Application {  
    public static void run(String[] args)  
    {  
        try (var dev1 = new Device("Rain sensor", "192.168.124", 3030);  
             var dev2 = new Device("Rain sensor", "192.168.124");  
             var dev3 = new Device("Rain sensor")) {  
  
            Console.writeLine(dev1);  
            Console.writeLine(dev2);  
            Console.writeLine(dev3);  
        }  
    }  
}  
  
record Device(String name, String host, int port) implements Closeable, Serializable {  
    Device {  
        if (port <= 0 || port > 65535)  
            throw new IllegalArgumentException("port number must in (0, 65536)");  
    }  
  
    public Device(String name, String host)  
    {  
        this(name, host, 6767);  
    }  
  
    public Device(String name)  
    {  
        this(name, "localhost");  
    }  
  
    @Override  
    public String toString()  
    {  
        return "%s, %s, %d".formatted(name, host, port);  
    }  
  
    @Override  
    public void close()  
    {  
        System.out.println("Close");  
    }  
}
```
