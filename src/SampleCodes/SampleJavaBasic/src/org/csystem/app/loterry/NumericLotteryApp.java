package org.csystem.app.loterry;

import org.csystem.random.lottery.NumericLottery;

import java.util.Random;
import java.util.Scanner;
import static org.csystem.util.array.ArrayUtil.print;

public class NumericLotteryApp {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);
        Random random = new Random();
        NumericLottery lottery = new NumericLottery(random);

        while (true) {
            System.out.print("Kaç tane kupon oynamak istersiniz:");
            int n = Integer.parseInt(kb.nextLine());

            if (n <= 0)
                break;

            print(2, lottery.getNumbers(n));
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
