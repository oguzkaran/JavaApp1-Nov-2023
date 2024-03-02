package org.csystem.range;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Ignore("Tested before")
public class IntToLongRangeTest {
    @Test
    public void givenValues_whenBounds_thenIterate()
    {
        var a = 10;
        var b = 50;
        var index = 0;

        for (long val : IntToLongRange.of(a, b)) {
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

        for (long val : IntToLongRange.of(a, b, step)) {
            System.out.printf("%d ", val);
            assertEquals(a + index++ * (long)step, val);
        }

        System.out.println();
    }

    @Test
    public void givenValues_whenBoundsAndOperator_thenIterate()
    {
        var a = 10;
        var b = 500_000;
        var index = 0;
        var step = 1000;

        for (long val : IntToLongRange.of(a, b, val -> val + step)) {
            System.out.printf("%d ", val);
            assertEquals(a + index++ * (long)step, val);
        }

        System.out.println();
    }

    @Test
    public void givenValues_whenBoundsAndOperatorWithMultiply_thenIterate()
    {
        var a = 10;
        var b = 500_000;
        var value = 3;

        for (long val : IntToLongRange.of(a, b, val -> val * value))
            System.out.printf("%d ", val);

        System.out.println();
    }

    @Test
    public void givenValues_whenBounds_thenThrowsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> IntToLongRange.of(5, 4));
    }

    @Test
    public void givenValues_whenBoundsAndStep_thenThrowsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> IntToLongRange.of(5, 4, 0));
    }
}
