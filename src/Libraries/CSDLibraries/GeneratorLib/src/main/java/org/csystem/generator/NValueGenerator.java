/*-------------------------------------------------------------
	FILE		: ValueGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class NValueGenerator<T> extends BaseGenerator<T> {
    protected Supplier<T> valueSupplier;
    protected String noSuchElementExceptionMessage;
    protected long count;

    protected NValueGenerator()
    {
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<>() {
            int count;

            @Override
            public boolean hasNext()
            {
                return count < NValueGenerator.this.count;
            }

            @Override
            public T next()
            {
                if (!hasNext())
                    throw new NoSuchElementException(noSuchElementExceptionMessage);

                ++count;

                return valueSupplier.get();
            }
        };
    }
}
