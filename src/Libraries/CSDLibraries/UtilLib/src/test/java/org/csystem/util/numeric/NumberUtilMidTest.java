package org.csystem.util.numeric;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested by Yaşar Uğur Güleç")
@RunWith(Parameterized.class)
public class NumberUtilMidTest {
    DataInfo dataInfo;
    static class DataInfo {
        int input1;
        int input2;
        int input3;
        int expected;

        DataInfo(int input1, int input2, int input3, int expected)
        {
            this.input1 = input1;
            this.input2 = input2;
            this.input3 = input3;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(5, 4, 9, 5),
                       new DataInfo(10, 10, -3, 10),
                       new DataInfo(-8, -12, -20, -12),
                       new DataInfo(-3, 0, 12, 0),
                       new DataInfo(12, 12, 12, 12),
                       new DataInfo(0, 0, 0, 0)
                );
    }

    public NumberUtilMidTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        assertEquals(dataInfo.expected, NumberUtil.mid(dataInfo.input1, dataInfo.input2, dataInfo.input3));
    }
}