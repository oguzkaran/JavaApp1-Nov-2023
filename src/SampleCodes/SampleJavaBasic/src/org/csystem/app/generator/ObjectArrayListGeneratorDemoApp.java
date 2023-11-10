package org.csystem.app.generator;

import org.csystem.generator.ObjectArrayGenerator;
import org.csystem.math.geometry.Point;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;

public class ObjectArrayListGeneratorDemoApp {
    //String, Integer, Boolean, Double, Character, Point, int [], String []
    private static void doForObjects(ArrayList<Object> objects)
    {
        Console.writeLine("Size = %d", objects.size());
        for (Object object : objects) {
            String typeStr = object.getClass().getName();

            Console.writeLine("-----------------------------------------------------------");
            Console.writeLine("Type:%s", typeStr);

            if (object instanceof String str)
                Console.writeLine("str:%s, upper:%s", str, str.toUpperCase());
            else if (object instanceof Integer) {
                int val = (int)object;

                Console.writeLine("%d * %d = %d", val, val, val * val);
            }
            else if (object instanceof Double) {
                double val = (double)object;

                Console.writeLine("%f + %f = %f", val, val, val + val);
            }
            else if (object instanceof Boolean) {
                boolean flag = (boolean)object;

                Console.writeLine("flag = %b", flag);
            }

            else if (object instanceof Character) {
                char ch = (char)object;

                Console.writeLine("ch = %c", ch);
            }
            else if (object instanceof Point point)
                Console.writeLine("Distance to origin of %s is %f", point.toString(), point.distance());
            else if (object instanceof int [] a) {
                ArrayUtil.print(a, " ", " -> ");
                Console.writeLine(ArrayUtil.sum(a));
            }
            else if (object instanceof String [] str) {
                ArrayUtil.print(str);
                Console.writeLine(StringUtil.join(str, '-'));
            }

            Console.writeLine("-----------------------------------------------------------");
        }
    }

    public static void run()
    {
        ObjectArrayGenerator generator = new ObjectArrayGenerator();
        doForObjects(generator.createUntilZero());
    }

    public static void main(String[] args)
    {
        run();
    }
}
