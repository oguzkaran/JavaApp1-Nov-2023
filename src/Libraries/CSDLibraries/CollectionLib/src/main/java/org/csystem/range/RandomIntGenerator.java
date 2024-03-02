/*-------------------------------------------------------------
	FILE		: RandomIntGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 2nd Mar 2024

	RandomIntGenerator class can be used for generate
	random values

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.range;

import java.util.Iterator;
import java.util.random.RandomGenerator;

public class RandomIntGenerator implements Iterable<Integer> {
    public static RandomIntGenerator of(RandomGenerator randomGenerator, int origin, int bound, int count)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Iterator<Integer> iterator()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
