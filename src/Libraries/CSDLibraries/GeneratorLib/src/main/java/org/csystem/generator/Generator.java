/*-------------------------------------------------------------
	FILE		: Generator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 9th Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Generator<T> extends BaseGenerator<T> {
    protected UnaryOperator<T> unaryOperator;
    protected Predicate<T> predicate;
    protected Generator()
    {
    }

    public Optional<T> findFirst(Predicate<T> predicate)
    {
        for (var t : this)
            if (predicate.test(t))
                return Optional.of(t);

        return Optional.empty();
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<>() {
            T value = supplier.get();

            @Override
            public boolean hasNext()
            {
                return predicate.test(value);
            }

            @Override
            public T next()
            {
                if (!hasNext())
                    throw new NoSuchElementException(noSuchElementExceptionMessage);

                var result = value;
                value = unaryOperator.apply(value);

                return result;
            }
        };
    }
}
