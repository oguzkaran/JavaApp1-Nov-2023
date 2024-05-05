
package org.csystem.util.numeric;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@Ignore("Written by Buğrahan Kısa")
@RunWith(Parameterized.class)
public class NumberUtilDigitsFactorialSumTest {
    public DataInfo dataInfo;

    static class DataInfo {
        int input;
        long expected;

        public DataInfo(int input, long expected)
        {
            this.input = input;
            this.expected = expected;
        }
    }

    public NumberUtilDigitsFactorialSumTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(123, 9),
                new DataInfo(-123, 9),
                new DataInfo(234, 32)
        );
    }

    @Test
    public void test()
    {
        Assert.assertEquals(dataInfo.expected, NumberUtil.digitsFactorialSum(dataInfo.input));
    }
}
