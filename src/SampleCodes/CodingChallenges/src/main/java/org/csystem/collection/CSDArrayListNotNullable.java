/*----------------------------------------------------------------
	FILE		: CSDArrayListNotNullable.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 17th Mar 2024

	CSDArrayListNotNullable class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CSDArrayListNotNullable<E> extends ArrayList<E> {
    private void checkAllNotNull(Collection<? extends E> c)
    {
        c.forEach(this::checkNotNull);
    }

    private void checkNotNull(E element)
    {
        if (element == null)
            throw new IllegalArgumentException("Argument can be null");
    }

    public CSDArrayListNotNullable(int initialCapacity)
    {
        super(initialCapacity);
    }

    public CSDArrayListNotNullable()
    {}

    public CSDArrayListNotNullable(Collection<? extends E> c)
    {
        super(c);
        addAll(c);
    }

    @Override
    public boolean add(E element)
    {
        checkNotNull(element);

        return super.add(element);
    }

    @Override
    public void add(int index, E element)
    {
        checkNotNull(element);

        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        checkAllNotNull(c);

        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        checkAllNotNull(c);
        return super.addAll(index, c);
    }

    @Override
    public E set(int index, E element)
    {
        checkNotNull(element);

        return super.set(index, element);
    }
}
