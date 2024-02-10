/*----------------------------------------------------------------------------------------------------------------------
    MR çeşitleri:
    1. static method reference:
    2. reference to an instance method of particular object:
    3. reference to an instance method of any object of a particular type:
    4. ctor reference:

    Aşağıdaki demo örnekte MR kullanımına odaklınınız
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.numeric.NumberUtil;

import java.util.Random;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();

        IIntBinaryOperator ibo = IntOperation::multiply; //1 -> Lambda karşılığı: (a, b) -> IntOperation.multiply(a, b);

        Console.writeLine(ibo.applyAsInt(10, 20));
        Console.writeLine("------------------------------------------------------------------");

        ibo = Integer::sum; //1
        Console.writeLine(ibo.applyAsInt(10, 20));
        Console.writeLine("------------------------------------------------------------------");

        IIntPredicate intPredicate = NumberUtil::isPrime; //1 -> Lambda karşılığı: a -> NumberUtil.isPrime(a);
        Console.writeLine(intPredicate.test(1_000_003) ? "Asal" : "Asal değil");
        Console.writeLine("------------------------------------------------------------------");

        IIntSupplier intSupplier = random::nextInt; //2 -> Lambda karşılığı:() -> random.nextInt();
        Console.writeLine(intSupplier.get());
        Console.writeLine("------------------------------------------------------------------");

        IIntRandomBoundSupplier intRandomBoundSupplier = Random::nextInt; //3 -> Lambda karşılığı: (r, b) -> r.nextInt(b);
        Console.writeLine(intRandomBoundSupplier.get(random, 100));
        Console.writeLine("------------------------------------------------------------------");

        IStringToIntConverter converter = String::length; //3 -> Lambda karşılığı: s -> s.length();
        Console.writeLine(converter.convert("ankara"));
        Console.writeLine("------------------------------------------------------------------");

        IIntValueFactory intValueFactory = IntValue::new; //4 -> Lambda karşılığı: a -> new IntValue(a);
        Console.writeLine(intValueFactory.create(10).getValue());
        Console.writeLine("------------------------------------------------------------------");

        IIntValueDefaultFactory intValueDefaultFactory = IntValue::new; //4 -> Lambda karşılığı: () -> new IntValue();
        Console.writeLine(intValueDefaultFactory.create().getValue());
        Console.writeLine("------------------------------------------------------------------");
    }
}

class IntOperation {
    public static int multiply(int a, int b)
    {
        return a * b;
    }
}

class IntValue {
    private int m_value;

    public IntValue()
    {
    }

    public IntValue(int value)
    {
        m_value = value;
    }

    public int getValue()
    {
        return m_value;
    }

    //...
}

interface IIntValueFactory {
    IntValue create(int val);
}

interface IIntValueDefaultFactory {
    IntValue create();
}

interface IIntBinaryOperator {
    int applyAsInt(int a, int b);
}

interface IIntPredicate {
    boolean test(int a);
}

interface IIntSupplier {
    int get();
}

interface IIntRandomBoundSupplier {
    int get(Random random, int bound);
}

interface IStringToIntConverter {
    int convert(String str);
}

