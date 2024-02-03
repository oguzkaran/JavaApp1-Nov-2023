package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private final LocalDateTime m_dateTime;
    private final Timer m_timer;

    private TimerTask createTimerTask(TimerTask timerTask)
    {
        return new TimerTask() {
            public void run()
            {
                //...
                timerTask.run();
            }
        };
    }

    public Alarm(LocalTime time)
    {
        this(time.atDate(LocalDate.now()));
    }

    public Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public Alarm(LocalDate date)
    {
        this(date.atTime(0, 0));
    }

    public void start(TimerTask task)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(task), 0, 1000);
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
