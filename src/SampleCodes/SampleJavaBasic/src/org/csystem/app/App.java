/*-----------------------------------------------------------------------------------------------------------------------
	Generic sınıflarda türetme durumu:
-----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

class App {
	public static void main(String [] args)
	{

	}
}

class B implements IX<String, Integer> {
	public void foo(String s, Integer i)
	{
		//...
	}
}

class A implements IX<Integer, String> {
	public void foo(Integer i, String s)
	{
		//...
	}
}

interface IX<T, K> {
	void foo(T t, K k);
}
