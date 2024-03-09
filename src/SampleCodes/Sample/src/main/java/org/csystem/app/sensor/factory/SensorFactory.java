package org.csystem.app.sensor.factory;

import org.csystem.app.sensor.entity.Sensor;
import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.random.RandomGenerator;

public class SensorFactory {
    private final RandomGenerator m_randomGenerator = new Random();

    private String createRandomHostAddress()
    {
        return String.format("%d.%d.%d.%d", m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(256),
                m_randomGenerator.nextInt(256), m_randomGenerator.nextInt(1, 256));
    }

    private Sensor createRandomSensor()
    {
        var id = m_randomGenerator.nextInt(1000);
        var name = StringUtil.generateRandomTextEN(m_randomGenerator, m_randomGenerator.nextInt(5, 41));
        var host = createRandomHostAddress();
        var port = m_randomGenerator.nextInt(1024, 65536);

        return new Sensor(id, name, host, port);
    }

    public Optional<Sensor> findFirst(Predicate<Sensor> predicate)
    {
        var count = m_randomGenerator.nextInt(1, 100);

        while (count-- > 0) {
            var sensor = createRandomSensor();

            if (predicate.test(sensor))
                return Optional.of(sensor);
        }

        return Optional.empty();
    }
}
