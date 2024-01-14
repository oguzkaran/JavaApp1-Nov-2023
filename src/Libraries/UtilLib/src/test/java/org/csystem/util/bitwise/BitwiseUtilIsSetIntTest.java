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
public class BitwiseUtilIsSetIntTest {

    public DataInfo data;

    static class DataInfo {
        int val;
        int n;

        public DataInfo(int val, int n)
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


    public BitwiseUtilIsSetIntTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertTrue(BitwiseUtil.isSet(data.val, data.n));
    }
}
