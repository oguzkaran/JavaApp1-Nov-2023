package org.csystem.util.bitwise;

import org.csystem.bitwise.BitwiseUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilIndicesOfSetBitsLongTest {
    public DataInfo data;

    static class DataInfo {
        long a;
        int [] expected;

        public DataInfo(long a, int[] expected)
        {
            this.a = a;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(10, new int[]{1, 3}),
                new DataInfo(0, new int[0])
        );
    }

    public BitwiseUtilIndicesOfSetBitsLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertArrayEquals(data.expected, BitwiseUtil.indicesOfSetBits(data.a));
    }

}
