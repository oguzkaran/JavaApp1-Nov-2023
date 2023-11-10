package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

class StringUtilGetLastPangramENTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Input a text:");
            String s = kb.nextLine();

            if ("quit".equals(s))
                break;

            System.out.printf("Last pangram:%s%n", StringUtil.getLastPangramEN(s));
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}