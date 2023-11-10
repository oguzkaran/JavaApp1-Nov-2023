package org.csystem.game.ballfall;

public final class BallFall {
	private String m_pattern;

	private static void fillSpace(int begin, int end, StringBuilder sb)
	{
		for (int i = begin; i < end; ++i)
			sb.append(' ');
	}
	
	private static void fillBall(int ballIndex, int end, StringBuilder sb)
	{
		fillSpace(0, ballIndex, sb);
		sb.append('*');
		fillSpace(ballIndex + 1, end, sb);
	}

	private static boolean updateRightFlag(boolean right, int ballIndex, int width)
	{
		if (ballIndex == 0)
			right = true;
		else if (ballIndex == width - 1)
			right = false;
			
		return right;
	}

	private static int updateBallIndex(int ballIndex, boolean right)
	{
		if (right)
			return ballIndex + 1;
		
		return ballIndex - 1;
	}


	public String getPattern()
	{
		return m_pattern;
	}

	public void play(int width, int height)
	{
		boolean right = false;
		int ballIndex = 0;

		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= height; ++i) {
			sb.append('|');
			fillBall(ballIndex, width, sb);
			if (width != 1) {
				right = updateRightFlag(right, ballIndex, width);
				ballIndex = updateBallIndex(ballIndex, right);
			}
			sb.append("|\r\n");
		}

		m_pattern = sb.toString();
	}
}
