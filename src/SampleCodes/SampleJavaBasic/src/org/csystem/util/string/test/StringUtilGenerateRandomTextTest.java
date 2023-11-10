package org.csystem.util.string.test;

import org.csystem.util.string.StringUtil;

import java.util.Random;
import java.util.Scanner;

public class StringUtilGenerateRandomTextTest {
	public static void run()
	{
		Scanner kb = new Scanner(System.in);
		Random random = new Random();
		
		while (true) {
			System.out.print("Bir sayı giriniz:");
			int count = Integer.parseInt(kb.nextLine());
			
			if (count <= 0)
				break;
			
			System.out.printf("Üretilen Yazı:%s%n", StringUtil.generateRandomTextTR(random, count));
			System.out.printf("Generated Text:%s%n", StringUtil.generateRandomTextEN(random, count));
		}		
	}

	public static void main(String[] args)
	{
		run();
	}
}