/*-------------------------------------------------------------
	FILE		: RandomIntGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 23rd Mar 2024

	RandomIntGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.random;

import org.csystem.generator.NValueGenerator;

import java.util.random.RandomGenerator;

public class RandomIntGenerator extends NValueGenerator<Integer> {
    private RandomIntGenerator(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        super.valueSupplier = () -> randomGenerator.nextInt(origin, bound);
        super.noSuchElementExceptionMessage = "Can not generate a value!...";
        super.count = count;
    }

    public static RandomIntGenerator of(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        return new RandomIntGenerator(randomGenerator, origin, bound, count);
    }
}
