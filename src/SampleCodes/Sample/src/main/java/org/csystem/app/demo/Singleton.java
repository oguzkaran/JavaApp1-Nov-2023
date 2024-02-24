package org.csystem.app.demo;

public class Singleton {
    private static Singleton ms_instance;
    private int m_value;

    private Singleton(int value)
    {
        m_value = value;
    }

    public static Singleton getInstance(int value)
    {
        if (ms_instance == null)
            ms_instance = new Singleton(value);

        return ms_instance;
    }

    public int getValue()
    {
        return m_value;
    }

    public void setValue(int value)
    {
        m_value = value;
    }
}
