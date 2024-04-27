package org.csystem.camera.car.source;

import org.csystem.scheduler.Scheduler;

import java.io.Closeable;
import java.util.function.Consumer;

public class CarSource implements Closeable  {
    private static final long INTERVAL = 300;
    private final CarFactory m_carFactory = new CarFactory();
    private Scheduler m_scheduler;

    public void run(Consumer<CarCameraInfo> consumer)
    {
        m_scheduler = Scheduler.of(INTERVAL);

        m_scheduler.schedule(() -> consumer.accept(m_carFactory.createCarCameraInfo()));
    }

    @Override
    public void close()
    {
        m_scheduler.cancel();
    }
}
