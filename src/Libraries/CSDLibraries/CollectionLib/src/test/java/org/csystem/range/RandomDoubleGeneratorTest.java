package org.csystem.range;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore("Tested before")
public class RandomDoubleGeneratorTest {
    @Test
    public void givenValues_whenOriginBoundCountAndRandomGenerator_thenIterate()
    {
        var count = 1_000_000;
        var origin = 2.3456;
        var bound = 2.345601;
        var a = new double[count];
        var index = 0;

        for (double val : RandomDoubleGenerator.of(new Random(), origin, bound, count))
            a[index++] = val;

        assertEquals(count, a.length);
        assertTrue(Arrays.stream(a).allMatch(val ->  origin <= val && val < bound));
    }
}
