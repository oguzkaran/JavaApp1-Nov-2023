package org.csystem.wrapper.test;

import org.csystem.wrapper.IntValue;

import java.util.Scanner;

public class IntValueOperationTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        IntValue n = IntValue.of(kb.nextInt());
        IntValue total = IntValue.ZERO;

        for (IntValue i = IntValue.ONE; i.compareTo(n) <= 0; i = i.inc())
            total = total.add(i);

        System.out.printf("Total:%s%n", total.toString());
    }

    public static void main(String[] args)
    {
        run();
    }
}
