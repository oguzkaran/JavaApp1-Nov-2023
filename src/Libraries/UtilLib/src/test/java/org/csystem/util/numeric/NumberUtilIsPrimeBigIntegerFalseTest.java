package org.csystem.util.numeric;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class NumberUtilIsPrimeBigIntegerFalseTest {
    public BigInteger input;

    @Parameterized.Parameters
    public static Collection<BigInteger> createData()
    {
        return List.of(BigInteger.valueOf(-1),
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.valueOf(12),
                BigInteger.valueOf(1_000_002));
    }

    public NumberUtilIsPrimeBigIntegerFalseTest(BigInteger input)
    {
        this.input = input;
    }

    @Test
    public void test()
    {
        Assert.assertFalse(NumberUtil.isPrime(input));
    }
}
