/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örnekte Treeset<E> collection'ı içerisindeki elemanlar ArrayList<E> collectiın'ına addAll metoduyla
    eklenmiştir. TreeSet<E> collection sınıfı ileride ele alınacaktır. Demo örnekte collection'lar arasındaki veri transferinib
    polimorfik olduğuna odaklanınız
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

class Application {
    public static void run(String[] args)
    {
        var random = new Random();
        var numberSet = new TreeSet<Integer>();
        var numberList = new ArrayList<>();

        while (true) {
            int val = random.nextInt(-100, 100);
            if (val == 0)
                break;

            numberSet.add(val);
        }

        numberList.add(1000);
        numberList.addAll(numberSet);

        numberList.forEach(val -> Console.write("%d ", val));
        Console.writeLine();
        numberSet.forEach(val -> Console.write("%d ", val));
    }
}
