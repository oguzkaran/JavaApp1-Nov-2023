package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

@RunWith(Parameterized.class)
public class CSDStackSearchTest {
    private DataInfo m_dataInfo;

    static class DataInfo {
        CSDStack<Integer> values;
        Stack<Integer> stack;
        int value;

        public DataInfo(int a, int b, int value)
        {
            this.value = value;
            values = new CSDStack<>();
            stack = new Stack<>();

            for (var i = a; i <= b; ++i) {
                values.push(i);
                stack.push(i);
            }
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(1, 10, 9),  new DataInfo(1, 100, 101), new DataInfo(1, 100, 94));
    }

    public CSDStackSearchTest(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
    }
    @Test
    public void test()
    {
        Assert.assertEquals(m_dataInfo.stack.search(m_dataInfo.value), m_dataInfo.values.search(m_dataInfo.value));
    }
}
