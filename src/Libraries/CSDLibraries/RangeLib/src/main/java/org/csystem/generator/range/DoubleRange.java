/*-------------------------------------------------------------
	FILE		: DoubleRange.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	DoubleRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.range;

import org.csystem.generator.Generator;

import java.util.function.UnaryOperator;

public class DoubleRange extends Generator<Double> {
    private final double m_a;
    private final double m_b;

    private DoubleRange(double a, double b, UnaryOperator<Double> unaryOperator)
    {
        m_a = a;
        m_b = b;
        this.unaryOperator = unaryOperator;
        predicate = val -> val <= m_b;
        initSupplier = () -> m_a;
        noSuchElementExceptionMessage = "No such element!...";
    }

    public static DoubleRange of(double a, double b, double delta)
    {
        if (delta <= 0)
            throw new IllegalArgumentException(String.format("Delta must be positive:%.20f", delta));

        return of(a, b, val -> val + delta);
    }

    public static DoubleRange of(double a, double b, UnaryOperator<Double> unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %f, b= %f", a, b));

        return new DoubleRange(a, b, unaryOperator);
    }

}
