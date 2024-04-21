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
public class ArrayUtilAreAllUniqueGenericTest {
    DataInfo data;

    static class DataInfo {
        String [] str;
        boolean expected;

        public DataInfo(boolean expected, String...str)
        {
            this.str = str;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(true, "ankara", "istanbul", "izmir"),
                new DataInfo(false, "ankara", "istanbul", "ankara", "izmir"),
                new DataInfo(false, "", ""),
                new DataInfo(true, ""),
                new DataInfo(false, "   ", "   ")
        );
    }

    public ArrayUtilAreAllUniqueGenericTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, ArrayUtil.areAllUnique(data.str));
    }
}
