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
public class BitwiseUtilToBinaryStrLongTest {

    public DataInfo data;

    static class DataInfo {
        long a;
        String s;

        DataInfo(long a, String s)
        {
            this.a = a;
            this.s = s;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(-10, "1111111111111111111111111111111111111111111111111111111111110110"),
                new DataInfo(10, "0000000000000000000000000000000000000000000000000000000000001010"),
                new DataInfo(0, "0000000000000000000000000000000000000000000000000000000000000000"),
                new DataInfo(-1, "1111111111111111111111111111111111111111111111111111111111111111")
        );
    }

    public BitwiseUtilToBinaryStrLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.s, BitwiseUtil.toBitsStr(data.a));
    }

}
