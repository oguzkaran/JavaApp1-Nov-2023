package com.egekucuk.math.geometry.test;

import com.egekucuk.math.geometry.AnalyticalCircle;

import java.util.Random;

public class AnalyticalCircleSetCenterTest {
    public static void run()
    {
        Random random = new Random();

        AnalyticalCircle ac = new AnalyticalCircle();

        System.out.println("---------------------------------------------------------");
        System.out.printf("Radius:%f, x = %f, y = %f%n", ac.getRadius(), ac.getCenterX(), ac.getCenterY());
        System.out.println("---------------------------------------------------------");

        while (true) {
            double radius = random.nextInt(-10, 10);
            double x = random.nextDouble(-10, 10);
            double y = random.nextDouble(-10, 10);

            ac.setRadius(radius);
            ac.setCenter(x, y);

            System.out.println("---------------------------------------------------------");
            System.out.printf("Radius:%f, x = %f, y = %f%n", radius, x, y);
            System.out.printf("Radius:%f, x = %f, y = %f%n", ac.getRadius(), ac.getCenterX(), ac.getCenterY());
            System.out.println("---------------------------------------------------------");

            if (radius == 0)
                break;
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
