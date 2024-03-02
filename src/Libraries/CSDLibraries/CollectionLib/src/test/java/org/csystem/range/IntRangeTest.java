package org.csystem.range;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Ignore("Tested before")
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

    @Test
    public void givenValues_whenBoundsAndOperator_thenIterate()
    {
        var a = 10;
        var b = 50;
        var index = 0;
        var step = 2;

        for (int val : IntRange.of(a, b, val -> val + step)) {
            System.out.printf("%d ", val);
            assertEquals(a + index++ * step, val);
        }

        System.out.println();
    }

    @Test
    public void givenValues_whenBoundsAndOperatorWithMultiply_thenIterate()
    {
        var a = 10;
        var b = 500_000;
        var value = 3;

        for (long val : IntRange.of(a, b, val -> val * value))
            System.out.printf("%d ", val);

        System.out.println();
    }

    @Test
    public void givenValues_whenBounds_thenThrowsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> IntRange.of(5, 4));
    }

    @Test
    public void givenValues_whenBoundsAndStep_thenThrowsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> IntRange.of(5, 4, 0));
    }
}
