package org.csystem.app;

import com.karandev.io.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        int a = Console.readInt("Birinci sayıyı giriniz:", "Hatalı giriş yaptınız!...");
        int b = Console.readInt("İkinci sayıyı giriniz:", "Hatalı giriş yaptınız!...");

        Console.writeLine("%d + %d = %d", a, b, a + b);
    }
}
