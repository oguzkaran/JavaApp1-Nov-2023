/*-------------------------------------------------------------
	FILE		: Circle.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 20th April 2024

	Circle class that represents a circle

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math.geometry;

import java.util.Objects;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class Circle {
    protected static final double DELTA = 0.00001;

    private double m_radius;

    public Circle()
    {
    }

    public Circle(double radius)
    {
        setRadius(radius);
    }

    public double getRadius()
    {
        return m_radius;
    }

    public void setRadius(double radius)
    {
        if (radius < 0)
            throw new IllegalArgumentException("Radius can not be negative");

        m_radius  = radius;
    }

    public double getArea()
    {
        return PI * m_radius * m_radius;
    }

    public double getCircumference()
    {
        return 2 * PI * m_radius;
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof Circle c && abs(c.m_radius - m_radius) < DELTA;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(m_radius);
    }

    @Override
    public String toString()
    {
        return String.format("Radius:%f, Area:%f, Circumference:%f", m_radius, getArea(), getCircumference());
    }
}
