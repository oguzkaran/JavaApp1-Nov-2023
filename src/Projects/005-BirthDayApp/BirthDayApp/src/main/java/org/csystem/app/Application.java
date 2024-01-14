package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.birthdate.remainder.BirthDateOperations;

class Application {
    private static void printBirthDayMessage(BirthDateOperations operations)
    {
        var status = operations.getStatus();
        var age = operations.getAge();

        switch (status) {
            case BEFORE -> Console.writeLine("Geçmiş doğum gününüz kutlu olsun. Yeni yaşınız:%.2f", age);
            case AFTER -> Console.writeLine("Doğum gününüz şimdiden kutlu olsun. Yeni yaşınız:%.2f", age);
            case TODAY -> Console.writeLine("Doğum gününüz kutlu olsun. Yeni yaşınız:%.0f", age);
        }
    }

    public static void run(String[] args)
    {
        var day = Console.readInt("Gün bilgisini giriniz:", "Hatalı giriş yaptınız!...");
        var month = Console.readInt("Ay bilgisini giriniz:", "Hatalı giriş yaptınız!...");
        var year = Console.readInt("Yıl bilgisini giriniz:", "Hatalı giriş yaptınız!...");

        printBirthDayMessage(new BirthDateOperations(day, month, year));
    }
}
