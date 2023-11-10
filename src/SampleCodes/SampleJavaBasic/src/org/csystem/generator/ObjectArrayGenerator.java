package org.csystem.generator;

import org.csystem.math.geometry.Point;
import org.csystem.util.array.ArrayUtil;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ObjectArrayGenerator {
    private final RandomGenerator m_randomGenerator = new Random();

    //String, Integer, Boolean, Double, Character, Point, int [], String []
    private Object createObject()
    {
        return switch (m_randomGenerator.nextInt(8)) {
            case 0 -> StringUtil.generateRandomTextTR(m_randomGenerator, m_randomGenerator.nextInt(5, 11));
            case 1 -> m_randomGenerator.nextInt(-128, 127);
            case 2 -> m_randomGenerator.nextBoolean();
            case 3 -> (double) m_randomGenerator.nextInt(-10, 10);
            case 4 -> (char)(m_randomGenerator.nextInt(26) + (m_randomGenerator.nextBoolean() ? 'A' : 'a'));
            case 5 -> Point.createCartesian(m_randomGenerator.nextDouble(-100, 100), m_randomGenerator.nextDouble(-100, 100));
            case 6 -> ArrayUtil.generateRandomArray(m_randomGenerator, m_randomGenerator.nextInt(5, 20), 0, 100);
            default -> StringUtil.generateRandomTextsTR(m_randomGenerator, m_randomGenerator.nextInt(5, 8), 5, 11);
        };
    }

    public Object [] createObjects(int count)
    {
        Object [] objects = new Object[count];

        for (int i = 0; i < count; ++i)
            objects[i] =  createObject();

        return objects;
    }

    public ArrayList<Object> createUntilZero()
    {
        ArrayList<Object> objects = new ArrayList<>();

        while (true) {
            Object o = createObject();

            if (o instanceof Integer val && val == 0)
                break;

            objects.add(o);
        }

        return objects;
    }
}
