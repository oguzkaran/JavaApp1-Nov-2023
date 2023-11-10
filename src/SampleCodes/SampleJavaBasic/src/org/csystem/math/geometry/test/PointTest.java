package org.csystem.math.geometry.test;

import org.csystem.math.geometry.Point;

import java.util.Random;

public class PointTest {
    public static void run()
    {
        Random r = new Random();

        while (true) {
            int a = r.nextInt(-100, 101);
            int b = r.nextInt(-100, 101);
            boolean polar = r.nextBoolean();
            System.out.println("-------------------------------------------");
            Point p = polar ? Point.createPolar(a, b) : Point.createCartesian(a, b);

            System.out.printf("a = %d, b = %d, polar = %b -> %s%n", a, b, polar, p.toString());

            System.out.println("-------------------------------------------");

            if (a == 0)
                break;
         }
    }
    public static void main(String[] args)
    {
        run();
    }
}
