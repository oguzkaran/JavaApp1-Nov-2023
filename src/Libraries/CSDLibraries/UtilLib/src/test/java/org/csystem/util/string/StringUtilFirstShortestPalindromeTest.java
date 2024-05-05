package org.csystem.util.string;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Mahmut Gazi Çabalar")
@RunWith(Parameterized.class)
public class StringUtilFirstShortestPalindromeTest {
    DataInfo data;

    static class DataInfo {
        String s1;
        String expected;

        DataInfo(String s1, String expected)
        {
            this.s1 = s1;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("adaecexyzanastasmumsatsanaxyzeyedipadanadapideyexyzalipapilakazakradarmakamadaecexyzanastasmumsatsanakırık",  "ada"),
                new DataInfo("nalxyzotoanastasmumsatsanaxyzxyzalipapilakazakradarmakamadaecexyzanastasmumsatsanakırık", "oto"),
                new DataInfo("xyzataxyzkırıkxyzalipapilakazakaslısanmışımnasılsaradarmakamanastasmumsatsana", "ata")

        );
    }

    public StringUtilFirstShortestPalindromeTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, StringUtil.getFirstShortestPalindrome(data.s1));
    }
}
