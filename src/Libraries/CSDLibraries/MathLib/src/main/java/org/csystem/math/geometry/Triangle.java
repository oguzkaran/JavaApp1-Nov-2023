/*-------------------------------------------------------------
	FILE		: Triangle.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 24th March 2024

	Triangle class that represents a triangle

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math.geometry;
public class Triangle {
    public Triangle(double a)
    {
        throw new UnsupportedOperationException("TODO:");
    }

    /**
     * Creates a triangle object by checking validity with the conditions:
     * a + b &gt; c AND b + c &gt; a AND a + c &gt; b AND abs(a - b) &lt; c AND abs(a - c) &lt; b AND abs(b - c) &lt; a
     * @throws IllegalArgumentException if the triangle is not valid
     */
    public Triangle(double a, double b, double c)
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public double getA()
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public void setA(double a)
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public double getB()
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public void setB(double b)
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public double getC()
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public void setC(double c)
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public double getCircumference()
    {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * If u is half of circumference the area is calculated by the formula sqrt(u * (u - a) * (u - b) * (u - c))
      * @return area of the triangle
     */
    public double getArea()
    {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * That is calculated via area = a * ha / 2
     * @return height belongs to a side
     */
    public double getHeightOfA()
    {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * That is calculated via area = b * hb / 2
     * @return height belongs to a side
     */
    public double getHeightOfB()
    {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * That is calculated via area = c * hc / 2
     * @return height belongs to a side
     */
    public double getHeightOfC()
    {
        throw new UnsupportedOperationException("TODO");
    }

    //...

    @Override
    public boolean equals(Object obj)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("TODO:");
    }
}
