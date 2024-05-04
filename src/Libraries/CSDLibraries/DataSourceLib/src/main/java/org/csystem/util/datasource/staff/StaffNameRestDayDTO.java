package org.csystem.util.datasource.staff;

import java.time.DayOfWeek;
import java.util.Objects;

public final class StaffNameRestDayDTO {
    public final String name;
    public final DayOfWeek restDay;

    public StaffNameRestDayDTO(String name, DayOfWeek restDay)
    {
        this.name = name;
        this.restDay = restDay;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, restDay);
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof StaffNameRestDayDTO s && s.name.equals(name) && s.restDay == restDay;
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", name, restDay);
    }
}
