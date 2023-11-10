package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

public class StringUtilSplitTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bir yazı giriniz:");
            String s = kb.nextLine();

            if ("elma".equals(s))
                break;

            String [] str = StringUtil.split(s, " ,.[,]", true);

            for (int i = 0; i < str.length; ++i)
                System.out.println(str[i]);
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
