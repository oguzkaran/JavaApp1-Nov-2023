/*-------------------------------------------------------------
	FILE		: Alarm.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 10th Feb 2024

	Alarm class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler.timeout;

import org.csystem.scheduler.Scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimerTask;

public class Alarm {
    private LocalDateTime m_dateTime;
    private Scheduler m_scheduler;
    private boolean m_repeat;

    private Runnable createTask(Runnable runnable)
    {
        return new TimerTask() {
            @Override
            public void run()
            {
                runnable.run();
                if (m_repeat) {
                    m_scheduler = Scheduler.of();
                    m_dateTime = m_dateTime.plusDays(1);
                    m_scheduler.schedule(createTask(runnable), m_dateTime);
                }
            }
        };
    }

    private Alarm(LocalDateTime dateTime)
    {
        m_dateTime = dateTime;
        m_scheduler = Scheduler.of();
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
        m_scheduler.schedule(createTask(runnable), m_dateTime);
    }

    public void cancel()
    {
        m_scheduler.cancel();
    }
}
