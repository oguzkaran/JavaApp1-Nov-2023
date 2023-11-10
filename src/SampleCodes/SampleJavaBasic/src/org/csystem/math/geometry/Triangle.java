/*-------------------------------------------------------------
	FILE		: Triangle.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 10th Sep 2023

	Triangle class that represents a triangle

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math.geometry;

public class Triangle {
    private double m_a, m_b, m_c;
    private double m_area;

    //...

    public double getSide1()
    {
        return m_a;
    }

    public void setSide1(double a)
    {
        //...
        m_a = a;
    }

    //...

    public double getArea()
    {
        return m_area;
    }
}
