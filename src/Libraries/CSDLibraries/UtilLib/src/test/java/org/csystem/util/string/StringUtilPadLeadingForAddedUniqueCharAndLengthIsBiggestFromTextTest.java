package org.csystem.util.string;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Written by Bekir Kocadağ")
@RunWith(Parameterized.class)
public class StringUtilPadLeadingForAddedUniqueCharAndLengthIsBiggestFromTextTest
{
    private static final char ch = '&';

    DataInfo data;

    static class DataInfo
    {
        String str;
        String expected;

        DataInfo(String str, String expected)
        {
            this.str = str;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("Bekir", ch + "Bekir"),
                new DataInfo("Kocadag", ch + "Kocadag"),
                new DataInfo("Denizli", ch + "Denizli"),
                new DataInfo("", ch + "")
        );
    }

    public StringUtilPadLeadingForAddedUniqueCharAndLengthIsBiggestFromTextTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, StringUtil.padLeading(data.str, data.str.length() + 1, ch));
    }
}
