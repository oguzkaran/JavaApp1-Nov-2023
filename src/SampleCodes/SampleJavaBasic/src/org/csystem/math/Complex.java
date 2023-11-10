/*----------------------------------------------------------------------------------------------------------------------
	Sınıf Çalışması: Aşağıdaki Complex sınıfını immutable olarak değiştirip, MutableComplax sınıfını da yazınız
----------------------------------------------------------------------------------------------------------------------*/

/*-------------------------------------------------------------
	FILE		: Complex.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 22nd Oct 2023
	
	Complex class that represents a complex number
	
	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Complex {
	private static final double DELTA = 0.000001;

	private double m_real;
	private double m_imag;
	
	private static Complex add(double a1, double b1, double a2, double b2)
	{
		return new Complex(a1 + a2, b1 + b2);
	}
	
	private static Complex subtract(double a1, double b1, double a2, double b2)
	{
		return add(a1, b1, -a2, -b2);
	}
	
	private static Complex multiply(double a1, double b1, double a2, double b2)
	{
		return new Complex(a1 * a2 - b1 * b2, a1 * b2 + a2 * b1);
	}
	
	public Complex()
	{
	}


	public Complex(double re)
	{
		m_real = re;
	}
	
	public Complex(double re, double im)
	{
		m_real = re;
		m_imag = im;
	}

	public double getReal()
	{
		return m_real;
	}

	public void setReal(double real)
	{
		m_real = real;
	}

	public double getImag()
	{
		return m_imag;
	}

	public void setImag(double imag)
	{
		m_imag = imag;
	}

	public double getLength()
	{
		return sqrt(m_real * m_real + m_imag * m_imag);
	}
	
	public double getNorm()
	{
		return getLength();
	}
	
	public Complex getConjugate()
	{
		return new Complex(m_real, -m_imag);
	}
	
	public static Complex add(double val, Complex z)
	{
		return add(val, 0, z.m_real, z.m_imag);
	}
	
	public Complex add(double val)
	{
		return add(m_real, m_imag, val, 0);
	}
	
	public Complex add(Complex other)
	{
		return add(m_real, m_imag, other.m_real, other.m_imag);
	}
	
	public static Complex subtract(double val, Complex z)
	{
		return subtract(val, 0, z.m_real, z.m_imag);
	}
	
	public Complex subtract(double val)
	{
		return subtract(m_real, m_imag, val, 0);
	}
	
	public Complex subtract(Complex other)
	{
		return subtract(m_real, m_imag, other.m_real, other.m_imag);
	}
	
	public static Complex multiply(double val, Complex z)
	{
		return multiply(val, 0, z.m_real, z.m_imag);
	}
	
	public Complex multiply(double val)
	{
		return multiply(m_real, m_imag, val, 0);
	}
	
	public Complex multiply(Complex other)
	{
		return subtract(m_real, m_imag, other.m_real, other.m_imag);
	}
	
	public void inc()
	{
		inc(1);
	}
	
	public void inc(double val)
	{
		m_real += val;
	}
	
	public void dec(double val)
	{
		inc(-val);
	}
	
	public void dec()
	{
		dec(1);
	}
	
	public boolean isEquals(Complex other)
	{
		double DELTA = 0.000001;
		
		return abs(m_real - other.m_real) < DELTA && abs(m_imag - other.m_imag) < DELTA;
	}

	public boolean equals(Object other)
	{
		return other instanceof Complex z && abs(m_real - z.m_real) < DELTA && abs(m_imag - z.m_imag) < DELTA;
	}

	public String toString()
	{
		return String.format("(%f, %f)", m_real, m_imag);
	}
}
