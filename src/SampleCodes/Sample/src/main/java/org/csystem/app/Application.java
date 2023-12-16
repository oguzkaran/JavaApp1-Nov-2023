/*----------------------------------------------------------------------------------------------------------------------
    Sisteme Giriş (login):
    UNIX sistemlerinde her kullanıcıya bir username ve bir passowrd verilir. Bir kullanıcı username ve password ile
    sisteme giriş yapar. Sisteme giriş yapmak genellikle 3 yoldan yapılabilir:
    1. Text tabanlı bir terminal program ile: Eğer sistemde bir Graphical User Interface (GUI) (tipik olarak Xwindow)
    yoksa bu yoldan giriş yapılır. Genellikle sunucu (server) sistemlere bu şekilde erişilr.

    2. GUI ile: Eğer sistemde bir GUI varsa bunlarla giriş yapılabilir.

    3. Uzak bağlantı (remote) yoluyla: Uzak bağlantı yoluyla erişim tipik olarak ssh ve telnet gibi bir protokolle text tabanlı
    olarak. VNC gibi protokol ile de GUI olarak yapılabilmektedir. Örneğin ssh ile bağlatı şu şekilde yapılabilir:

        ssh oguz@192.168.1.123

    Burada tipik olarak oguz kullanıcı ismi ve @ işaretinden sonra yazılan bilgi ise uzak makinenin adres bilgisidir.

    UNIX/Linux Sistemlerinde Yeni Kullanıcıların ve Grupların Yaratılması:
    UNIX sistemlerinin çoğunda kullanıcılara ilişkin bilgiler text dosalarda tutulur. Bu text dosyanın her satırı
    bir kullanıcıya ilişkin bilgilerden oluşur. Linux ve BSD sistemlerinde /etc/passwd dosyası kullanıcı bilgilerini
    tutan bir dosyadır. Her kullanıcının bilgisi burada tutulur. Bu dosya normal kullanıcılar için read omnly durumdadır.
    Yani bu dosyanın içeriğini normal kullanıcılar görüntüleyebilir ancak dosyada değişiklik yapamaz. Bir kullanıcıya
    ilişkin bilgiler : ile ayrılır ve toplam 7 tane sütun bulunur:

        csystem:x:1000:1000:JavaApp1-Mar-2023 Group,,,:/home/csystem:/bin/bash

    Buradaki 7 sütunun anlamları kabaca şu şekildedir:

    1. Kullancı ismi

    2. Kullanıcının passord'üne ilişkin encrypted bir bilgidir. Eskiden kullanıcılar şfrelenmiş parola bilgileri bu
    dosyada saklanırdı. Bu anlamda şifrelenmiş bilgilerin şifrelemesi tek yönlü (one way) yapıldığı için bu bilginin
    elde edilmesinde bir sakınca görülmemiştir. Zamanla bu bilginin de görülmesi istenmediğinden /etc/passwd dosyasında
    x olarak yazılmaya başlandı. Bu bilgi ayrı bir dosyada saklanır duruma geldi. Bu bilgi tipik olarak /etc/shodow
    dosyası içerisinde saklanır ve bu dosyanın içeriği normalk kullanıcılar tarafından okunamaz ve değiştirilemez.

    3. Kullanıcı id'si her kullanıcı ismine karşılık verilir. İki kullanıcının id'si aynı olamaz. Tipik olarak root
    kullanıcsının id bilgisi sıfırdır.

    4. Grup id'si her gruba karşılık verilir. Kullanıcıların ait olduğu grupların bilgileri de /etc/group dosyasında
    tutulur. Her yeni kullanıcı için default olarak ayrı bir grup oluşturulur

    5. Kullanıcıya ilişkin bilgiler bulunur. Bilgiler virgül ile ayrılır ve 4 bölüm vardır. Bilgiler boş geçilebilir ancak
    virgüller yine bulunur.

    6. Kullanıcı dizinine ilişkin yol ifadesi belirtilir

    7. Kullanıcının sisteme ilk giriş yaptığında çalıştırılacak terminal program belirtilir. Buradaki program default
    olarak çalıştırılır. Linux sistemlerinde default olarak bash (Bourne Again Shell) kullanılır.

    Öyleyse kullanıcı eklemek için tipik olarak /etc/passwd dosyasıne uygun satırı eklemek gerekir. Tabi bu durumda
    kullanıcı dizini, password ve grup id gibi bilgilerin de oluşturulması gerekir. Bu işlemleri manuel olarak yapmak
    oldukça zahmetli olabilmektedir. Bu sebeple adduser isimli bir komut vardır. Ancak bu komut pek user friendly değildir.
    Bu sebeple daha user friendly olan useradd isimli ayrı bir komut vardır. User oluşturabilmek için root yetkisine
    sahip olmak gerekir. root yetkisine sahip olan bir user'a "sudoer" denir. sudoer olan bir user ile login olunduğunda
    sudo (super user do) isimli komut ile root şifresi de girilerek root yetkisi elde edilebilir. Eğer user sudoer
    değilse kesinlikle root yetkisine sahip işlemleri yapamaz.

    Anahtar Notlar: Bazı lightweight sistemlerde kurulum sırasında root kullanıcısına ilişkin bilgiler sorulmaz. Tipik
    olarak Ubuntu ve Mint dağıtımları bu şekildedir. Bu sistemler kurulurken belirlenen ilk user sudoer yapılır ve
    parolası aynı zamanda root kullanıcısının da parolası olur.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {

    }
}
