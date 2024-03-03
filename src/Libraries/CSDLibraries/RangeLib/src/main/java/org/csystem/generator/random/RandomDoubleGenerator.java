/*-------------------------------------------------------------
	FILE		: RandomDoubleGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	RandomDoubleGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.random;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.random.RandomGenerator;

public class RandomDoubleGenerator implements Iterable<Double> {
    private final RandomGenerator m_randomGenerator;
    private final double m_origin;
    private final double m_bound;
    private final double m_count;
    private static class RandomDoubleGeneratorIterator implements Iterator<Double> {
        final RandomDoubleGenerator doubleGenerator;
        double count;

        public RandomDoubleGeneratorIterator(RandomDoubleGenerator doubleGenerator)
        {
            this.doubleGenerator = doubleGenerator;
        }

        @Override
        public boolean hasNext()
        {
            return count < doubleGenerator.m_count;
        }

        @Override
        public Double next()
        {
            if (!hasNext())
                throw new NoSuchElementException("Can not generate a value!...");

            ++count;

            return doubleGenerator.m_randomGenerator.nextDouble(doubleGenerator.m_origin, doubleGenerator.m_bound);
        }
    }

    private RandomDoubleGenerator(RandomGenerator randomGenerator, double origin, double bound, double count)
    {
        m_randomGenerator = randomGenerator;
        m_origin = origin;
        m_bound = bound;
        m_count = count;
    }

    public static RandomDoubleGenerator of(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        return new RandomDoubleGenerator(randomGenerator, origin, bound, count);
    }

    @Override
    public Iterator<Double> iterator()
    {
        return new RandomDoubleGeneratorIterator(this);
    }
}
