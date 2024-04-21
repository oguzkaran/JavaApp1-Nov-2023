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
public class StringUtilAreAllUniqueTest {
    DataInfo data;

    static class DataInfo {
        String str;
        boolean expected;

        public DataInfo(String str, boolean expected)
        {
            this.str = str;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("ali", true),
                new DataInfo("ankara", false),
                new DataInfo("oguz", true),
                new DataInfo("deniz", true),
                new DataInfo("", true)
        );
    }

    public StringUtilAreAllUniqueTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, StringUtil.areAllUnique(data.str));
    }
}
