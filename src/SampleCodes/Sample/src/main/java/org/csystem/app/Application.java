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


