package org.csystem.util.string;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class StringUtilPadLeadingNullPointerExceptionTest
{
    @Test
    public void testForAddedDefaultChar()
    {
        assertThrows(NullPointerException.class, () -> StringUtil.padLeading(null, 2));
    }

    @Test
    public void testForAddedUniqueChar()
    {
        assertThrows(NullPointerException.class, () -> StringUtil.padLeading(null, 2, '#'));
    }
}
