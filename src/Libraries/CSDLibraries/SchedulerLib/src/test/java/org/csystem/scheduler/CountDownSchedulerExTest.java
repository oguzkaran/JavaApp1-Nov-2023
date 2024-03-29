package org.csystem.scheduler;

import com.karandev.io.util.console.Console;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CountDownSchedulerExTest {
    private int m_expected = 10;

    @Test
    public void test() throws InterruptedException
    {
        new CountDownSchedulerEx(10000, 1000) {

            @Override
            public void onStart()
            {
                Console.writeLine("Scheduler started!...");
            }

            @Override
            public void onTick(long remainingMilliseconds)
            {
                Console.writeLine(remainingMilliseconds / 1000);
                --m_expected;
            }

            @Override
            public void onFinish()
            {
                Assert.assertEquals(0, m_expected);
            }
        }.startScheduler();

        Thread.sleep(11000);
    }
}
