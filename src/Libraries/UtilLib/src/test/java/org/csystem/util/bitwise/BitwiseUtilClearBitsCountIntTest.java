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
public class BitwiseUtilClearBitsCountIntTest {

    public DataInfo data;

    static class DataInfo {
        int val;
        int expected;

        public DataInfo(int val, int expected)
        {
            this.val = val;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(0x41, 30),
                new DataInfo(0x43, 29),
                new DataInfo(0x0, 32),
                new DataInfo(0xFFFFFFFF, 0),
                new DataInfo(0x7FFFFFFF, 1),
                new DataInfo(0x80000000, 31));
    }

    public BitwiseUtilClearBitsCountIntTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.clearBitsCount(data.val));
    }
}
