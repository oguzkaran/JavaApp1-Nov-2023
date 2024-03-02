/*-------------------------------------------------------------
	FILE		: IntRangeLong.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 2nd Mar 2024

	IntToLongRange class can be used for values in an interval

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.LongUnaryOperator;

public class IntToLongRange implements Iterable<Long> {
    private final int m_a;
    private final int m_b;
    private final LongUnaryOperator m_unaryOperator;

    private IntToLongRange(int a, int b, LongUnaryOperator unaryOperator)
    {
        m_a = a;
        m_b = b;
        m_unaryOperator = unaryOperator;
    }

    public static IntToLongRange of(int a, int b)
    {
        return of(a, b, 1);
    }

    public static IntToLongRange of(int a, int b, int step)
    {
        if (step <= 0)
            throw new IllegalArgumentException(String.format("Step must be positive:%d", step));

        return of(a, b, val -> val + step);
    }

    public static IntToLongRange of(int a, int b, LongUnaryOperator unaryOperator)
    {
        if (a > b)
            throw new IllegalArgumentException(String.format("a can not be greater than b:a = %d, b= %d", a, b));

        return new IntToLongRange(a, b, unaryOperator);
    }

    @Override
    public Iterator<Long> iterator()
    {
        return new Iterator<>() {
            long value = m_a;

            @Override
            public boolean hasNext()
            {
                return value <= m_b;
            }

            @Override
            public Long next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No such element!...");

                var result = value;

                value = m_unaryOperator.applyAsLong(value);

                return result;
            }
        };
    }
}
