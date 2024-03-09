/*-------------------------------------------------------------
	FILE		: StringGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator.string;

import org.csystem.generator.NValueGenerator;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StringGenerator extends NValueGenerator<String> {
    public StringGenerator(Supplier<String> supplier, int count)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    public Optional<String> findFirst(Predicate<String> predicate)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
