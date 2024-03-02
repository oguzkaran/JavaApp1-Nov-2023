package org.csystem.range;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore("Tested before")
public class RandomIntGeneratorTest {
    @Test
    public void givenValues_whenOriginBoundCountAndRandomGenerator_thenIterate()
    {
        var count = 1_000_000;
        var origin = 0;
        var bound = 100;
        var a = new int[count];
        var index = 0;

        for (int val : RandomIntGenerator.of(new Random(), origin, bound, count))
            a[index++] = val;

        assertEquals(count, a.length);
        assertTrue(Arrays.stream(a).allMatch(val ->  origin <= val && val < bound));
    }
}
