/*----------------------------------------------------------------
	FILE		: CSDArrayListStack.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 16th Mar 2024

	CSDArrayListStack class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class CSDArrayListStack<E> extends ArrayList<E> {
    public boolean empty()
    {
        return isEmpty();
    }

    public E push(E e)
    {
        add(e);

        return e;
    }

    public E pop()
    {
        if (empty())
            throw new EmptyStackException();

        return remove(size() - 1);
    }

    public E peek()
    {
        if (empty())
            throw new EmptyStackException();

        return get(size() - 1);
    }

    public int search(E e)
    {
        var index = indexOf(e);

        return index != -1 ? size() - index : index;
    }
}
