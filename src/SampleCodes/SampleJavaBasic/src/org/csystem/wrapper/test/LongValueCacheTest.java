package org.csystem.wrapper.test;

import org.csystem.wrapper.LongValue;

public class LongValueCacheTest {
    public static void run()
    {
        LongValue val1 = LongValue.of(10);
        LongValue val2 = LongValue.of(10);
        LongValue val3 = LongValue.of(128);
        LongValue val4 = LongValue.of(128);

        System.out.println(val1 == val2 ? "Aynı nesne" : "Farklı nesneler");
        System.out.println(val3 == val4 ? "Aynı nesne" : "Farklı nesneler");
    }

    public static void main(String[] args)
    {
        run();
    }
}
