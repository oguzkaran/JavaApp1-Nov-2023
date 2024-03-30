package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CSDBoundedStackPeekMethodTest {
    private DataInfo m_dataInfo;

    static class DataInfo {
        CSDBoundedStack<Integer> values;
        int n;
        int expected;
        public DataInfo(int n, int expected)
        {
            values = new CSDBoundedStack<>(n);
            this.expected = expected;

            for (var i = 1; i <= n; ++i)
                values.push(i);
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(10, 10),  new DataInfo(100, 100));
    }

    public CSDBoundedStackPeekMethodTest(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        Assert.assertEquals(m_dataInfo.expected, (int)m_dataInfo.values.peek());
    }
}