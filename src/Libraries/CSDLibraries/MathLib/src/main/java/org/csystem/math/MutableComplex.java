/*-------------------------------------------------------------
	FILE		: MutableComplex.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 6th October 2024
	
	MutableComplex class that represents a complex number
	
	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math;

import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class MutableComplex {
	private static final double DELTA = 0.000001;

	private double m_real;
	private double m_imag;

	private static MutableComplex add(double a1, double b1, double a2, double b2)
	{
		return new MutableComplex(a1 + a2, b1 + b2);
	}

	private static MutableComplex subtract(double a1, double b1, double a2, double b2)
	{
		return add(a1, b1, -a2, -b2);
	}

	private static MutableComplex multiply(double a1, double b1, double a2, double b2)
	{
		return new MutableComplex(a1 * a2 - b1 * b2, a1 * b2 + a2 * b1);
	}

	public MutableComplex()
	{
	}


	public MutableComplex(double re)
	{
		m_real = re;
	}

	public MutableComplex(double re, double im)
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
	
	public MutableComplex getConjugate()
	{
		return new MutableComplex(m_real, -m_imag);
	}
	
	public static MutableComplex add(double val, MutableComplex z)
	{
		return add(val, 0, z.m_real, z.m_imag);
	}
	
	public MutableComplex add(double val)
	{
		return add(m_real, m_imag, val, 0);
	}
	
	public MutableComplex add(MutableComplex other)
	{
		return add(m_real, m_imag, other.m_real, other.m_imag);
	}
	
	public static MutableComplex subtract(double val, MutableComplex z)
	{
		return subtract(val, 0, z.m_real, z.m_imag);
	}
	
	public MutableComplex subtract(double val)
	{
		return subtract(m_real, m_imag, val, 0);
	}
	
	public MutableComplex subtract(MutableComplex other)
	{
		return subtract(m_real, m_imag, other.m_real, other.m_imag);
	}
	
	public static MutableComplex multiply(double val, MutableComplex z)
	{
		return multiply(val, 0, z.m_real, z.m_imag);
	}
	
	public MutableComplex multiply(double val)
	{
		return multiply(m_real, m_imag, val, 0);
	}
	
	public MutableComplex multiply(MutableComplex other)
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

	@Override
	public boolean equals(Object other)
	{
		return other instanceof MutableComplex z && abs(m_real - z.m_real) < DELTA && abs(m_imag - z.m_imag) < DELTA;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(m_real, m_imag);
	}

	@Override
	public String toString()
	{
		return String.format("(%f, %f)", m_real, m_imag);
	}
}
