package org.csystem.camera.car.source;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CarCameraInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String m_plate;
    private final LocalDateTime m_cameraTime;

    public CarCameraInfo(String plate, LocalDateTime cameraTime)
    {
        m_plate = plate;
        m_cameraTime = cameraTime;
    }

    public String getPlate()
    {
        return m_plate;
    }

    public LocalDateTime getCameraTime()
    {
        return m_cameraTime;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(m_plate, m_cameraTime);
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof CarCameraInfo ci && ci.m_plate.equals(m_plate) && ci.m_cameraTime.equals(m_cameraTime);
    }

    @Override
    public String toString()
    {
        return "%s, %s".formatted(m_plate, FORMATTER.format(m_cameraTime));
    }
}
