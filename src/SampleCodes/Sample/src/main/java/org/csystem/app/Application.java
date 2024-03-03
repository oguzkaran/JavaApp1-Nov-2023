/*----------------------------------------------------------------------------------------------------------------------
    Optional Sınıfları: Programlamada sıklıkla karşımıza çıkan bir durum vardır: Bir koşul sağlandığında bir bilgiye
    elde etmek isteriz. Örneğin, T.C. Kimlik numarasına bir kişinin bilgilerini veren bir metot yazacak olalım. Bu metot
    parametre olarak aldığı kimlik numarasında bir kişi olmadığı durumda ne dönecektir? Şüphesiz, böylesi bir metot muhtemel
    bir sınıf türünden referansa geri dönecektir. Bu durumda bulunamadığı koşulda null adrese geri dönülebilir. Ancak
    null adrese geri dönülmesi metodu çağrıran programcı açısından farkedilmeden kullanımda NullPointerException
    oluşumuna da yol açabilecektir. Yine örneğin bir metot exception fırlatmadan yazıyı sayıya çevirmek isterse, geri dönüş
    değeri olarak ne dönecektir? Eğer int türüne geri dönerse geçersiz değer için hangi değere geri dönecektir? Şüphesiz,
    bu metot Integer referansına geri dönebilir ve dönüştürülememesi durumunda null değere geri döner. Bu da görece olarak
    okunabilir olmaz. Üstelik yine null adres döndürülmiş olur. Yine örneğin, bir kişinin adı, ikinci adı ve soyadı
    bilgileri tutulduğunda ikinci adı olmayan kişiler için bu bilgi null olarak veya boş string tutulabilir. Bu da
    yine iyi bir tasarım değldir.

    Programlamada böylesi durumlar genel adı "nullable type" olan (buradaki null terimi null adres anlamında değildir.
    Olmama kavramını temsil eder) "optional" sınıfları kullanılabilir. Java 8 ile birlikte 4 tane optional sınıfı JavaSE'ye
    eklenmiştir: Optional<T>, OptionalInt, OptionalDouble, OptionalLong.

    Optional sınıflarının empty metotları, boş bir optional oluşturmak için kullanılır. Optional sınıflarının ctor'ları
    private'dır. Optinal sınıflarının of metotları ile dolu bir optional ilgili değer verilerek yaratılabilir. Optional
    sınıflarının getXXX metotları ile ilgili optional nesnesi içerisindeki veri elde edilebilir. Boş bir optional
    nesnesi için getXXX metotları NoSuchElementException fırlatırlar. Optional sınıflarının isPresent isimli metodu
    ile nesnenin dolu olup olmadığı test edilebilir. Optional sınıflarının isEmpty isimli metodu ile nesnenin boş olup
    olmadığı test edilebilir. Optional sınıfları içerisinde java.util.function paketi içerisindeki arayüzleri callback
    olarak olan çeşitli metotlar da bulunur.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {

    }
}

