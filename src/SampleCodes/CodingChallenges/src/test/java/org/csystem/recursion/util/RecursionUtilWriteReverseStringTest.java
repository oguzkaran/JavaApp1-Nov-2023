package org.csystem.recursion.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class RecursionUtilWriteReverseStringTest {
    String str;

    @Parameterized.Parameters
    public static Collection<String> createData()
    {
        return List.of("ankara", "ali", "", "alipapila", "istanbul");
    }

    public RecursionUtilWriteReverseStringTest(String str)
    {
        this.str = str;
    }

    @Test
    public void test()
    {
        RecursionUtil.writeReverse(str);
        System.out.println();
    }
}