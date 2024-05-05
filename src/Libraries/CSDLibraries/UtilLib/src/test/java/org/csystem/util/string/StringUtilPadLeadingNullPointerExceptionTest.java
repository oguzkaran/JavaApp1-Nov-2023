package org.csystem.util.string;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThrows;


@Ignore("Written by Bekir Kocadağ")
public class StringUtilPadLeadingNullPointerExceptionTest {
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
