/*-------------------------------------------------------------
	FILE		: BitwiseUtil.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 6th January 2024

	Utility class for bitwise operations

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.bitwise;

public class BitwiseUtil {

    public static int clearBit(int val, int n)
    {
        return val & ~(1 << n);
    }

    public static long clearBit(long val, int n)
    {
        return val & ~(1L << n);
    }

    public static int setBit(int val, int n)
    {
        return val | 1 << n;
    }

    public static long setBit(long val, int n)
    {
        return val | 1L << n;
    }

    public static String toBinaryStr(int val)
    {
        var str = Integer.toBinaryString(val);
        var size = Integer.SIZE - str.length();

        return size == 0 ? str : String.format("%0" + size +"d", 0) + str;
    }

    public static String toBinaryStr(long val)
    {
        var str = Long.toBinaryString(val);
        var size = Long.SIZE - str.length();

        return size == 0 ? str : String.format("%0" + size +"d", 0) + str;
    }
}
