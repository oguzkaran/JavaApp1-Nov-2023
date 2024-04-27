package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.bank.Customer;
import org.csystem.app.bank.factory.CustomerFactory;
import org.csystem.scheduler.CountDownScheduler;

import java.util.PriorityQueue;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(1, args.length, "wrong number of arguments");
        try {
            var factory = new CustomerFactory();
            var priorityQueue = new PriorityQueue<Customer>();
            var millisInFuture = Long.parseLong(args[0]);

            new CountDownScheduler(millisInFuture, 1000) {
                @Override
                public void onTick(long remainingMilliseconds)
                {
                    var customer = factory.createCustomer();

                    Console.writeLine(customer);

                    priorityQueue.add(customer);
                }

                @Override
                public void onFinish()
                {
                    Customer c;

                    Console.writeLine("----------------------------------------");
                    Console.writeLine("Order of Customers:");
                    while ((c = priorityQueue.poll()) != null)
                        Console.writeLine(c);

                    Console.writeLine("----------------------------------------");
                }
            }.start();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid interval value!...");
        }
    }
}
