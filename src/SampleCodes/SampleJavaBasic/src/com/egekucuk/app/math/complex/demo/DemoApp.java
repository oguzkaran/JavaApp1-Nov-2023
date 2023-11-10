package com.egekucuk.app.math.complex.demo;

import com.egekucuk.math.factory.RandomComplexFactory;
import org.csystem.math.Complex;

import java.util.ArrayList;
import java.util.Random;

public class DemoApp {
    public static void run()
    {
        Random r = new Random();
        RandomComplexFactory factory = new RandomComplexFactory(r);
        ArrayList list = factory.createComplexListUntil(new Complex(), -10, 11);
        Complex z = new Complex(r.nextInt(-10, 10), r.nextInt(-10, 10));
        int index = list.indexOf(z);

        for (Object o : list)
            System.out.println(o);

        System.out.println("-----------------------------------------");

        System.out.printf("%s%s%n", z, index != -1 ? " found at " + index : " not found");
    }

    public static void main(String[] args)
    {
        run();
    }
}
