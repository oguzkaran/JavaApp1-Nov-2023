package org.csystem.util.numeric;

import org.csystem.util.numeric.data.IntIntDataInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore("Tested by Süeda Şen")
@RunWith(Parameterized.class)
public class NumberUtilReverseTest {
    public IntIntDataInfo dataInfoIntInt;
    public NumberUtilReverseTest(IntIntDataInfo dataInfoIntInt)
    {
        this.dataInfoIntInt = dataInfoIntInt;
    }

    @Parameterized.Parameters
    public static Collection<IntIntDataInfo> createData()
    {
        return List.of(
                new IntIntDataInfo(12345678, 87654321),
                new IntIntDataInfo(546, 645),
                new IntIntDataInfo(1, 1),
                new IntIntDataInfo(234, 432),
                new IntIntDataInfo(398, 893),
                new IntIntDataInfo(55, 55),
                new IntIntDataInfo(324, 423),
                new IntIntDataInfo(78, 87),
                new IntIntDataInfo(325, 523)
        );
    }

    @Test
    public void givenValue_WhenIndex_ThenReturnsReverseOfNumber()
    {
        assertEquals(dataInfoIntInt.expected, NumberUtil.reverse(dataInfoIntInt.input));
    }
}
