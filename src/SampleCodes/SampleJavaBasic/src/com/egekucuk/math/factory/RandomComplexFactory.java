package com.egekucuk.math.factory;

import org.csystem.math.Complex;

import java.util.ArrayList;
import java.util.Random;

public class RandomComplexFactory {
    private final Random m_random;

    public RandomComplexFactory(Random random)
    {
        m_random = random;
    }

    public ArrayList createComplexListUntil(Complex z, int min, int bound)
    {
        ArrayList list = new ArrayList();

        while (true) {
            Complex c = new Complex(m_random.nextInt(min, bound), m_random.nextInt(min, bound));

            list.add(c);

            if (z.equals(c))
                break;
        }

        return list;
    }
}
