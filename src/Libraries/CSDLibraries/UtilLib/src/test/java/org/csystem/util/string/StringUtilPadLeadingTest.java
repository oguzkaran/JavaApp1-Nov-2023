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
public class StringUtilPadLeadingTest
{
    private static final char ch = '&';
    private static final char defaultCh = ' ';

    DataInfo data;

    static class DataInfo
    {
        String str;
        String expected;
        boolean isLengtSmallFromText;
        boolean isSendCh;

        DataInfo(String str, String expected, boolean isLengtSmallFromText, boolean isSendCh)
        {
            this.str = str;
            this.expected = expected;
            this.isLengtSmallFromText = isLengtSmallFromText;
            this.isSendCh = isSendCh;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("Bekir", defaultCh + "Bekir", false, false),
                new DataInfo("Kocadag", defaultCh + "Kocadag", false, false),
                new DataInfo("Denizli", defaultCh + "Denizli", false, false),
                new DataInfo("", defaultCh + "", false, false),
                new DataInfo("Bekir", "Bekir", true, false),
                new DataInfo("Kocadag", "Kocadag", true, false),
                new DataInfo("Denizli", "Denizli", true, false),
                new DataInfo("", "", true, false),
                new DataInfo("Bekir", ch + "Bekir", false, true),
                new DataInfo("Kocadag", ch + "Kocadag", false, true),
                new DataInfo("Denizli", ch + "Denizli", false, true),
                new DataInfo("", ch + "", false, true),
                new DataInfo("Bekir", "Bekir", true, true),
                new DataInfo("Kocadag", "Kocadag", true, true),
                new DataInfo("Denizli", "Denizli", true, true),
                new DataInfo("", "", true, true)
        );
    }

    public StringUtilPadLeadingTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        if (data.isSendCh)
            assertEquals(data.expected, StringUtil.padLeading(data.str, len(data), ch));
        else
            assertEquals(data.expected, StringUtil.padLeading(data.str, len(data)));
    }

    private int len(DataInfo data)
    {
        return data.isLengtSmallFromText ? data.str.length() - 1 : data.str.length() + 1;
    }
}
