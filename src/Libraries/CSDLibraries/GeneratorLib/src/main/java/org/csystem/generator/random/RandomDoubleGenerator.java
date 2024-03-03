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

import org.csystem.generator.NValueGenerator;

import java.util.random.RandomGenerator;

public class RandomDoubleGenerator extends NValueGenerator<Double> {
    private RandomDoubleGenerator(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        super.valueSupplier = () -> randomGenerator.nextDouble(origin, bound);
        super.noSuchElementExceptionMessage = "Can not generate a value!...";
        super.count = count;
    }

    public static RandomDoubleGenerator of(RandomGenerator randomGenerator, double origin, double bound, int count)
    {
        return new RandomDoubleGenerator(randomGenerator, origin, bound, count);
    }

}
