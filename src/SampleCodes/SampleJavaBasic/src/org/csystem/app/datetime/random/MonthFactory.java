package org.csystem.app.datetime.random;


import org.csystem.datetime.Month;

import java.util.Random;

public class MonthFactory {
    private final Month[] m_months = Month.values();
    public Month create(int ordinal)
    {
        return m_months[ordinal];
    }

    public Month create(Random random)
    {
        return m_months[random.nextInt(m_months.length)];
    }

}
