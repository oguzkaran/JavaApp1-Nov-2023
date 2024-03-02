package org.csystem.range;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntRangeTest {
    @Test
    public void givenValues_whenBounds_thenIterate()
    {
        var a = 10;
        var b = 50;
        var index = 0;

        for (int val : IntRange.of(a, b)) {
            System.out.printf("%d ", val);
            assertEquals(a + index++, val);
        }

        System.out.println();
    }

    @Test
    public void givenValues_whenBoundsAndStep_thenIterate()
    {
        var a = 10;
        var b = 50;
        var index = 0;
        var step = 3;

        for (int val : IntRange.of(a, b, step)) {
            System.out.printf("%d ", val);
            assertEquals(a + index++ * step, val);
        }

        System.out.println();
    }
}
