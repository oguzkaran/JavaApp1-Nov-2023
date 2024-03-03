package org.csystem.util.numeric;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;


@Ignore("Tested before and passed")
@RunWith(Parameterized.class)
public class NumberUtilToIntTest {
    public DataInfo dataInfo;

    static class DataInfo {
        public String input;
        public OptionalInt expected;

        public DataInfo(String input, OptionalInt expected)
        {
            this.input = input;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo("10", OptionalInt.of(10)), new DataInfo("-10", OptionalInt.of(-10)),
                new DataInfo("ali", OptionalInt.empty()), new DataInfo("10ali", OptionalInt.empty()));
    }

    public NumberUtilToIntTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        Assert.assertEquals(dataInfo.expected, NumberUtil.toInt(dataInfo.input));
    }
}
