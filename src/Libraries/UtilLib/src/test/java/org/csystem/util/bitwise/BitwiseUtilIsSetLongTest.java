package org.csystem.util.bitwise;

import org.csystem.bitwise.BitwiseUtil;
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
public class BitwiseUtilIsSetLongTest {

    public DataInfo data;

    static class DataInfo {
        long val;
        int n;

        DataInfo(long val, int n)
        {
            this.val = val;
            this.n = n;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(0x41, 6), new DataInfo(0x41, 0),  new DataInfo(0x43, 1));
    }


    public BitwiseUtilIsSetLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertTrue(BitwiseUtil.isSet(data.val, data.n));
    }
}
