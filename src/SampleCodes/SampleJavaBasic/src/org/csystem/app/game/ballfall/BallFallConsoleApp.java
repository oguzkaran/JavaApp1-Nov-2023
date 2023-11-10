package org.csystem.app.game.ballfall;

import org.csystem.game.ballfall.BallFall;

import java.util.Scanner;

public class BallFallConsoleApp {
	public static void run()
	{
		Scanner kb = new Scanner(System.in);
		BallFall ballFall = new BallFall();

		while (true) {
			System.out.print("Input width and height:");
			int width = kb.nextInt();
			int height = kb.nextInt();

			if (width == 0 || height == 0)
				break;
			
			ballFall.play(width, height);

			System.out.println(ballFall.getPattern());
		}	
	}

	public static void main(String[] args)
	{
		run();
	}
}
