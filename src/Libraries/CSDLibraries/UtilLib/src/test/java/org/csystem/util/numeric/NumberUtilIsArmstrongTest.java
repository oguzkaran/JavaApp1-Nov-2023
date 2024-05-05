package org.csystem.util.numeric;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class NumberUtilIsArmstrongTest {

    public DataInfo data;

    static class DataInfo {
        int a;
        boolean expected;

        public DataInfo(int a, boolean expected)
        {
            this.a = a;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(153, true),
                new DataInfo(407, true),
                new DataInfo(3, true),
                new DataInfo(0, true),
                new DataInfo(-3, false)
        );
    }

    public NumberUtilIsArmstrongTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, NumberUtil.isArmstrong(data.a));
    }

}
