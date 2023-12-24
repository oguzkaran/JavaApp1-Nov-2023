package org.csystem.recursion.util;

public class RecursionUtil {
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

    public static long factorial(int n)
    {
        if (n <= 0)
            return 1;

        return n * factorial(n - 1);
    }

    public static int gcd(int a, int b)
    {
        return gcdRecur(Math.abs(a), Math.abs(b));
    }

    public static String reverse(String s)
    {
        char [] chars = s.toCharArray();

        reverse(s.toCharArray(), 0, s.length() - 1);

        return String.valueOf(chars);
    }
}
