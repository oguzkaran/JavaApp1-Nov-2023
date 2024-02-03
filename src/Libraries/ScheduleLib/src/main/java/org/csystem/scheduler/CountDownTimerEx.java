package org.csystem.scheduler;

import java.util.concurrent.TimeUnit;

public abstract class CountDownTimerEx extends CountDownTimer {
    protected CountDownTimerEx(long durationInFuture, long countDownInterval, TimeUnit timeUnit)
    {
        this(timeUnit.toMillis(durationInFuture), timeUnit.toMillis(countDownInterval));
    }

    protected CountDownTimerEx(long millisInFuture, long countDownInterval)
    {
        super(millisInFuture, countDownInterval);
    }
    
    public abstract void onStart();
}
