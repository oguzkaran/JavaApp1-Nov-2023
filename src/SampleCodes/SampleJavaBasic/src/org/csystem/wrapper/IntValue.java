/*-------------------------------------------------------------
	FILE		: IntValue.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 22nd Oct 2023

	Immutable IntValue class that wraps an int value by
	using cache for [-128, 127]

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.wrapper;

import javax.print.attribute.standard.MediaSize;

public final class IntValue {
    private static final int CACHE_MIN_VALUE = -128;
    private static final int CACHE_MAX_VALUE = 127;
    private static final IntValue[] CACHE = new IntValue[CACHE_MAX_VALUE - CACHE_MIN_VALUE + 1];
    private final int m_value;

    public static final IntValue ZERO = of(0);
    public static final IntValue ONE = of(1);

    public static final IntValue TEN = of(10);

    private IntValue(int value)
    {
        m_value = value;
    }

    public static IntValue of(int value)
    {
        if (value < CACHE_MIN_VALUE || value > CACHE_MAX_VALUE)
            return new IntValue(value);

        if (CACHE[value - CACHE_MIN_VALUE] == null)
            CACHE[value - CACHE_MIN_VALUE] = new IntValue(value);

        return CACHE[value - CACHE_MIN_VALUE];
    }

    public int getValue()
    {
        return m_value;
    }

    public IntValue add(IntValue other)
    {
        return add(other.m_value);
    }

    public IntValue add(int value)
    {
        return of(m_value + value);
    }

    public IntValue subtract(IntValue other)
    {
        return subtract(other.m_value);
    }

    public IntValue subtract(int value)
    {
        return add(-value);
    }

    public IntValue multiply(IntValue other)
    {
        return multiply(other.m_value);
    }

    public IntValue multiply(int value)
    {
        return of(m_value * value);
    }

    public IntValue divide(IntValue other)
    {
        return divide(other.m_value);
    }

    public IntValue divide(int value)
    {
        return of(m_value / value);
    }

    public IntValue [] divideAndRemainder(IntValue other)
    {
        return divideAndRemainder(other.m_value);
    }

    public IntValue [] divideAndRemainder(int value)
    {
        IntValue [] result = new IntValue[2];

        result[0] = divide(value);
        result[1] = IntValue.of(m_value % value);

        return result;
    }

    public IntValue inc()
    {
        return add(1);
    }

    public IntValue dec()
    {
        return subtract(1);
    }

    public int compareTo(IntValue other)
    {
        return m_value - other.m_value;
    }

    public boolean equals(Object other)
    {
        return other instanceof IntValue i && i.m_value == m_value;
    }

    public String toString()
    {
        return String.valueOf(m_value);
    }
}
