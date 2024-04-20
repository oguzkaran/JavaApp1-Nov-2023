/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Parametresi ile aldığı bir yazının tüm karakterlerinin farklı olup olmadığını test eden areAllUnique
    isimli metodu UtilLib içerisindeki StringUtil sınıfına ekleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.app.fraction.ComplexFactory;
import org.csystem.math.Complex;

import java.util.HashSet;

class Application {
    public static void run(String[] args)
    {
        var factory = new ComplexFactory();
        var set = new HashSet<Complex>();

        while (set.add(factory.createRandomComplexNumber(-10, 10)))
            ;

        set.forEach(Console::writeLine);
    }
}
