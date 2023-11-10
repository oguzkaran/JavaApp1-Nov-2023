package org.csystem.wrapper.test;

import org.csystem.wrapper.LongValue;

import java.util.Scanner;

public class LongValueOperationTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        LongValue n = LongValue.of(kb.nextInt());
        LongValue total = LongValue.ZERO;

        for (LongValue i = LongValue.ONE; i.compareTo(n) <= 0; i = i.inc())
            total = total.add(i);

        System.out.printf("Total:%s%n", total.toString());
    }

    public static void main(String[] args)
    {
        run();
    }
}
