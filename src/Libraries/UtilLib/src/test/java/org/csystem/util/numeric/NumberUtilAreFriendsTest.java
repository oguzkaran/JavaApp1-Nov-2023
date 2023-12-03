package org.csystem.util.numeric;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested by Sinan Dönder")
@RunWith(Parameterized.class)
public class NumberUtilAreFriendsTest {

    public DataInfo data;

    static class DataInfo {
        int a;
        int b;
        boolean expected;

        DataInfo(int a, int b, boolean expected)
        {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(220, 284, true),
                new DataInfo(1184, 1210, true),
                new DataInfo(6232, 6368, true),
                new DataInfo(10_744, 10856, true),
                new DataInfo(17_296, 18_416, true),
                new DataInfo(9_363_584, 9_437_056, true),
                new DataInfo(1, 2, false),
                new DataInfo(2, 3, false),
                new DataInfo(3, 6368, false),
                new DataInfo(10_744, 10_555, false),
                new DataInfo(17_223, 18_413, false),
                new DataInfo(9_363_521, 9_437_111, false)
        );
    }

    public NumberUtilAreFriendsTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, NumberUtil.areFriends(data.a, data.b));
    }

}
