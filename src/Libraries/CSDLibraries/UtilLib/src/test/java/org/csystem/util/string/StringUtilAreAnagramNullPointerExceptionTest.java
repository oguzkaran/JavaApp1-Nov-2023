package org.csystem.util.string;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

@Ignore
public class StringUtilAreAnagramNullPointerExceptionTest {
    @Test
    public void test1()
    {
        assertThrows(NullPointerException.class, () -> StringUtil.areAnagram(null, "ankara"));
    }

    @Test
    public void test2()
    {
        assertThrows(NullPointerException.class, () -> StringUtil.areAnagram("ankara", null));
    }

    @Test
    public void test3()
    {
        assertThrows(NullPointerException.class, () -> StringUtil.areAnagram(null, null));
    }
}
