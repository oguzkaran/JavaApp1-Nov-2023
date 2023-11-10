package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

class StringUtilGetFirstPangramTRTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bir yazı giriniz:");
            String s = kb.nextLine();

            if ("elma".equals(s))
                break;

            System.out.printf("İlk pangram:%s%n", StringUtil.getFirstPangramTR(s));
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}