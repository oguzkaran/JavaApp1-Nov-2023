package org.csystem.recursion.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class UtilReverseStringTest {
    DataInfo dataInfo;

    static class DataInfo {
        String s;
        String expected;

        public DataInfo(String s, String expected)
        {
            this.s = s;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo("ankara", "arakna"), new DataInfo("ali", "ila"), new DataInfo("", ""),
                new DataInfo("alipapila", "alipapila"));
    }

    public UtilReverseStringTest(DataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        Assert.assertEquals(dataInfo.expected, Util.reverse(dataInfo.s));
    }
}