package org.csystem.util.bitwise;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilSetBitsCountIntTest {

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
        return List.of(new DataInfo(0x41, 2),
                new DataInfo(0x43, 3),
                new DataInfo(0x0, 0),
                new DataInfo(0xFFFFFFFF, 32),
                new DataInfo(0x7FFFFFFF, 31),
                new DataInfo(0x80000000, 1));
    }

    public BitwiseUtilSetBitsCountIntTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.setBitsCount(data.val));
    }
}
