package org.csystem.app.earthquake.data;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaveEarthquakeTest {
    @Autowired
    private IRegionInfoRepository m_regionInfoRepository;


    @Test
    @Order(0)
    public void givenValue_whenEarthquake_thenPrivateSaveRegionInfo_generated_id_true() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException
    {
        var earthquake = new EarthquakeInfoSave();

        earthquake.regionInfo = new RegionInfo();
        earthquake.regionInfo.east = 23.4;
        earthquake.regionInfo.west = 21.4;
        earthquake.regionInfo.north = 20.4;
        earthquake.regionInfo.south = 29.4;

        var method = m_regionInfoRepository.getClass().getDeclaredMethod("saveRegionInfo", RegionInfo.class);

        method.setAccessible(true);
        var result = (long)method.invoke(m_regionInfoRepository, earthquake.regionInfo);

        method.setAccessible(false);

        assertEquals(1, result);
    }

    @Test
    @Order(1)
    public void givenValue_whenEarthquake_thenSaveNotThrowsSQLException()
    {
        var earthquake = new EarthquakeInfoSave();

        earthquake.regionInfo = new RegionInfo();
        earthquake.regionInfo.east = 23.4;
        earthquake.regionInfo.west = 21.4;
        earthquake.regionInfo.north = 20.4;
        earthquake.regionInfo.south = 29.4;

        earthquake.earthquakeInfo = new EarthquakeInfo();
        earthquake.earthquakeInfo.dateTime = "2023-02-06 04:00:00";
        earthquake.earthquakeInfo.depth = 100;
        earthquake.earthquakeInfo.latitude = 45.67;
        earthquake.earthquakeInfo.longitude = 40.67;
        earthquake.earthquakeInfo.earthquakeId = "Test Earthquake";
        earthquake.earthquakeInfo.magnitude = 7.6;

        earthquake.earthquakeAddress = new EarthquakeAddress();

        earthquake.earthquakeCountryInfo = new EarthquakeCountryInfo();

        assertDoesNotThrow(() -> m_regionInfoRepository.saveEarthquake(earthquake));
    }
}
