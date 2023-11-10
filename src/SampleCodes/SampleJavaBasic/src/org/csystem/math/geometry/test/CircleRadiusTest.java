package org.csystem.math.geometry.test;

import org.csystem.math.geometry.Circle;
import org.csystem.util.console.Console;

import java.util.Random;

public class CircleRadiusTest {
    public static void run()
    {
        Random random = new Random();

        while (true) {
            try {
                double radius = random.nextDouble(-5.56, 6);
                Circle c = new Circle(radius);

                Console.writeLine("Generated radius:%f, Circle:%s", radius, c.toString());

                if (Math.abs(radius) <= 0.001)
                    break;
            }
            catch (IllegalArgumentException ex) {
                Console.writeLine("Message:%s", ex.getMessage());
            }
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
