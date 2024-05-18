/*-------------------------------------------------------------
	FILE		: CollectionUtil.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 18th May 2024

	Utility class for collection operations

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public final class CollectionUtil {
    private CollectionUtil()
    {
    }

    public static <T> boolean areAllUnique(Collection<? extends T> collection)
    {
        return new HashSet<>(collection).size() == collection.size();
    }

    public static <T> Collection<T> except(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> boolean isEmpty(Collection<? extends T> collection)
    {
        return collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<? extends T> collection)
    {
        return !isEmpty(collection);
    }

    public static <T> Collection<T> intersect(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> Collection<T> union(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> Collection<T> unionAll(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> List<T> toModifiableList(Stream<T> stream)
    {
        return toModifiableList(stream.toList());
    }

    public static <T> List<T> toModifiableList(List<T> list)
    {
        return new ArrayList<>(list);
    }
}
