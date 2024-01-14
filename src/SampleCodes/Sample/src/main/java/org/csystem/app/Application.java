/*----------------------------------------------------------------------------------------------------------------------
    Java 8 ile eklenen ChronoUnit isimli enum sınfı ile çeşitli birimlerde ölçümler yapılabilmektedir. Bunun için tipik
    olarak between metodu kullanılabilir. Bu enum sınıfın detayları ileride ele alınacaktır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

class Application {
    public static void run(String[] args)
    {
        var earthquakeDatetime = LocalDateTime.of(1999, Month.AUGUST, 17, 3, 2);
        var now = LocalDateTime.now();
        var hours = ChronoUnit.HOURS.between(earthquakeDatetime, now);
        var days = ChronoUnit.DAYS.between(earthquakeDatetime, now);
        var years = ChronoUnit.YEARS.between(earthquakeDatetime, now);
        var totalYears = ChronoUnit.DAYS.between(earthquakeDatetime, now) / 365.;

        Console.writeLine(hours);
        Console.writeLine(days);
        Console.writeLine(years);
        Console.writeLine(totalYears);
    }
}
