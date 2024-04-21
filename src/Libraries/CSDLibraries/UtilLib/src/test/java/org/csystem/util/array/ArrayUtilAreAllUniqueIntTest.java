package org.csystem.util.array;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class ArrayUtilAreAllUniqueIntTest {
    DataInfo data;

    static class DataInfo {
        int [] a;
        boolean expected;

        public DataInfo(boolean expected, int...a)
        {
            this.a = a;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(true, 1, 2, 3, 4, 5),
                new DataInfo(false, 1, 2, 3, 4, 2, 5),
                new DataInfo(true, 1)
        );
    }

    public ArrayUtilAreAllUniqueIntTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, ArrayUtil.areAllUnique(data.a));
    }
}
