package org.csystem.generator.range;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Tested before")
public class DoubleRangeTest {
    @Test
    public void givenValues_whenBoundsAndOperator_thenIterate()
    {
        for (double a : DoubleRange.of(-2 * Math.PI, 2 * Math.PI, 0.1))
            System.out.println(Math.sin(a));
    }
}
