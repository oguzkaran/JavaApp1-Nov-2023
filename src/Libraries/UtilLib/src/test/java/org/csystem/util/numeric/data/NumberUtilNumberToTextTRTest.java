package org.csystem.util.numeric.data;

import org.csystem.util.numeric.NumberUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class NumberUtilNumberToTextTRTest {

    public DataInfo data;

    static class DataInfo {
        long a;
        String expected;

        DataInfo(long a, String expected)
        {
            this.a = a;
            this.expected = expected;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo(32, "otuziki"),
                new DataInfo(16, "onaltı"),
                new DataInfo(48, "kırksekiz"),
                new DataInfo(-32, "eksiotuziki"),
                new DataInfo(19, "ondokuz"),
                new DataInfo(-123, "eksiyüzyirmiüç"),
                new DataInfo(0, "sıfır")
        );
    }

    public NumberUtilNumberToTextTRTest(DataInfo data)
    {
        this.data = data;
    }

    @Test
    public void test()
    {
        assertEquals(data.expected, NumberUtil.numberToTextTR(data.a));
    }
}
