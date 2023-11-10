package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

public class StringUtilSqueezeTest {
    public static void run()
    {
        Scanner kb = new java.util.Scanner(System.in);

        while (true) {
            System.out.print("Birinci yazıyı giriniz:");
            String s1 = kb.nextLine();

            if ("elma".equals(s1))
                break;

            System.out.print("İkinci yazıyı giriniz:");
            String s2 = kb.nextLine();

            System.out.println(StringUtil.squeeze(s1, s2));
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}