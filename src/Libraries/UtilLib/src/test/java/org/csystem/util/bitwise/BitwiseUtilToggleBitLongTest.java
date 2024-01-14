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
public class BitwiseUtilToggleBitLongTest {

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
        return List.of(new DataInfo(0x41, 6, 0x01), new DataInfo(0x41, 1, 0x43));
    }

    public BitwiseUtilToggleBitLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.toggleBit(data.val, data.n));
    }
}
