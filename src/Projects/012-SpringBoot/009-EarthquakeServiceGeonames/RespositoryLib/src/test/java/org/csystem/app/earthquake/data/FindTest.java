package org.csystem.app.earthquake.data;

import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.earthquake.data.repository.IEarthquakeInfoRepository;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-unittest.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class FindTest {
    @Nested
    @SpringBootTest
    @TestPropertySource(locations = "classpath:application-unittest.properties")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestClassOrder(ClassOrderer.OrderAnnotation.class)
    @Order(2)
    class FindByRegionTest {
        @Autowired
        private IRegionInfoRepository m_regionInfoRepository;

        @Test
        @Order(1)
        public void givenValues_whenLocation_thenFound()
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

            m_regionInfoRepository.saveEarthquake(earthquakeInfo);
            var regionInfoOpt = m_regionInfoRepository.findByRegion(23.4, 21.4, 20.4, 29.4);

            assertTrue(regionInfoOpt.isPresent());
            assertEquals(1, regionInfoOpt.get().id);
        }

        @Test
        @Order(2)
        public void givenValues_whenLocation_thenNotFound()
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

            m_regionInfoRepository.saveEarthquake(earthquakeInfo);
            var regionInfoOpt = m_regionInfoRepository.findByRegion(26.4, 21.4, 20.4, 29.4);

            assertTrue(regionInfoOpt.isEmpty());
        }
    }

    @Nested
    @SpringBootTest
    @TestPropertySource(locations = "classpath:application-unittest.properties")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestClassOrder(ClassOrderer.OrderAnnotation.class)
    @Order(1)
    class FindEarthQuakesTest {
        @Autowired
        private IRegionInfoRepository m_regionInfoRepository;
        @Autowired
        private IEarthquakeInfoRepository m_earthquakeInfoRepository;

        private long saveEarthquakes()
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

            m_regionInfoRepository.saveEarthquake(earthquakeInfo);

            earthquakeInfo = EarthquakeInfo.builder()
                    .regionInfoId(regionInfo.id)
                    .dateTime("2023-02-06 04:00:00")
                    .depth(100)
                    .latitude(45.67)
                    .longitude(40.67)
                    .earthquakeId("Test earthquake2")
                    .magnitude(7.2)
                    .locality("Test locality")
                    .street("Test street")
                    .postalCode("67100")
                    .distance("100")
                    .countryCode("TR")
                    .countryName("Turkey")
                    .build();

            m_regionInfoRepository.saveEarthquake(earthquakeInfo);

            return regionInfo.id;
        }

        @Test
        @Order(1)
        public void givenValuesFindEarthquakesByRegion_whenLocation_thenCountTrue()
        {
            saveEarthquakes();
            var earthquakes = m_earthquakeInfoRepository.findEarthquakesByRegion(23.4, 21.4, 20.4, 29.4);

            assertEquals(2, earthquakes.size());
        }

        @Test
        @Order(2)
        public void givenValues_WhenRegionInfpo_thenCountTrue()
        {
            var earthquakes = m_earthquakeInfoRepository.findEarthquakesByRegionInfoId(saveEarthquakes());

            assertEquals(2, earthquakes.size());
        }

    }
}
