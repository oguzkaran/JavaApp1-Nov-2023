/*-------------------------------------------------------------
	FILE		: CollectionUtil.java
	AUTHOR		: Java-Mar-2023 Group
	Last UPDATE	: 19th May 2024

	Utility class for collection operations

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.util.collection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    public static <T> List<T> toModifiableList(Stream<? extends T> stream)
    {
        return toModifiableList(stream.toList());
    }

    public static <T> List<T> toModifiableList(List<? extends T> list)
    {
        return new ArrayList<>(list);
    }

    public static <T,K,U> Map<K,U> toModifiableMap(Stream<? extends T> stream,
                                                     Function<? super T,? extends K> keyMapper,
                                                     Function<? super T,? extends U> valueMapper)
    {
        return toModifiableMap(stream.collect(Collectors.toMap(keyMapper, valueMapper)));
    }

    public static <K,V> Map<K,V> toModifiableMap(Map<K, V> map)
    {
        return new HashMap<>(map);
    }

    public static <T> Set<T> toModifiableSet(Stream<? extends T> stream)
    {
        return toModifiableSet(stream.collect(Collectors.toSet()));
    }

    public static <T> Set<T> toModifiableSet(Set<? extends T> set)
    {
        return new HashSet<>(set);
    }

    public static <T> Stream<T> parallelStream(Iterable<T> iterable)
    {
        return stream(iterable, true);
    }

    public static <T> Stream<T> stream(Iterable<T> iterable)
    {
        return stream(iterable, false);
    }

    public static <T> Stream<T> stream(Iterable<T> iterable, boolean parallel)
    {
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }
}
