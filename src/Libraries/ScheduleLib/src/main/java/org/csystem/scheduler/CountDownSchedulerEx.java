/*-------------------------------------------------------------
	FILE		: CountDownSchedulerEx.java
	AUTHOR		: JavaApp1-Nov-2023 Group
	Last UPDATE	: 3rd Feb 2024

	CountDownTimerSchedulerEx class

	Copyleft (c) 1993 C and System Programmers Association
	All Right Free
-------------------------------------------------------------*/
package org.csystem.scheduler;

import java.util.concurrent.TimeUnit;

public abstract class CountDownSchedulerEx extends CountDownScheduler {
    protected CountDownSchedulerEx(long durationInFuture, long countDownInterval, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(durationInFuture), timeUnit.toMillis(countDownInterval));
    }

    protected CountDownSchedulerEx(long millisInFuture, long countDownInterval)
    {
        super(millisInFuture, countDownInterval);
    }

    public abstract void onStart();

    public final void startScheduler()
    {
        onStart();
        start();
    }
}
