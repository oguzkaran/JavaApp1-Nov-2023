/*-------------------------------------------------------------
	FILE		: DoubleRange.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 2nd Mar 2024

	DoubleRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.range;

import java.util.Iterator;
import java.util.function.DoubleUnaryOperator;

public class DoubleRange implements Iterable<Double> {
    public static DoubleRange of(double a, double b, double delta)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }


    public static DoubleRange of(double a, double b, DoubleUnaryOperator unaryOperator)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Iterator<Double> iterator()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
