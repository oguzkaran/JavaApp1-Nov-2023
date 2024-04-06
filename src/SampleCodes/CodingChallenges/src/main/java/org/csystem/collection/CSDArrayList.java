/*----------------------------------------------------------------
	FILE		: CSDArrayList.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 6th Apr 2024

	CSDArrayList class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import java.util.*;
import java.util.function.IntFunction;

public class CSDArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E [] m_elements;
    private int m_index;

    private static void doForIllegalArgument(String message)
    {
        throw new IllegalArgumentException(message);
    }

    private static void doForIndexOutOfBounds(String message)
    {
        throw new IndexOutOfBoundsException(message);
    }

    private void checkCapacity(int capacity)
    {
        if (capacity < 0)
            doForIllegalArgument("Capacity can not be negative:" + capacity);
    }

    private void checkIndex(int index)
    {
        if (index < 0 || index >= m_index)
            doForIndexOutOfBounds("Index out of bounds:" + index);
    }

    private void changeCapacity(int capacity)
    {
        m_elements = Arrays.copyOf(m_elements, capacity);
    }

    private void enlargeCapacityIfNecessary()
    {
        if (m_index == m_elements.length)
            changeCapacity(m_elements.length == 0 ? 1 : m_elements.length * 2);
    }

    @SuppressWarnings("unchecked")
    public CSDArrayList()
    {
        m_elements = (E[])new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public CSDArrayList(int initialCapacity)
    {
        checkCapacity(initialCapacity);
        m_elements = (E[])new Object[initialCapacity];
    }

    @Override
    public boolean add(E e)
    {
        enlargeCapacityIfNecessary();
        m_elements[m_index++] = e;

        return true;
    }

    @Override
    public void add(int index, E e)
    {
        enlargeCapacityIfNecessary();
        for (int i = m_index++; i > index; --i)
            m_elements[i] = m_elements[i - 1];
        m_elements[index] = e;
    }

    public int capacity()
    {
        return m_elements.length;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < m_index; ++i)
            m_elements[i] = null;

        m_index = 0;
    }

    public void ensureCapacity(int minCapacity)
    {
        if (minCapacity > m_elements.length)
            changeCapacity(Math.max(m_elements.length * 2, minCapacity));
    }

    @Override
    public E get(int index)
    {
        checkIndex(index);

        return m_elements[index];
    }

    @Override
    public E remove(int index)
    {
        checkIndex(index);
        E old = m_elements[index];

        for (int i = index; i < m_index - 1; ++i)
            m_elements[i] = m_elements[i + 1];

        m_elements[--m_index] = null;

        return old;
    }

    @Override
    public int indexOf(Object o)
    {
        for (var i = 0; i < m_index; ++i)
            if (Objects.equals(o, m_elements[i]))
                return i;

        return -1;
    }

    @Override
    public int lastIndexOf(Object o)
    {
        for (var i = m_index - 1; i >= 0; --i)
            if (Objects.equals(o, m_elements[i]))
                return i;

        return -1;
    }

    @Override
    public ListIterator<E> listIterator()
    {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public E set(int index, E e)
    {
        checkIndex(index);
        E old  = m_elements[index];

        m_elements[index] = e;

        return old;
    }

    @Override
    public int size()
    {
        return m_index;
    }

    @Override
    public boolean isEmpty()
    {
        return m_index == 0;
    }

    @Override
    public boolean contains(Object o)
    {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<>() {
            int index;

            @Override
            public boolean hasNext()
            {
                return index < m_index;
            }

            @Override
            public E next()
            {
                if (!hasNext())
                    throw new NoSuchElementException("No element in list");

                return m_elements[index++];
            }
        };
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException("Unsupported!..:");
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        throw new UnsupportedOperationException("Unsupported!..:");
    }

    public void trimToSize()
    {
        if (m_index != m_elements.length)
             changeCapacity(m_index);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < m_index; ++i)
            sb.append(m_elements[i]).append(", ");

        return (m_index != 0 ? sb.substring(0, sb.length() - 2) : sb.toString()) + "]" ;
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator)
    {
        throw new UnsupportedOperationException("Unsupported!..:");
    }

    @Override
    public boolean remove(Object o)
    {
        var index = indexOf(o);

        remove(index);
        return index != -1;
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        for (var e : c)
            if (!contains(e))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        for (var e : c)
            add(e);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        for (var e : c)
            add(index, e);

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        boolean result = false;

        for (var e : c)
            if (remove(e))
                result = true;

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException("Unsupported!..:");
    }
}
