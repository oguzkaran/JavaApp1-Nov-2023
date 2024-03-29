package org.csystem.util.bitwise;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilClearBitLongTest {

    public DataInfo data;

    static class DataInfo {
        long val;
        int n;
        int expected;

        DataInfo(long val, int n, int expected)
        {
            this.val = val;
            this.n = n;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(0x41, 6, 0x01), new DataInfo(0x41, 0, 0x40));
    }


    public BitwiseUtilClearBitLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.clearBit(data.val, data.n));
    }
}
