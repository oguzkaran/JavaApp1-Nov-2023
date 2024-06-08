package org.csystem.app.generator.random.text.runner;

import com.karandev.io.util.console.Console;
import lombok.extern.slf4j.Slf4j;
import org.csystem.scheduler.Scheduler;
import org.csystem.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.random.RandomGenerator;

@Component
@Slf4j
public class TextGeneratorRunner implements ApplicationRunner {
    private final Scheduler m_scheduler;
    private final RandomGenerator m_randomGenerator;

    @Value("${gen.min}")
    private int m_min;

    @Value("${gen.bound}")
    private int m_bound;

    private void schedulerCallback()
    {
        try {
            var text = StringUtil.generateRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(m_min, m_bound));
            Console.writeLine("Generated Text:%s", text);
        }
        catch (Throwable ex) {
            m_scheduler.cancel();
            log.error("Exception occurred:{}", ex.getMessage());
        }
    }

    public TextGeneratorRunner(Scheduler scheduler, RandomGenerator randomGenerator)
    {
        m_scheduler = scheduler;
        m_randomGenerator = randomGenerator;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        m_scheduler.schedule(this::schedulerCallback);
    }
}
