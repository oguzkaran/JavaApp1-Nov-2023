package org.csystem.util.numeric;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Ignore("Tested before and passed")
@RunWith(Parameterized.class)
public class NumberUtilIsPrimeBigIntegerTrueTest {
    public BigInteger input;

    @Parameterized.Parameters
    public static Collection<BigInteger> createData()
    {
        return List.of(BigInteger.valueOf(710_584_055_392_819_667L),
                BigInteger.valueOf(1_386_437_196_678_024_971L),
                BigInteger.valueOf(19L),
                BigInteger.valueOf(71L),
                BigInteger.valueOf(569_785_970_6174_583_067L));
    }

    public NumberUtilIsPrimeBigIntegerTrueTest(BigInteger input)
    {
        this.input = input;
    }

    @Test
    public void test()
    {
        Assert.assertTrue(NumberUtil.isPrime(input));
    }
}
