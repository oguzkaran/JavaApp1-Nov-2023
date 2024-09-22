package org.csystem.app.earthquake.data;

import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.csystem.app.earthquake.data.repository.RegionInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootApplication
@SpringBootTest
@TestPropertySource(locations = "classpath:application-unittest.properties")
public class SaveEarthquakeTest {
    @Autowired
    private IRegionInfoRepository m_regionInfoRepository;

    @Test
    public void givenValue_whenEarthquake_thenPrivateSaveRegionInfo_generated_id_true() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException
    {
        var regionInfo = RegionInfo.builder()
                .east(23.4)
                .west(21.4)
                .north(20.4)
                .south(29.4)
                .build();

        regionInfo = m_regionInfoRepository.save(regionInfo);

        assertEquals(1, regionInfo.id);
    }

    //@Test
    public void givenValue_whenEarthquakeInfo_thenPrivateSaveEarthquakeInfo_DoesNotThrowException() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException
    {
        var regionInfo = RegionInfo.builder()
                .east(25.4)
                .west(45.4)
                .north(30.4)
                .south(23.4)
                .build();

        regionInfo = m_regionInfoRepository.save(regionInfo);

        var earthquakeInfo = EarthquakeInfo.builder()
                .regionInfoId(regionInfo.id)
                .dateTime("2023-02-06 04:00:00")
                .depth(100)
                .latitude(45.67)
                .longitude(40.67)
                .earthquakeId("Test earthquake1")
                .magnitude(7.6)
                .locality("Test locality")
                .street("Test street")
                .postalCode("67100")
                .distance("100")
                .countryCode("TR")
                .countryName("Turkey")
                .build();

        var method = RegionInfoRepository.class.getDeclaredMethod("saveEarthquakeInfo", EarthquakeInfo.class);

        method.setAccessible(true);
        assertDoesNotThrow(() -> method.invoke(m_regionInfoRepository, earthquakeInfo));
    }

    @Test
    public void givenValue_whenEarthquake_thenSaveDoesNotThrowException()
    {
        var regionInfo = RegionInfo.builder()
                .east(23.4)
                .west(21.4)
                .north(20.4)
                .south(29.4)
                .build();

        m_regionInfoRepository.save(regionInfo);

        var earthquakeInfo = EarthquakeInfo.builder()
                .regionInfoId(regionInfo.id)
                .dateTime("2023-02-06 04:00:00")
                .depth(100)
                .latitude(45.67)
                .longitude(40.67)
                .earthquakeId("Test earthquake")
                .magnitude(7.6)
                .locality("Test locality")
                .street("Test street")
                .postalCode("67100")
                .distance("100")
                .countryCode("TR")
                .countryName("Turkey")
                .build();

        assertDoesNotThrow(() -> m_regionInfoRepository.saveEarthquake(earthquakeInfo));
    }
}
