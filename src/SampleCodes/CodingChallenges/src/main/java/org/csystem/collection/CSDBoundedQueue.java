/*----------------------------------------------------------------
	FILE		: CSDBoundedQueue.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 30th Mar 2024

	CSDBoundedQueue class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import org.csystem.collection.exception.FullQueueException;

import java.util.ArrayDeque;

public class CSDBoundedQueue<E> {
    private final ArrayDeque<E> m_elems;
    private final int m_size;

    public CSDBoundedQueue(int n)
    {
        m_elems = new ArrayDeque<>(m_size = n);
    }

    public boolean empty()
    {
        return m_elems.isEmpty();
    }

    public void add(E e)
    {
        if (m_elems.size() == m_size)
            throw new FullQueueException();

        m_elems.addLast(e);
    }

    public boolean offer(E e)
    {
        if (m_elems.size() == m_size)
            return false;

        m_elems.addLast(e);

        return true;
    }
    public E remove()
    {
        return m_elems.removeFirst();
    }

    public E poll()
    {
        return m_elems.pollFirst();
    }

    public E element()
    {
        return m_elems.getFirst();
    }

    public E peek()
    {
        return m_elems.peekFirst();
    }
}
