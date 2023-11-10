package org.csystem.wrapper.test;

import org.csystem.wrapper.LongValue;

import java.util.Scanner;

public class LongValueDivideAndRemainderTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bölüneni giriniz:");
            LongValue a = LongValue.of(Long.parseLong(kb.nextLine()));

            if (a.compareTo(LongValue.ZERO) == 0)
                break;

            System.out.print("Böleni giriniz:");
            LongValue b = LongValue.of(Integer.parseInt(kb.nextLine()));

            LongValue [] result = a.divideAndRemainder(b);

            System.out.printf("Bölüm:%s, Kalan:%s%n", result[0].toString(), result[1].toString());
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
