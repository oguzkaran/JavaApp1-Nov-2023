package org.csystem.util.bitwise;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(Parameterized.class)
public class BitwiseUtilIsClearLongTest {

    public DataInfo data;

    static class DataInfo {
        long val;
        int n;

        public DataInfo(long val, int n)
        {
            this.val = val;
            this.n = n;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(0x41, 5), new DataInfo(0x41, 1),  new DataInfo(0x43, 2));
    }

    public BitwiseUtilIsClearLongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertTrue(BitwiseUtil.isClear(data.val, data.n));
    }
}
