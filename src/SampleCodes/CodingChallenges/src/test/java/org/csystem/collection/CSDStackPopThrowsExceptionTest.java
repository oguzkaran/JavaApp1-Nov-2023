package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class CSDStackPopThrowsExceptionTest {
    @Test
    public void test()
    {
        var stack = new CSDStack<>();

        Assert.assertThrows(EmptyStackException.class, stack::pop);
    }
}