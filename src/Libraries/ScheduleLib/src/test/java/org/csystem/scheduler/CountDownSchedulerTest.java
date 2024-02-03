package org.csystem.scheduler;

import com.karandev.io.util.console.Console;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CountDownSchedulerTest {
    private int m_expected = 10;

    @Test
    public void test() throws InterruptedException
    {
        new CountDownScheduler(10000, 1000) {
            public void onTick(long remainingMilliseconds)
            {
                Console.writeLine(remainingMilliseconds / 1000);
                --m_expected;
            }

            public void onFinish()
            {
                Assert.assertEquals(0, m_expected);
            }
        }.start();

        Thread.sleep(11000);
    }
}
