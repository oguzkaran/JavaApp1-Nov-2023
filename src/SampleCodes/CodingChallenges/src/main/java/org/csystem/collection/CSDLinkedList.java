/*----------------------------------------------------------------
	FILE		: CSDLinkedList.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 16th Mar 2024

	CSDLinkedList class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CSDLinkedList<E> implements List<E> {
    private Node<E> m_head, m_tail;
    private int m_size;

    private static class Node<E> {
        E e;
        Node<E> next;
        Node<E> prev;

        public Node(E e)
        {
            this.e = e;
        }
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean contains(Object o)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Iterator<E> iterator()
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException("Unsupported!...");
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        throw new UnsupportedOperationException("Unsupported!...");
    }

    @Override
    public boolean add(E e)
    {

        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean remove(Object o)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Unsupported!...");
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public E get(int index)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public E set(int index, E element)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void add(int index, E element)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public E remove(int index)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int indexOf(Object o)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int lastIndexOf(Object o)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public ListIterator<E> listIterator()
    {
        throw new UnsupportedOperationException("Unsupported!...");
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        throw new UnsupportedOperationException("Unsupported!...");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException("TODO");
    }
}
