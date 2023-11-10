package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

class StringUtilWrapWithTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bir yazı giriniz:");
            String text = kb.nextLine();

            if ("elma".equals(text))
                break;

            text = StringUtil.wrapWith(text, '(', ')');

            System.out.println(text);
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
