/*----------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import java.util.Scanner;

class Application {
    public static void run(String[] args)
    {
        Scanner kb = new Scanner(System.in);

        System.out.print("İki sayı giriniz:");
        int a = kb.nextInt();
        int b = kb.nextInt();

        System.out.printf("%d + %d = %d%n", a, b, a + b);

    }
}
