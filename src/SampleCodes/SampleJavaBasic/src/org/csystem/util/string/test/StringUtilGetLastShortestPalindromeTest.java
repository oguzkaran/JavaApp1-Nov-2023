package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

public class StringUtilGetLastShortestPalindromeTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bir yazı giriniz:");
            String s = kb.nextLine();

            if ("elma".equals(s))
                break;

            System.out.printf("Son en kısa palindrom:%s%n", StringUtil.getLastShortestPalindrome(s));
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}