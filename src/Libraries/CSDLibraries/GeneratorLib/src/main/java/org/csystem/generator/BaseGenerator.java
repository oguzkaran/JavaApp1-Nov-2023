/*-------------------------------------------------------------
	FILE		: BaseGenerator.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Mar 2024

	Generic iterable generator class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.generator;

import java.util.function.Supplier;

public abstract class BaseGenerator<T> implements Iterable<T> {
    protected Supplier<T> supplier;
    protected String noSuchElementExceptionMessage;

    protected BaseGenerator()
    {
    }
}
