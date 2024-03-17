package org.csystem.collection;

import java.util.ArrayList;
import java.util.Collection;

public class CSDArrayListNotNullable<E> extends ArrayList<E> {
    //...

    public CSDArrayListNotNullable(int initialCapacity)
    {
        super(initialCapacity);
    }

    public CSDArrayListNotNullable()
    {}

    public CSDArrayListNotNullable(Collection<? extends E> c)
    {
        super(c);
        //...
    }

    @Override
    public boolean add(E e)
    {
        //...
        return super.add(e);
    }

    @Override
    public void add(int index, E element)
    {
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        return super.addAll(index, c);
    }

    @Override
    public E set(int index, E element)
    {
        return super.set(index, element);
    }
}
