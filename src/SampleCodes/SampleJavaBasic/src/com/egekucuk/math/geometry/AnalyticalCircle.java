/*-------------------------------------------------------------
	FILE		: AnalyticalCircle.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 22nd Oct 2023

	AnalyticalCircle class that represents a circle
	in cartesian plane

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package com.egekucuk.math.geometry;

import org.csystem.math.geometry.Circle;
import org.csystem.math.geometry.MutablePoint;

public class AnalyticalCircle extends Circle {
    private static final double DELTA = 0.000001;
    private final MutablePoint m_center;

    public AnalyticalCircle()
    {
        this(0);
    }

    public AnalyticalCircle(double radius)
    {
        this(radius, 0, 0);
    }

    public AnalyticalCircle(double x, double y)
    {
        this(0, x, y);
    }

    public AnalyticalCircle(double radius, double x, double y)
    {
        super(radius);
        m_center = MutablePoint.createCartesian(x, y);
    }

    public double getCenterX()
    {
        return m_center.getX();
    }

    public double getCenterY()
    {
        return m_center.getY();
    }

    public void setCenterX(double x)
    {
        m_center.setX(x);
    }

    public void setCenterY(double y)
    {
        m_center.setY(y);
    }

    public void setCenter(double x, double y)
    {
        setCenterX(x);
        setCenterY(y);
    }

    public void offset(double dx, double dy)
    {
        m_center.offset(dx, dy);
    }

    public void offset(double dxy)
    {
        offset(dxy, dxy);
    }

    public double centerDistance(AnalyticalCircle other)
    {
        return m_center.distance(other.m_center);
    }

    public boolean isTangent(AnalyticalCircle other)
    {
        return Math.abs(centerDistance(other) - getRadius() - other.getRadius()) < DELTA;
    }

    public boolean equals(Object other)
    {
        return other instanceof AnalyticalCircle ac && super.equals(ac) && m_center.equals(ac.m_center);
    }

    public String toString()
    {
        return String.format("%s, Center:%s", super.toString(), m_center);
    }
}
