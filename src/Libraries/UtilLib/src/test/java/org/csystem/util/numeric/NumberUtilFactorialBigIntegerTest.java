package org.csystem.util.numeric;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested")
@RunWith(Parameterized.class)
public class NumberUtilFactorialBigIntegerTest {
    public DataInfo dataInfo;

    static class DataInfo {
        int n;
        BigInteger expected;

        DataInfo(int n, BigInteger expected)
        {
            this.n = n;
            this.expected = expected;
        }
    }

    public NumberUtilFactorialBigIntegerTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> creatData()
    {
        return List.of(new DataInfo(3, BigInteger.valueOf(6)),
                new DataInfo(5, BigInteger.valueOf(120)),
                new DataInfo(13, BigInteger.valueOf(6227020800L)),
                new DataInfo(4, BigInteger.valueOf(24)));
    }

    @Test
    public void test()
    {
        assertEquals(dataInfo.expected, NumberUtil.factorialBigInteger(dataInfo.n));
    }
}