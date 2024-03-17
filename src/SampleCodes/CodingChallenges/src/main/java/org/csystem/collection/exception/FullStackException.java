/*----------------------------------------------------------------
	FILE		: FullStackException.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	LAST UPDATE	: 17th Mar 2024

	FullStackException class

	Copyleft (c) 1993 C and System Programmers Association
	All Rights Free
----------------------------------------------------------------*/
package org.csystem.collection.exception;

public class FullStackException extends RuntimeException {
    @Override
    public String getMessage()
    {
        return "Stack Full";
    }
}
