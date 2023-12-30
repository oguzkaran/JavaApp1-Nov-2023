/*-------------------------------------------------------------
	FILE		: BitwiseUtil.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 30th December 2023

	Utility class for bitwise operations

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.bitwise;

public class BitwiseUtil {
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
