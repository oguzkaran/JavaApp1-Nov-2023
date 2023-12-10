package org.csystem.app;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();

        for (var i = 0; i < 10; ++i)
            System.out.println(random.nextInt(100));
    }
}
