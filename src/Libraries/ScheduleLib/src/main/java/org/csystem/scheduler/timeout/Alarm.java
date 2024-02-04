/*-------------------------------------------------------------
	FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 4th Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private LocalDateTime m_dateTime;
    private Timer m_timer;
    private boolean m_repeat;

    private TimerTask createTimerTask(Runnable runnable)
    {
        return new TimerTask() {
            public void run()
            {
                if (LocalDateTime.now().isBefore(m_dateTime))
                    return;

                runnable.run();
                m_timer.cancel();
                if (m_repeat) {
                    m_timer = new Timer();
                    m_dateTime = m_dateTime.plusDays(1);
                    m_timer.scheduleAtFixedRate(createTimerTask(runnable), 0, 1000);
                }
            }
        };
    }

    private Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_timer = new Timer();
    }

    public static Alarm of(LocalTime time)
    {
        return of(time.atDate(LocalDate.now()));
    }

    public static Alarm of(LocalTime time, boolean repeat)
    {
        var alarm = of(time.atDate(LocalDate.now()));

        alarm.m_repeat = repeat;

        return alarm;
    }

    public static Alarm of(LocalDateTime dateTime)
    {
        return new Alarm(dateTime);
    }

    public static Alarm of(LocalDate date)
    {
        return of(date.atTime(0, 0));
    }

    public void start(Runnable runnable)
    {
        m_timer.scheduleAtFixedRate(createTimerTask(runnable), 0, 1000);
    }

    public void cancel()
    {
        m_timer.cancel();
    }
}
