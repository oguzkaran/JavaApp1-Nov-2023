package org.csystem.util.numeric;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtilIsPrimeTest {
    @Test
    public void givenValue_WhenNumber_ThenReturnsTrue()
    {
        int input = 1_000_003;

        Assert.assertTrue(NumberUtil.isPrime(input));
    }

    @Test
    public void givenValue_WhenNumber_ThenReturnsFalse()
    {
        int input = 1233;

        Assert.assertFalse(NumberUtil.isPrime(input));
    }
}
