/*-------------------------------------------------------------
	FILE		: Complex.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 6th October 2024
	
	Complex record that represents a complex number
	
	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.math;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public record Complex(double real, double imag) {
	private static final double DELTA = 0.000001;

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
		this(0);
	}

	public Complex(double real)
	{
		this(real, 0);
	}

	public double length()
	{
		return sqrt(real * real + imag * imag);
	}
	
	public double norm()
	{
		return length();
	}
	
	public Complex conjugate()
	{
		return new Complex(real, -imag);
	}
	
	public static Complex add(double val, Complex z)
	{
		return add(val, 0, z.real, z.imag);
	}
	
	public Complex add(double val)
	{
		return add(real, imag, val, 0);
	}
	
	public Complex add(Complex other)
	{
		return add(real, imag, other.real, other.imag);
	}
	
	public static Complex subtract(double val, Complex z)
	{
		return subtract(val, 0, z.real, z.imag);
	}
	
	public Complex subtract(double val)
	{
		return subtract(real, imag, val, 0);
	}
	
	public Complex subtract(Complex other)
	{
		return subtract(real, imag, other.real, other.imag);
	}
	
	public static Complex multiply(double val, Complex z)
	{
		return multiply(val, 0, z.real, z.imag);
	}
	
	public Complex multiply(double val)
	{
		return multiply(real, imag, val, 0);
	}
	
	public Complex multiply(Complex other)
	{
		return subtract(real, imag, other.real, other.imag);
	}

	@Override
	public boolean equals(Object other)
	{
		return other instanceof Complex z && abs(real - z.real) < DELTA && abs(imag - z.imag) < DELTA;
	}

	@Override
	public String toString()
	{
		return String.format("(%f, %f)", real, imag);
	}
}
