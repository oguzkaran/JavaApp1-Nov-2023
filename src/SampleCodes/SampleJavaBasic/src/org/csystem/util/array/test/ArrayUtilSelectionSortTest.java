package org.csystem.util.array.test;

import java.util.Random;
import java.util.Scanner;

import static org.csystem.util.array.ArrayUtil.*;

public class ArrayUtilSelectionSortTest {
    public static void run()
    {
        Random random = new Random();
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int [] a = generateRandomArray(random, random.nextInt(5, 21), 0, 100);
            boolean descending = random.nextBoolean();

            System.out.println("--------------------------------------------------------");
            print(2, a);
            selectionSort(a, descending);
            System.out.printf("%s order:%n", descending ? "Descending" : "Ascending");
            print(2, a);
            System.out.println("--------------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
