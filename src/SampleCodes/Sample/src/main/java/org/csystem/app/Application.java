/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: stdin'den girilen doğum tarihi bilgisine göre aşağıdaki mesajlar uygun olanı stdout'a basan basit
    uygulamayı yazınız. Mesajlar:
        1. Doğum günü geçmişse          -> Geçmiş doğum gününüz kutlu olsun. Yeni yaşınız:...
        2. Doğum günü ise               -> Doğum gününüz kutlu olsun. Yeni yaşınız:...
        3. Doğum günü henüz gelmemişse  -> Doğum gününüz şimdiden kutlu olsun. Yeni yaşınız:...

    Burada yaş bilgisini istediğiniz duyarlılıkta yazdırabilirsiniz. Uygulama ayrı bir proje olarak yapılacaktır
----------------------------------------------------------------------------------------------------------------------*/
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
                Console.writeLine("KArtın son kullanma tarihi geçmiştir");
            else
                Console.writeLine("KArtın son kullanma tarihi geçmemiştir");
        }
    }
}


