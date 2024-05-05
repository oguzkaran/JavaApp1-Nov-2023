package org.csystem.util.string;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

@Ignore("Written by Bekir Kocadağ")
public class StringUtilPadLeadingNullPointerExceptionForDefaultChTest
{
    @Test
    public void test()
    {
        assertThrows(NullPointerException.class, () -> StringUtil.padLeading(null, 2));
    }
}
