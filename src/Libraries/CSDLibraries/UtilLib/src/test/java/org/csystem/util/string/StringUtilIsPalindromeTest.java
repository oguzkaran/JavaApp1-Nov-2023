package org.csystem.util.string;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class StringUtilIsPalindromeTest {
    DataInfo data;

    static class DataInfo {
        String s1;
        boolean expected;

        DataInfo(String s1, boolean expected)
        {
            this.s1 = s1;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("yapay",  true),
                new DataInfo("radar", true),
                new DataInfo("teğet", true),
                new DataInfo("alipapila", true),
                new DataInfo("oğuzkaran", false),
                new DataInfo("gazicabalar", false)
        );
    }

    public StringUtilIsPalindromeTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, StringUtil.isPalindrome(data.s1));
    }
}
