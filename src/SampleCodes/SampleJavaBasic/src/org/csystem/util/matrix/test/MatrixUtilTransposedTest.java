package org.csystem.util.matrix.test;

import org.csystem.util.array.ArrayUtil;
import org.csystem.util.matrix.MatrixUtil;

import java.util.Random;
import java.util.Scanner;

public class MatrixUtilTransposedTest {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Bir sayı giriniz:");
        int count = kb.nextInt();

        while (count-- > 0) {
            int row = r.nextInt(1, 11);
            int col = r.nextInt(1, 11);
            int [][] m = MatrixUtil.generateRandomMatrix(r, row, col, 0, 100);

            System.out.println("###################################################");
            ArrayUtil.print(2, m);
            System.out.println("---------------------------------------------------");
            ArrayUtil.print(2, MatrixUtil.transposed(m));
            System.out.println("###################################################");
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
