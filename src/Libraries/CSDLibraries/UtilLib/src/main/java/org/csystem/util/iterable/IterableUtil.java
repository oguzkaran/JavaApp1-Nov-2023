/*-------------------------------------------------------------
	FILE		: IterableUtil.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 21st April 2024

	Utility class for iterable operations

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.util.iterable;

public final class IterableUtil {
    private IterableUtil()
    {
    }

    public static <T> boolean areAllUnique(Iterable<? extends T> iterable)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> Iterable<T> except(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> boolean isEmpty(Iterable<? extends T> iterable)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> boolean isNotEmpty(Iterable<? extends T> iterable)
    {
        return !isEmpty(iterable);
    }

    public static <T> Iterable<T> intersect(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> Iterable<T> union(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <T> Iterable<T> unionAll(Iterable<? extends T> a, Iterable<? extends T> b)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
