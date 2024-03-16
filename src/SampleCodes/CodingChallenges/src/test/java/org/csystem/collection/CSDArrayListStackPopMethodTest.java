package org.csystem.collection;

import org.csystem.recursion.util.RecursionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;
@RunWith(Parameterized.class)
public class CSDArrayListStackPopMethodTest {
    private DataInfo m_dataInfo;

    static class DataInfo {
        CSDArrayListStack<Integer> values;
        int a;
        int b;

        public DataInfo(int a, int b)
        {
            this.a = a;
            this.b = b;
            values = new CSDArrayListStack<>();

            for (var i = a; i <= b; ++i)
                values.push(i);
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(new DataInfo(1, 10),  new DataInfo(1, 100));
    }

    public CSDArrayListStackPopMethodTest(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
    }

    @Test
    public void givenStack_whenFilled_thenPop()
    {
        Assert.assertEquals(m_dataInfo.b, (int)m_dataInfo.values.pop());
    }
}