package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class CSDArrayListStackPeekThrowsExceptionTest {
    @Test
    public void test()
    {
        var stack = new CSDArrayListStack<>();

        Assert.assertThrows(EmptyStackException.class, stack::peek);
    }
}