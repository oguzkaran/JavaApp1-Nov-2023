/*-------------------------------------------------------------
	FILE		: BitwiseUtil.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 7th January 2024

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
    public static int clearBitsCount(int val)
    {
        return Integer.SIZE - setBitsCount(val);
    }

    public static int clearBitsCount(long val)
    {
        return Long.SIZE - setBitsCount(val);
    }

    public static int clearBitsCount(short val)
    {
        return Short.SIZE - setBitsCount(val);
    }

    public static int clearBitsCount(byte val)
    {
        return Byte.SIZE - setBitsCount(val);
    }

    public static int clearBitsCount(char val)
    {
        return Character.SIZE - setBitsCount(val);
    }

    public static boolean isClear(int val, int n)
    {
        return !isSet(val, n);
    }

    public static boolean isClear(long val, int n)
    {
        return !isSet(val, n);
    }

    public static boolean isReset(int val, int n)
    {
        return isClear(val, n);
    }

    public static boolean isReset(long val, int n)
    {
        return isClear(val, n);
    }

    public static boolean isSet(int val, int n)
    {
        return (val & 1 << n) != 0;
    }

    public static boolean isSet(long val, int n)
    {
        return (val & 1L << n) != 0;
    }

    public static int resetBit(int val, int n)
    {
        return clearBit(val, n);
    }

    public static long resetBit(long val, int n)
    {
        return clearBit(val, n);
    }
    public static int resetBitsCount(int val)
    {
        return clearBitsCount(val);
    }

    public static int resetBitsCount(long val)
    {
        return clearBitsCount(val);
    }

    public static int resetBitsCount(short val)
    {
        return clearBitsCount(val);
    }

    public static int resetBitsCount(byte val)
    {
        return clearBitsCount(val);
    }

    public static int resetBitsCount(char val)
    {
        return clearBitsCount(val);
    }

    public static int setBit(int val, int n)
    {
        return val | 1 << n;
    }

    public static long setBit(long val, int n)
    {
        return val | 1L << n;
    }

    public static int setBitsCount(int val)
    {
        var count = 0;

        for (var i = 0; i < Integer.SIZE; ++i)
            if (isSet(val, i))
                ++count;

        return count;
    }

    public static int setBitsCount(long val)
    {
        var count = 0;

        for (var i = 0; i < Long.SIZE; ++i)
            if (isSet(val, i))
                ++count;

        return count;
    }


    public static int setBitsCount(short val)
    {
        var count = 0;

        for (var i = 0; i < Short.SIZE; ++i)
            if (isSet(val, i))
                ++count;

        return count;
    }

    public static int setBitsCount(byte val)
    {
        var count = 0;

        for (var i = 0; i < Byte.SIZE; ++i)
            if (isSet(val, i))
                ++count;

        return count;
    }

    public static int setBitsCount(char val)
    {
        var count = 0;

        for (var i = 0; i < Character.SIZE; ++i)
            if (isSet(val, i))
                ++count;

        return count;
    }

    public static String toBitsStr(int val)
    {
        var sb = new StringBuilder(Integer.SIZE);

        for (var i = Integer.SIZE - 1; i >= 0; --i)
            sb.append(isSet(val, i) ? '1' : '0');

        return sb.toString();
    }

    public static String toBitsStr(long val)
    {
        var sb = new StringBuilder(Long.SIZE);

        for (var i = Long.SIZE - 1; i >= 0; --i)
            sb.append(isSet(val, i) ? '1' : '0');

        return sb.toString();
    }

    public static String toBitsStr(short val)
    {
        var sb = new StringBuilder(Short.SIZE);

        for (var i = Short.SIZE - 1; i >= 0; --i)
            sb.append(isSet(val, i) ? '1' : '0');

        return sb.toString();
    }

    public static String toBitsStr(byte val)
    {
        var sb = new StringBuilder(Byte.SIZE);

        for (var i = Byte.SIZE - 1; i >= 0; --i)
            sb.append(isSet(val, i) ? '1' : '0');

        return sb.toString();
    }

    public static String toBitsStr(char val)
    {
        var sb = new StringBuilder(Character.SIZE);

        for (var i = Character.SIZE - 1; i >= 0; --i)
            sb.append(isSet(val, i) ? '1' : '0');

        return sb.toString();
    }

    public static int toggleBit(int val, int n)
    {
        return val ^ 1 << n;
    }

    public static long toggleBit(long val, int n)
    {
        return val ^ 1L << n;
    }
}
