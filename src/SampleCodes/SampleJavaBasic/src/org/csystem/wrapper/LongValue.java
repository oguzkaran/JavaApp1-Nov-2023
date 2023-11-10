/*-------------------------------------------------------------
	FILE		: LongValue.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 22nd Oct 2023

	Immutable LongValue class that wraps a long value by
	using cache for [-128, 127]

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.wrapper;

public final class LongValue {
    private static final long CACHE_MIN_VALUE = -128;
    private static final long CACHE_MAX_VALUE = 127;
    private static final LongValue[] CACHE = new LongValue[(int)(CACHE_MAX_VALUE - CACHE_MIN_VALUE + 1)];
    private final long m_value;

    public static final LongValue ZERO = of(0);
    public static final LongValue ONE = of(1);

    public static final LongValue TEN = of(10);

    private LongValue(long value)
    {
        m_value = value;
    }

    public static LongValue of(long value)
    {
        if (value < CACHE_MIN_VALUE || value > CACHE_MAX_VALUE)
            return new LongValue(value);

        int idx = (int)(value - CACHE_MIN_VALUE);

        if (CACHE[idx] == null)
            CACHE[idx] = new LongValue(value);

        return CACHE[idx];
    }

    public long getValue()
    {
        return m_value;
    }

    public LongValue add(LongValue other)
    {
        return add(other.m_value);
    }

    public LongValue add(long value)
    {
        return of(m_value + value);
    }

    public LongValue subtract(LongValue other)
    {
        return subtract(other.m_value);
    }

    public LongValue subtract(long value)
    {
        return add(-value);
    }

    public LongValue multiply(LongValue other)
    {
        return multiply(other.m_value);
    }

    public LongValue multiply(long value)
    {
        return of(m_value * value);
    }

    public LongValue divide(LongValue other)
    {
        return divide(other.m_value);
    }

    public LongValue divide(long value)
    {
        return of(m_value / value);
    }

    public LongValue[] divideAndRemainder(LongValue other)
    {
        return divideAndRemainder(other.m_value);
    }

    public LongValue[] divideAndRemainder(long value)
    {
        LongValue[] result = new LongValue[2];

        result[0] = divide(value);
        result[1] = LongValue.of(m_value % value);

        return result;
    }

    public LongValue inc()
    {
        return add(1);
    }

    public LongValue dec()
    {
        return subtract(1);
    }

    public int compareTo(LongValue other)
    {
        if (m_value < other.m_value)
            return -1;

        if (m_value == other.m_value)
            return 0;

        return 1;
    }

    public boolean equals(Object other)
    {
        return other instanceof LongValue val && val.m_value == m_value;
    }

    public String toString()
    {
        return String.valueOf(m_value);
    }
}
