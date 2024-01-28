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

