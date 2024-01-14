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
public class BitwiseUtilSetBitsCountLongTest {

    public DataInfo data;

    static class DataInfo {
        long val;
        int expected;

        public DataInfo(long val, int expected)
        {
            this.val = val;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(0x0000000000000041, 2),
                new DataInfo(0x0000000000000043, 3),
                new DataInfo(0x0000000000000000, 0),
                new DataInfo(0xFFFFFFFFFFFFFFFFL, 64),
                new DataInfo(0x7FFFFFFFFFFFFFFFL, 63),
                new DataInfo(0x8000000000000000L, 1));
    }

    public BitwiseUtilSetBitsCountLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.setBitsCount(data.val));
    }
}
