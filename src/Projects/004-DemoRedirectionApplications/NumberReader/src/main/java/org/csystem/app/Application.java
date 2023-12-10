package org.csystem.app;

import java.util.Scanner;

class Application {
    public static void run(String[] args)
    {
        Scanner input = new Scanner(System.in);

        var sum = 0;
        for (var i = 0;i <  10; ++i) {
            var val = Integer.parseInt(input.nextLine());
            System.out.printf("%d ", val);

            sum += val;
        }

        System.out.printf("Sum:%d%n", sum);
    }
}
