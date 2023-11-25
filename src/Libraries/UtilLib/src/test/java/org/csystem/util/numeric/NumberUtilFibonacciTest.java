package org.csystem.util.numeric;

import org.csystem.util.numeric.data.IntIntDataInfo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;


@Ignore("Tested before and passed")
@RunWith(Parameterized.class)
public class NumberUtilFibonacciTest {
    public IntIntDataInfo dataInfo;

    @Parameterized.Parameters
    public static Collection<IntIntDataInfo> createData()
    {
        return List.of(new IntIntDataInfo(1, 0), new IntIntDataInfo(2, 1), new IntIntDataInfo(3, 1), new IntIntDataInfo(4, 2), new IntIntDataInfo(5, 3));
    }

    public NumberUtilFibonacciTest(IntIntDataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void givenValue_WhenIndex_ThenReturnsNumber()
    {
        Assert.assertEquals(dataInfo.expected, NumberUtil.fibonacciNumber(dataInfo.input));
    }
}
