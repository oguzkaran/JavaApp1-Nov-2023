package org.csystem.util.array.test;

import org.csystem.util.array.ArrayUtil;
import org.csystem.util.matrix.MatrixUtil;

import java.util.Random;
import java.util.Scanner;

public class ArrayUtilAddByTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int row = r.nextInt(1, 11);
            int col = r.nextInt(1, 11);
            int [][] a = MatrixUtil.generateRandomMatrix(r, row, col, 0, 100);

            System.out.println("---------------------------------------------------");
            ArrayUtil.print(2, a);
            int val = r.nextInt(5, 15);

            System.out.printf("val = %d%n", val);
            ArrayUtil.addBy(a, val);
            ArrayUtil.print(3, a);
            System.out.println("---------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
