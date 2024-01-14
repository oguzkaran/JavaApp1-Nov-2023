package org.csystem.birthdate.remainder;

import org.csystem.util.datetime.legacy.DateTime;

public class BirthDateOperations {
    private static final double DIVIDER = 1000. * 60 * 60 * 24 * 365;
    private final DateTime m_birthDate;
    private final DateTime m_today;

    public BirthDateOperations(int day, int month, int year)
    {
        m_birthDate = DateTime.of(day, month, year);
        m_today = DateTime.today();
    }

    public double getAge()
    {
        return (m_today.getTimeInMillis() - m_birthDate.getTimeInMillis()) / DIVIDER;
    }

    public BirthDayStatus getStatus()
    {
        var birthDay =  m_birthDate.withYear(m_today.getYear());
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
