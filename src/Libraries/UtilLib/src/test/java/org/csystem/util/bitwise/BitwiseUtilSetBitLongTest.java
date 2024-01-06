package org.csystem.util.bitwise;

import org.csystem.bitwise.BitwiseUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilSetBitLongTest {

    public DataInfo data;

    static class DataInfo {
        long val;
        int n;
        long expected;

        public DataInfo(long val, int n, long expected)
        {
            this.val = val;
            this.n = n;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(0x41, 4, 0x51), new DataInfo(0x41, 5, 0x61));
    }

    public BitwiseUtilSetBitLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.setBit(data.val, data.n));
    }

}
