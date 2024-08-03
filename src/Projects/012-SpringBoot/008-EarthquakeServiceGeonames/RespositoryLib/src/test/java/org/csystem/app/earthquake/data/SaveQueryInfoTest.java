package org.csystem.app.earthquake.data;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-unittest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaveQueryInfoTest {
    @Autowired
    private IRegionInfoRepository m_regionInfoRepository;

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
        m_regionInfoRepository.saveEarthquake(earthquake);

        assertDoesNotThrow(() -> m_regionInfoRepository.saveEarthquakeQueryInfo(1));
    }
}
