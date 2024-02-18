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
        int day = 0, month = 0, year = 0;

        if (args.length != 3 && args.length != 0)  {
            Console.Error.writeLine("Geçersiz kullanım!...");
            System.exit(1);
        }
        try {
            day = args.length == 3 ? Integer.parseInt(args[0]) : Console.readInt("Gün bilgisini giriniz:", "Hatalı giriş yaptınız!...");
            month = args.length == 3 ? Integer.parseInt(args[1]) : Console.readInt("Ay bilgisini giriniz:", "Hatalı giriş yaptınız!...");
            year = args.length == 3 ? Integer.parseInt(args[2]) : Console.readInt("Yıl bilgisini giriniz:", "Hatalı giriş yaptınız!...");
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments!...");
        }

        printBirthDayMessage(new BirthDateOperations(day, month, year));
    }
}
