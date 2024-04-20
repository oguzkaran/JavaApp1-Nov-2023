package org.csystem.random.lottery;

import java.util.TreeSet;
import java.util.random.RandomGenerator;

public class NumericLottery {
    private final RandomGenerator m_randomGenerator;

    public NumericLottery(RandomGenerator randomGenerator)
    {
        m_randomGenerator = randomGenerator;
    }

    public int [] getNumbers()
    {
        var numbers = new int [6];
        var set = new TreeSet<Integer>();

        while (set.size() != 6)
            set.add(m_randomGenerator.nextInt(1, 50));

        var i = 0;

        for (var val : set)
            numbers[i++] = val;

        return numbers;
    }

    public int [][] getNumbers(int count)
    {
        int [][] numbers = new int[count][];

        for (int i = 0; i < count; ++i)
            numbers[i] = getNumbers();

        return numbers;
    }
}
