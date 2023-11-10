package org.csystem.app.datetime.random;


import org.csystem.datetime.DayOfWeek;

import java.util.Random;

public class DayOfWeekFactory {
    private final DayOfWeek[] m_dayOfWeeks = DayOfWeek.values();
    public DayOfWeek create(int ordinal)
    {
        return m_dayOfWeeks[ordinal];
    }

    public DayOfWeek create(Random random)
    {
        return m_dayOfWeeks[random.nextInt(m_dayOfWeeks.length)];
    }

}
