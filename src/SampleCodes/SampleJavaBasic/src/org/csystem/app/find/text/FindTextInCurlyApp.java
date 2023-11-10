package org.csystem.app.find.text;

import org.csystem.find.text.FindTextInCurly;

import java.util.Scanner;

public class FindTextInCurlyApp {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bir yazı giriniz:");
            String text = kb.nextLine();

            if ("elma".equals(text))
                break;

            FindTextInCurly findTextInCurly = new FindTextInCurly(text);

            findTextInCurly.parse();

            System.out.printf("%s%n", findTextInCurly.isValid() ? findTextInCurly.getResult() : "yanlış bir giriş");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}