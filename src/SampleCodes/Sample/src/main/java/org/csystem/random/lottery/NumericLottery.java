package org.csystem.random.lottery;

import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class NumericLottery {
    private final RandomGenerator m_randomGenerator;

    public NumericLottery(RandomGenerator randomGenerator)
    {
        m_randomGenerator = randomGenerator;
    }

    public int [] getNumbers()
    {
        return IntStream.generate(() -> m_randomGenerator.nextInt(1, 50)).distinct().limit(6).sorted().toArray();
    }

    public int [][] getNumbers(int count)
    {
        int [][] numbers = new int[count][];

        IntStream.range(0, count).forEach(i -> numbers[i] = getNumbers());

        return numbers;
    }
}
