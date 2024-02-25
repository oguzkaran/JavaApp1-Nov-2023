package org.csystem.util.numeric;

import org.csystem.util.numeric.data.IntIntDataInfo;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@Ignore("Tested by Berkay Yılmaz")
@RunWith(Parameterized.class)
public class NumberUtilCalculateDigitalRootTest {
    public IntIntDataInfo dataInfo;

    @Parameterized.Parameters
    public static Collection<IntIntDataInfo> createData()
    {
        return List.of(
                new IntIntDataInfo(11, 2),
                new IntIntDataInfo(36987, 6),
                new IntIntDataInfo(351, 9),
                new IntIntDataInfo(429, 6),
                new IntIntDataInfo(58, 4),
                new IntIntDataInfo(927424, 1),
                new IntIntDataInfo(36, 9));
    }

    public NumberUtilCalculateDigitalRootTest(IntIntDataInfo dataInfo)
    {
        this.dataInfo = dataInfo;
    }

    @Test
    public void givenValue_WhenIndex_ThenReturnsNumber()
    {
        Assert.assertEquals(dataInfo.expected, NumberUtil.calculateDigitalRoot(dataInfo.input));
    }
}