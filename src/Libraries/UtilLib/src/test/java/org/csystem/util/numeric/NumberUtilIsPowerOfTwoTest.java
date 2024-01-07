package org.csystem.util.numeric;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested by Sinan Dönder")
@RunWith(Parameterized.class)
public class NumberUtilIsPowerOfTwoTest {

    public DataInfo data;

    static class DataInfo {
        long a;
        boolean expected;

        public DataInfo(long a, boolean expected)
        {
            this.a = a;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(32, true),
                new DataInfo(16, true),
                new DataInfo(48, false),
                new DataInfo(-32, false),
                new DataInfo(19, false),
                new DataInfo(1024, true)
        );
    }

    public NumberUtilIsPowerOfTwoTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, NumberUtil.isPowerOfTwo(data.a));
    }

}
