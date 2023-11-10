/*-------------------------------------------------------------
	FILE		: Point.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 14th Oct 2023
	
	Immutable Point class that represents a point in cartesian
	plane
	
	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math.geometry;

import static java.lang.Math.*;

public final class Point {
	private final double m_x;
	private final double m_y;

	Point(double x, double y)
	{
		m_x = x;
		m_y = y;
	}

	private Point(double a, double b, boolean polar)
	{
		if (polar) {
			m_x = a * cos(b);
			m_y = a * sin(b);
		}
		else {
			m_x = a;
			m_y = b;
		}
	}

	public static Point createCartesian(double x, double y)
	{
		return new Point(x, y, false);
	}

	public static Point createPolar(double radius, double theta)
	{
		return new Point(radius, theta, true);
	}

	public double getX()
	{
		return m_x;
	}
	public double getY()
	{
		return m_y;
	}

	public double distance()
	{
		return distance(0, 0);
	}
	
	public double distance(Point other)
	{
		return distance(other.m_x, other.m_y);
	}
	
	public double distance(double x, double y)
	{
		return PointCommon.distance(m_x, m_y, x, y);
	}

	public MutablePoint toMutablePoint()
	{
		return new MutablePoint(m_x, m_y);
	}

	public boolean equals(Object other)
	{
		return other instanceof Point p && PointCommon.equals(m_x, m_y, p.m_x, p.m_y);
	}

	public String toString()
	{
		return PointCommon.toString(m_x, m_y);
	}
}
