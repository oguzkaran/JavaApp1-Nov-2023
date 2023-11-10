/*-------------------------------------------------------------
	FILE		: MutableIntValue.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 22nd Oct 2023

	MutableIntValue class that wraps an int value

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.wrapper;

public final class MutableIntValue {
    private int m_value;

    private MutableIntValue(int value)
    {
        m_value = value;
    }

    public static MutableIntValue of(int value)
    {
        return new MutableIntValue(value);
    }

    public int getValue()
    {
        return m_value;
    }

    public MutableIntValue add(MutableIntValue other)
    {
        add(other.m_value);

        return this;
    }

    public MutableIntValue add(int value)
    {
        m_value += value;

        return this;
    }

    public MutableIntValue subtract(MutableIntValue other)
    {
        subtract(other.m_value);

        return this;
    }

    public MutableIntValue subtract(int value)
    {
        add(-value);

        return this;
    }

    public MutableIntValue multiply(MutableIntValue other)
    {
        multiply(other.m_value);

        return this;
    }

    public MutableIntValue multiply(int value)
    {
        m_value *= value;

        return this;
    }

    public MutableIntValue divide(MutableIntValue other)
    {
        divide(other.m_value);

        return this;
    }

    public MutableIntValue divide(int value)
    {
        m_value /= value;

        return this;
    }

    public int compareTo(MutableIntValue other)
    {
        return m_value - other.m_value;
    }

    public boolean equals(Object other)
    {
        return other instanceof MutableIntValue val && val.m_value == m_value;
    }

    public String toString()
    {
        return String.valueOf(m_value);
    }
}
