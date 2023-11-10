package com.egekucuk.math.geometry.test;

import com.egekucuk.math.geometry.AnalyticalCircle;

import java.util.Random;

public class AnalyticalCircleIsTangentTest {
    public static void run()
    {
        AnalyticalCircle ac1 = new AnalyticalCircle(3, 100, 200);
        AnalyticalCircle ac2 = new AnalyticalCircle(2, 104, 197);

        System.out.println(ac1.isTangent(ac2) ? "Teğet" : "Teğet değil");
    }

    public static void main(String[] args)
    {
        run();
    }
}
