/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Aşağıdaki geri sayım işlemini yapaan CountDownTimer isimli sınıfı public bölümünü değiştiemeden
    SchedulerLib içerisinde yazınız
        package org.csystem.scheduler;

        public abstract class CountDownTimer {
            protected CountDownTimer(long millisInFuture, long countDownInterval)
            {

            }

            public abstract void onTick(long remainingMilliseconds);
            public abstract void onFinish();

            public final void start()
            {

            }

            public final void cancel()
            {

            }
        }


    Açıklamalar:
    - Sınıfı ctor'un parametreleri ile aldığı değerelere (milisaniye cinsinden) göre geriye sayım işleminde her adımda
    onTick metodunu kalan milisaniye sayısını argüman olarak geçecek şekilde çağıracaktır. Geri sayım tamamlandığında
    onFinish metodu çağrılacaktır. Örnek bir kullanım şu şekilde olavilir
        new CountDownTimer(1000, 10000) {
            public void onTick(long ms)
            {
                //Her saniyede bir çağrılacak ve kalan zaman (milisaniye cinsinden) argüman olarak geçilmiş olacak
            }

            public void onFinish()
            {
                //10 saniye sonunda yani geri sayım tamamlandığında çağrılacak
            }
        }.start();

    - Sınıfı Timer sınıfını kullanarak yazınız
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

class Application {
    public static void run(String[] args)
    {
        var timer = new Timer();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        timer.scheduleAtFixedRate(new DateTimeTask(formatter), 0, 1000);
    }
}

class DateTimeTask extends TimerTask {
    private final DateTimeFormatter m_dateTimeFormatter;

    public DateTimeTask(DateTimeFormatter dateTimeFormatter)
    {
        m_dateTimeFormatter = dateTimeFormatter;
    }

    public void run()
    {
        Console.write("%s\r", m_dateTimeFormatter.format(LocalDateTime.now()));
    }
}

