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
public class StringUtilAreAnagramTest {
    DataInfo data;

    static class DataInfo {
        String s1;
        String s2;
        boolean expected;

        DataInfo(String s1, String s2, boolean expected)
        {
            this.s1 = s1;
            this.s2 = s2;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("bart", "brat", true),
                new DataInfo("para", "arap", true),
                new DataInfo("bratt", "baart", false),
                new DataInfo("aaaa", "aaaa", true),
                new DataInfo("", "", false),
                new DataInfo("    ", "    ", false)
        );
    }

    public StringUtilAreAnagramTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, StringUtil.areAnagram(data.s1, data.s2));
    }
}
