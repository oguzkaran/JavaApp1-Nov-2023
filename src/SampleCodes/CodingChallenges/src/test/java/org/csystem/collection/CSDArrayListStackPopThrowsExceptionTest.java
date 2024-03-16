package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class CSDArrayListStackPopThrowsExceptionTest {
    @Test
    public void test()
    {
        var stack = new CSDArrayListStack<>();

        Assert.assertThrows(EmptyStackException.class, stack::pop);
    }
}