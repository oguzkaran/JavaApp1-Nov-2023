package org.csystem.wrapper.test;

import org.csystem.wrapper.IntValue;

import java.util.Scanner;

public class IntValueDivideAndRemainderTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bölüneni giriniz:");
            IntValue a = IntValue.of(Integer.parseInt(kb.nextLine()));

            if (a.compareTo(IntValue.ZERO) == 0)
                break;

            System.out.print("Böleni giriniz:");
            IntValue b = IntValue.of(Integer.parseInt(kb.nextLine()));

            IntValue [] result = a.divideAndRemainder(b);

            System.out.printf("Bölüm:%s, Kalan:%s%n", result[0].toString(), result[1].toString());
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
