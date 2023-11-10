package org.csystem.util.numeric.test;

import java.util.Random;
import java.util.Scanner;

import static org.csystem.util.numeric.NumberUtil.numberToTextTR;

public class NumberUtilNumberToTextTRTest {
    public static void run()
    {
        Random random = new Random();
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int val = random.nextInt(-999, 1000);

            System.out.println("--------------------------------------------------------");
            System.out.printf("%d -> %s%n", val, numberToTextTR(val));
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
