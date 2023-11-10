package org.csystem.app.generator;

import org.csystem.generator.ObjectArrayGenerator;
import org.csystem.math.geometry.Point;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.string.StringUtil;

import java.util.Scanner;

public class ObjectArrayGeneratorDemoApp {
    //String, Integer, Boolean, Double, Character, Point, int [], String []
    private static void doForObjects(Object [] objects)
    {
        for (Object object : objects) {
            String typeStr = object.getClass().getName();

            System.out.println("-----------------------------------------------------------");
            System.out.printf("Type:%s%n", typeStr);

            if (object instanceof String str)
                System.out.printf("str:%s, upper:%s%n", str, str.toUpperCase());
            else if (object instanceof Integer) {
                int val = (int)object;

                System.out.printf("%d * %d = %d%n", val, val, val * val);
            }
            else if (object instanceof Double) {
                double val = (double)object;

                System.out.printf("%f + %f = %f%n", val, val, val + val);
            }
            else if (object instanceof Boolean) {
                boolean flag = (boolean)object;

                System.out.printf("flag = %b%n", flag);
            }

            else if (object instanceof Character) {
                char ch = (char)object;

                System.out.printf("ch = %c%n", ch);
            }
            else if (object instanceof Point point)
                System.out.printf("Distance to origin of %s is %f%n", point.toString(), point.distance());
            else if (object instanceof int [] a) {
                ArrayUtil.print(a, " ", " -> ");
                System.out.println(ArrayUtil.sum(a));
            }
            else if (object instanceof String [] str) {
                ArrayUtil.print(str);
                System.out.println(StringUtil.join(str, '-'));
            }

            System.out.println("-----------------------------------------------------------");
        }
    }

    public static void run()
    {
        ObjectArrayGenerator generator = new ObjectArrayGenerator();
        Scanner kb = new Scanner(System.in);

        System.out.print("Input count:");
        doForObjects(generator.createObjects(kb.nextInt()));
    }

    public static void main(String[] args)
    {
        run();
    }
}
