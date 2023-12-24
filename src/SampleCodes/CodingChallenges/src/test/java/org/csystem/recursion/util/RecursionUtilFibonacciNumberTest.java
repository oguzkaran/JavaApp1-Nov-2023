package org.csystem.recursion.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;


@RunWith(Parameterized.class)
public class RecursionUtilFibonacciNumberTest {
    DataInfo dataInfo;

    static class DataInfo {
        int input;
        int expected;

        DataInfo(int input, int expected)
        {
            this.input = input;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(-1, -1),  new DataInfo(0, -1), new DataInfo(1, 0), new DataInfo(2, 1), new DataInfo(3, 1),
                new DataInfo(4, 2), new DataInfo(5, 3), new DataInfo(6, 5), new DataInfo(7, 8), new DataInfo(8, 13));
    }

    public RecursionUtilFibonacciNumberTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        Assert.assertEquals(dataInfo.expected, RecursionUtil.fibonacciNumber(dataInfo.input));
    }
}