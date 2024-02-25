package org.csystem.util.bitwise;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilToBinaryStrIntTest {

    public DataInfo data;

    static class DataInfo {
        int a;
        String s;

        DataInfo(int a, String s)
        {
            this.a = a;
            this.s = s;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(-10, "11111111111111111111111111110110"),
                new DataInfo(10, "00000000000000000000000000001010"),
                new DataInfo(0, "00000000000000000000000000000000"),
                new DataInfo(-1, "11111111111111111111111111111111")
        );
    }

    public BitwiseUtilToBinaryStrIntTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.s, BitwiseUtil.toBitsStr(data.a));
    }

}
