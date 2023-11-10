package org.csystem.util.matrix.test;

import org.csystem.util.array.ArrayUtil;
import org.csystem.util.matrix.MatrixUtil;

import java.util.Random;
import java.util.Scanner;

public class MatrixUtilGenerateRandomSquareMatrixTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int n = r.nextInt(1, 11);

            int [][] m = MatrixUtil.generateRandomSquareMatrix(r, n, 0, 100);

            System.out.println("---------------------------------------------------");
            System.out.printf("n = %d%n", n);
            ArrayUtil.print(2, m);
            System.out.println("---------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
