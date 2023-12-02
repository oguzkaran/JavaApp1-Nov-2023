package org.csystem.util.numeric;

import org.csystem.util.numeric.data.IntIntDataInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested by Hüseyin Mercimek")
@RunWith(Parameterized.class)
public class NumberUtilNextClosestFibonacciTest {
    public IntIntDataInfo intIntDataInfo;

    public NumberUtilNextClosestFibonacciTest(IntIntDataInfo intIntDataInfo)
    {
        this.intIntDataInfo = intIntDataInfo;
    }

    @Parameterized.Parameters
    public static Collection<IntIntDataInfo> createData()
    {
        return List.of(new IntIntDataInfo(2, 3), new IntIntDataInfo(5, 8), new IntIntDataInfo(89, 144));
    }

    @Test
    public void test()
    {
        assertEquals(intIntDataInfo.expected, NumberUtil.nextClosestFibonacciNumber(intIntDataInfo.input));
    }
}