package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CSDArrayListStackPeekMethodTest {
    private DataInfo m_dataInfo;

    static class DataInfo {
        CSDArrayListStack<Integer> values;
        int expected;

        public DataInfo(int a, int b, int expected)
        {
            values = new CSDArrayListStack<>();

            for (var i = a; i <= b; ++i)
                values.push(i);

            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(1, 10, 10),  new DataInfo(1, 100, 100));
    }

    public CSDArrayListStackPeekMethodTest(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
    }

    @Test
    public void test()
    {
        Assert.assertEquals(m_dataInfo.expected, (int)m_dataInfo.values.peek());
    }
}