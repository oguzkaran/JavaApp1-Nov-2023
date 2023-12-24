package org.csystem.recursion.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;


@RunWith(Parameterized.class)
public class RecursionUtilGcdTest {
    DataInfo dataInfo;

    static class DataInfo {
        int a;
        int b;
        long expected;

        public DataInfo(int a, int b, long expected)
        {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(3, 4, 1),  new DataInfo(8, 18, 2), new DataInfo(5, 25, 5), new DataInfo(2, 2, 2),
                new DataInfo(-3, -4, 1),  new DataInfo(8, -18, 2), new DataInfo(-5, -25, 5), new DataInfo(2, -2, 2));
    }

    public RecursionUtilGcdTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        Assert.assertEquals(dataInfo.expected, RecursionUtil.gcd(dataInfo.a, dataInfo.b));
    }
}