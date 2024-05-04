package org.csystem.util.datasource.staff;

import java.util.Objects;

public final class StaffNameAgeDTO {
    public final String name;
    public final double age;

    public StaffNameAgeDTO(String name, double age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof StaffNameAgeDTO s && s.name.equals(name) && s.age == age;
    }

    @Override
    public String toString()
    {
        return String.format("%s - %.2f", name, age);
    }
}
