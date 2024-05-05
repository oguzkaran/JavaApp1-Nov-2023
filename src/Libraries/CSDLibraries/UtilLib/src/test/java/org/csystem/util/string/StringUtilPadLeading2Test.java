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
public class StringUtilPadLeading2Test
{
    private static final char ch = '&';
    private static final char defaultCh = ' ';
    private static final String EMPTY = "";
    private static final String name = "Bekir";
    private static final String surname = "Kocadag";
    private static final String city = "Denizli";

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
                new DataInfo(name, defaultCh + name, false, false),
                new DataInfo(surname, defaultCh + surname, false, false),
                new DataInfo(city, defaultCh + city, false, false),
                new DataInfo(EMPTY, defaultCh + EMPTY, false, false),
                new DataInfo(name, name, true, false),
                new DataInfo(surname, surname, true, false),
                new DataInfo(city, city, true, false),
                new DataInfo(EMPTY, EMPTY, true, false),
                new DataInfo(name, ch + name, false, true),
                new DataInfo(surname, ch + surname, false, true),
                new DataInfo(city, ch + city, false, true),
                new DataInfo(EMPTY, ch + EMPTY, false, true),
                new DataInfo(name, name, true, true),
                new DataInfo(surname, surname, true, true),
                new DataInfo(city, city, true, true),
                new DataInfo(EMPTY, EMPTY, true, true)
        );
    }

    public StringUtilPadLeading2Test(DataInfo data)
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
