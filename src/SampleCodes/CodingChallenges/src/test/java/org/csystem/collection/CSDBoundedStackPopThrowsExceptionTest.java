package org.csystem.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class CSDBoundedStackPopThrowsExceptionTest {
    @Test
    public void test()
    {
        var stack = new CSDBoundedStack<>(10);

        Assert.assertThrows(EmptyStackException.class, stack::pop);
    }
}