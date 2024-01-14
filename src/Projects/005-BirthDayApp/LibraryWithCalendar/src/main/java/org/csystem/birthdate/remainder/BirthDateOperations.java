package org.csystem.birthdate.remainder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

public class BirthDateOperations {
    private static final double DIVIDER = 1000. * 60 * 60 * 24 * 365;
    private final Calendar m_birthDate;
    private final Calendar m_today;

    public BirthDateOperations(int day, int month, int year)
    {
        m_birthDate = new GregorianCalendar(year, month - 1, day);
        var today = new GregorianCalendar();
        m_today = new GregorianCalendar(today.get(YEAR), today.get(MONTH), today.get(DAY_OF_MONTH));
    }

    public double getAge()
    {
        return (m_today.getTimeInMillis() - m_birthDate.getTimeInMillis()) / DIVIDER;
    }

    public BirthDayStatus getStatus()
    {
        var birthDay = new GregorianCalendar(m_today.get(YEAR), m_birthDate.get(MONTH), m_birthDate.get(DAY_OF_MONTH));
        int status = m_today.compareTo(birthDay);

        BirthDayStatus result;

        if (status > 0)
            result = BirthDayStatus.BEFORE;
        else if (status < 0)
            result = BirthDayStatus.AFTER;
        else
            result = BirthDayStatus.TODAY;

        return result;
    }
}
