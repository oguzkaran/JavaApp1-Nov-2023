package org.csystem.util.bitwise;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilLowestSetBitIndexIntTest {
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
        return List.of(
                new DataInfo(10, 1),
                new DataInfo(8, 3),
                new DataInfo(0, -1)
        );
    }

    public BitwiseUtilLowestSetBitIndexIntTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, BitwiseUtil.lowestSetBitIndex(data.val));
    }
}
