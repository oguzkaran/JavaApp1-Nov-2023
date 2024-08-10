package org.csystem.app.earthquake.data;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.csystem.app.earthquake.data.repository.RegionInfoRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

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

    //@Test
    //@Order(0)
    public void givenValue_whenEarthquake_thenPrivateSaveRegionInfo_generated_id_true() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException
    {
        var earthquake = new EarthquakeInfoSave();

        earthquake.regionInfo = RegionInfo.builder()
                .east(23.4)
                .west(21.4)
                .north(20.4)
                .south(29.4)
                .build();

        var method = RegionInfoRepository.class.getDeclaredMethod("saveRegionInfo", RegionInfo.class);

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

        earthquake.regionInfo = RegionInfo.builder()
                .east(23.4)
                .west(21.4)
                .north(20.4)
                .south(29.4)
                .build();

        earthquake.earthquakeInfo = EarthquakeInfo.builder()
                .dateTime("2023-02-06 04:00:00")
                .depth(100)
                .latitude(45.67)
                .longitude(40.67)
                .earthquakeId("Test earthquake")
                .magnitude(7.6)
                .build();

        earthquake.earthquakeAddress = EarthquakeAddress.builder()
                .locality("Test locality")
                .street("Test street")
                .postalCode("67100")
                .build();

        earthquake.earthquakeCountryInfo = EarthquakeCountryInfo.builder()
                .distance("100")
                .countryCode("TR")
                .countryName("Turkey")
                .build();

        assertDoesNotThrow(() -> m_regionInfoRepository.saveEarthquake(earthquake));
    }
}
