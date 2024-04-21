package org.csystem.util.collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class CollectionUtilUtilAreAllUniqueTest {
    DataInfo data;

    static class DataInfo {
        List<String> str;
        boolean expected;

        public DataInfo(boolean expected, List<String> str)
        {
            this.str = str;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(true, new ArrayList<>() {{add("ankara"); add("istanbul"); add("izmir");}}),
                new DataInfo(false, new ArrayList<>() {{add("ankara"); add("istanbul"); add("ankara"); add("izmir");}}),
                new DataInfo(false, new ArrayList<>() {{add(""); add("");}}),
                new DataInfo(true, new ArrayList<>() {{add("");}}),
                new DataInfo(false, new ArrayList<>() {{add("    "); add("    ");}})
        );
    }

    public CollectionUtilUtilAreAllUniqueTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, CollectionUtil.areAllUnique(data.str));
    }
}
