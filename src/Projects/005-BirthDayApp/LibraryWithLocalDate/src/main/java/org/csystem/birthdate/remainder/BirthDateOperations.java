package org.csystem.birthdate.remainder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BirthDateOperations {
    private final LocalDate m_birthDate;
    private final LocalDate m_today;

    public BirthDateOperations(int day, int month, int year)
    {
        m_birthDate = LocalDate.of(year, month, day);
        m_today = LocalDate.now();
    }

    public double getAge()
    {
        return ChronoUnit.DAYS.between(m_birthDate, m_today) / 365.;
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
