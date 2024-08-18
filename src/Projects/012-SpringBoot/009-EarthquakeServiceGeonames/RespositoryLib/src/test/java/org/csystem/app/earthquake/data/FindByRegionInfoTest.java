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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-unittest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FindByRegionInfoTest {
    @Autowired
    private IRegionInfoRepository m_regionInfoRepository;

    @Test
    @Order(0)
    public void givenValues_whenLocation_thenFound()
    {
        var earthquake = new EarthquakeInfoSave();

        var regionInfo = RegionInfo.builder()
                .east(23.4)
                .west(21.4)
                .north(20.4)
                .south(29.4)
                .build();

        m_regionInfoRepository.save(regionInfo);
        earthquake.earthquakeInfo = EarthquakeInfo.builder()
                .dateTime("2023-02-06 04:00:00")
                .depth(100)
                .latitude(45.67)
                .longitude(40.67)
                .earthquakeId("Test earthquake")
                .magnitude(7.6)
                .regionInfoId(regionInfo.id)
                .build();

        earthquake.earthquakeAddress = EarthquakeAddress.builder()
                .locality("Test locality")
                .street("Test street")
                .postalCode("67100")
                .regionInfoId(regionInfo.id)
                .build();

        earthquake.earthquakeCountryInfo = EarthquakeCountryInfo.builder()
                .distance("100")
                .countryCode("TR")
                .countryName("Turkey")
                .regionInfoId(regionInfo.id)
                .build();

        m_regionInfoRepository.saveEarthquake(earthquake);
        var earthquakesInfo = m_regionInfoRepository.findByRegion(23.4, 21.4, 20.4, 29.4);

        assertFalse(earthquakesInfo.earthquakes.isEmpty());
        assertEquals(1, earthquakesInfo.regionInfoId);
    }

    @Test
    @Order(1)
    public void givenValues_whenLocation_thenNotFound()
    {
        var earthquake = new EarthquakeInfoSave();

        var regionInfo = RegionInfo.builder()
                .east(23.4)
                .west(21.4)
                .north(20.4)
                .south(29.4)
                .build();

        m_regionInfoRepository.save(regionInfo);

        earthquake.earthquakeInfo = EarthquakeInfo.builder()
                .dateTime("2023-02-06 04:00:00")
                .depth(100)
                .latitude(45.67)
                .longitude(40.67)
                .earthquakeId("Test earthquake")
                .magnitude(7.6)
                .regionInfoId(regionInfo.id)
                .build();

        earthquake.earthquakeAddress = EarthquakeAddress.builder()
                .locality("Test locality")
                .street("Test street")
                .postalCode("67100")
                .regionInfoId(regionInfo.id)
                .build();

        earthquake.earthquakeCountryInfo = EarthquakeCountryInfo.builder()
                .distance("100")
                .countryCode("TR")
                .countryName("Turkey")
                .regionInfoId(regionInfo.id)
                .build();

        m_regionInfoRepository.saveEarthquake(earthquake);
        var earthquakesInfo = m_regionInfoRepository.findByRegion(26.4, 21.4, 20.4, 29.4);

        assertTrue(earthquakesInfo.earthquakes.isEmpty());
    }
}
