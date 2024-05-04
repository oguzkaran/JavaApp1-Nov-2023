package org.csystem.util.string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringUtilPadLeadingForAddedDefaultCharAndLengthIsBiggestFromTextTest
{
    private static final char defaultCh = ' ';

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
                new DataInfo("Bekir", defaultCh + "Bekir"),
                new DataInfo("Kocadag", defaultCh + "Kocadag"),
                new DataInfo("Denizli", defaultCh + "Denizli"),
                new DataInfo("", defaultCh + "")
        );
    }

    public StringUtilPadLeadingForAddedDefaultCharAndLengthIsBiggestFromTextTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, StringUtil.padLeading(data.str, data.str.length() + 1));
    }
}
