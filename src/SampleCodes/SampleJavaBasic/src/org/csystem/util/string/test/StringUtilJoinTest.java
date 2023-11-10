package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Random;
import java.util.Scanner;

class StringUtilJoinTest {
	public static void run()
	{
		Scanner kb = new Scanner(System.in);
		Random r = new Random();

		System.out.print("Bir sayı giriniz:");
		int count = kb.nextInt();

		String [] str = new String[count];
		
		for (int i = 0; i < count; ++i)
			str[i] = StringUtil.generateRandomTextTR(r, r.nextInt(5, 11));

		for (int i = 0; i < str.length; ++i)
			System.out.println(str[i]);

		String s = StringUtil.join(str, ", ");

		System.out.println(s);
	}

	public static void main(String[] args)
	{
		run();
	}
}
