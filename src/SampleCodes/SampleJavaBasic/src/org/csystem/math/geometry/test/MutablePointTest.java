package org.csystem.math.geometry.test;

import org.csystem.math.geometry.MutablePoint;

import java.util.Random;

public class MutablePointTest {
    public static void run()
    {
        Random r = new Random();

        while (true) {
            int a = r.nextInt(-100, 101);
            int b = r.nextInt(-100, 101);
            boolean polar = r.nextBoolean();
            System.out.println("-------------------------------------------");
            MutablePoint p = polar ? MutablePoint.createPolar(a, b) : MutablePoint.createCartesian(a, b);

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
