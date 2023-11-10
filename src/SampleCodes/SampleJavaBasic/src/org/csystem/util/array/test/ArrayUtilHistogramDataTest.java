package org.csystem.util.array.test;

import java.util.Random;
import java.util.Scanner;

import static org.csystem.util.array.ArrayUtil.*;

public class ArrayUtilHistogramDataTest {
    public static void run()
    {
        Random random = new Random();
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int [] a = generateRandomArray(random, random.nextInt(10, 100), 0, 11);

            System.out.println("--------------------------------------------------------");
            print(a);
            print(histogramData(a, 10));
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
