/*----------------------------------------------------------------
	FILE		: CSDArrayListStack.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 30th Mar 2024

	CSDArrayListStack class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class CSDStack<E> {
    private final ArrayList<E> m_elems = new ArrayList<>();

    public boolean empty()
    {
        return m_elems.isEmpty();
    }

    public E push(E e)
    {
        m_elems.add(e);

        return e;
    }

    public E pop()
    {
        if (empty())
            throw new EmptyStackException();

        return m_elems.remove(m_elems.size() - 1);
    }

    public E peek()
    {
        if (empty())
            throw new EmptyStackException();

        return m_elems.get(m_elems.size() - 1);
    }

    public int search(E e)
    {
        var index = m_elems.indexOf(e);

        return index != -1 ? m_elems.size() - index : -1;
    }
}
