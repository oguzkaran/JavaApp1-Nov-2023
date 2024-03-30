/*----------------------------------------------------------------
	FILE		: CSDBoundedStack.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 30th Mar 2024

	CSDBoundedStack class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import org.csystem.collection.exception.FullStackException;

import java.util.EmptyStackException;
import java.util.Objects;

public class CSDBoundedStack<E> {
    private static final boolean DEBUG = false;
    private final E [] m_elems;
    private int m_top;

    @SuppressWarnings("unchecked")
    public CSDBoundedStack(int n)
    {
        m_elems = (E[])new Object[n];
    }

    public boolean empty()
    {
        return m_top == 0;
    }

    public E push(E e)
    {
        if (DEBUG)
            assert m_top <= m_elems.length: "Invalid modification for top index";

        if (m_top == m_elems.length)
            throw new FullStackException();

        m_elems[m_top++] = e;

        return e;
    }

    public E pop()
    {
        if (empty())
            throw new EmptyStackException();

        var e = m_elems[--m_top];

        m_elems[m_top] = null;

        return e;
    }

    public E peek()
    {
        if (empty())
            throw new EmptyStackException();

        return m_elems[m_top - 1];
    }

    public int search(E e)
    {
        for (var i = m_top - 1; i >= 0; --i)
            if (Objects.equals(e, m_elems[i]))
                return m_top - i;

        return -1;
    }
}
