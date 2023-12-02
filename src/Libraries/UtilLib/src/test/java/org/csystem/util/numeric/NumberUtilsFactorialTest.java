package org.csystem.util.numeric;

import org.csystem.util.numeric.data.IntLongDataInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested by Hüseyin Mercimek")
@RunWith(Parameterized.class)
public class NumberUtilsFactorialTest {
    public IntLongDataInfo intLongDataInfo;

    public NumberUtilsFactorialTest(IntLongDataInfo intLongDataInfo)
    {
        this.intLongDataInfo = intLongDataInfo;
    }

    @Parameterized.Parameters
    public static Collection<IntLongDataInfo> creatData()
    {
        return List.of(new IntLongDataInfo(3, 6L), new IntLongDataInfo(5, 120L), new IntLongDataInfo(13, 6227020800L), new IntLongDataInfo(4, 24));
    }

    @Test
    public void test()
    {
        assertEquals(intLongDataInfo.expected, NumberUtil.factorial(intLongDataInfo.input));
    }
}