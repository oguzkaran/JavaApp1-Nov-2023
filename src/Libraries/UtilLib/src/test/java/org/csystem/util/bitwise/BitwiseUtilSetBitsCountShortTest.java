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
public class BitwiseUtilSetBitsCountShortTest {

    public DataInfo data;

    static class DataInfo {
        short val;
        int expected;

        public DataInfo(short val, int expected)
        {
            this.val = val;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo((short)0x41, 2),
                new DataInfo((short)0x43, 3),
                new DataInfo((short)0x0, 0),
                new DataInfo((short)0xFFFF, 16),
                new DataInfo((short)0x7FFF, 15),
                new DataInfo((short)0x8000, 1));
    }

    public BitwiseUtilSetBitsCountShortTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.setBitsCount(data.val));
    }
}
