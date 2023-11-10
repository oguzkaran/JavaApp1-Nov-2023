package org.csystem.util.array.test;

import java.util.Random;
import java.util.Scanner;

import static org.csystem.util.array.ArrayUtil.*;

public class ArrayUtilSumTest {
    public static void run()
    {
        Random random = new Random();
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int [] a = generateRandomArray(random, random.nextInt(3, 8), 0, 100);

            System.out.println("--------------------------------------------------------");
            print(2, a);
            System.out.printf("Toplam:%d%n", sum(a));
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
