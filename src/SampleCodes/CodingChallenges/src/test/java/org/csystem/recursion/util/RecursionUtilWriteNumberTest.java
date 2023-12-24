package org.csystem.recursion.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class RecursionUtilWriteNumberTest {
    int value;

    @Parameterized.Parameters
    public static Collection<Integer> createData()
    {
        return List.of(0, -123, 123, 1234, -1234, 92765, -92765, 1, -1);
    }

    public RecursionUtilWriteNumberTest(int value)
    {
        this.value = value;
    }

    @Test
    public void test()
    {
        RecursionUtil.writeNumber(value);
        System.out.println();
    }
}