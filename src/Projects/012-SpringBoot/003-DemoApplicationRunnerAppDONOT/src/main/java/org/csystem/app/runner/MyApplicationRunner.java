package org.csystem.app.runner;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args)
    {
        while (true) {
            var a = Console.readInt("Input a number:");

            if (a <= 0)
                break;

            Console.writeLine(NumberUtil.isPrime(a) ? "Prime number" : "No a prime number");
        }
    }
}
