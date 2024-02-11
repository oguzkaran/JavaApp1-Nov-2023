package org.csystem.recursion.util;

public class RecursionUtil {
    private static int fibonacciNumberRecur(int n)
    {
        return n <= 2 ? n - 1 : fibonacciNumberRecur(n - 1) + fibonacciNumberRecur(n - 2);
    }

    private static int gcdRecur(int a, int b)
    {
        return b == 0 ? a : gcdRecur(b, a % b);
    }

    private static void reverse(char [] chars, int left, int right)
    {
        if (left >= right)
            return;

        char temp = chars[left];

        chars[left] = chars[right];
        chars[right] = temp;

        reverse(chars, left + 1, right - 1);
    }

    public static void writeNumberRecur(int val)
    {
        if (val / 10 != 0)
            writeNumberRecur(val / 10);

        System.out.write((char)(val % 10 + '0'));
    }

    private static void writeReverse(String s, int i)
    {
        if (i == s.length())
            return;

        writeReverse(s, i + 1);
        System.out.print(s.charAt(i));
    }

    public static long factorial(int n)
    {
        return n <= 0 ? 1 : n * factorial(n - 1);
    }

    public static int fibonacciNumber(int n)
    {
        return n <= 0 ? -1 : fibonacciNumberRecur(n);
    }

    public static int gcd(int a, int b)
    {
        return gcdRecur(Math.abs(a), Math.abs(b));
    }

    public static String reverse(String s)
    {
        char [] chars = s.toCharArray();

        reverse(chars, 0, s.length() - 1);

        return String.valueOf(chars);
    }

    public static void writeCollatz(int val)
    {
        //TODO:
    }

    public static void writeNumber(int val)
    {
        if (val < 0) {
            System.out.write('-');
            val = -val;
        }

        writeNumberRecur(val);
    }

    public static void writeReverse(String s)
    {
        writeReverse(s, 0);
    }
}
