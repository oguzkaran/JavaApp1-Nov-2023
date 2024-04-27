package org.csystem.camera.car.source;

import static org.csystem.util.string.StringUtil.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

class CarFactory {
    private final RandomGenerator m_randomGenerator = new Random();
    private final List<String> RECORDED_PLATES = new ArrayList<String>();

    {
        RECORDED_PLATES.add("34 CSD 034");
        RECORDED_PLATES.add("67 CSD 067");
        RECORDED_PLATES.add("35 CSD 035");
        RECORDED_PLATES.add("06 CSD 006");
        //...
    }

    private String createRandomPlateText()
    {
        return String.format("%s%c%s", generateRandomText(m_randomGenerator, 1, "ABCDEFGHIJKLMNPRSTUYZ"),
                (char)(m_randomGenerator.nextInt(26) + 'A'),
                generateRandomText(m_randomGenerator, 1, "ABCDEFGHIJKLMNPRSTUYZ"));
    }

    private String createRandomPlate()
    {
        return m_randomGenerator.nextBoolean() ? RECORDED_PLATES.get(m_randomGenerator.nextInt(RECORDED_PLATES.size()))
                :String.format("%02d %s %03d", m_randomGenerator.nextInt(1, 82), createRandomPlateText(), m_randomGenerator.nextInt(1, 1000));
    }

    CarCameraInfo createCarCameraInfo()
    {
        return new CarCameraInfo(createRandomPlate(), LocalDateTime.now());
    }
}
