package org.csystem.app.flight.data;

import org.csystem.app.flight.data.constant.TestConstant;
import org.csystem.app.flight.data.entity.City;
import org.csystem.app.flight.data.repository.ICityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class CityRepositorySaveTest {
    @Autowired
    private ICityRepository m_cityRepository;

    @Test
    public void givenValue_whenCity_thenSetIdentity()
    {
        var city = new City(0, "Zonguldak");
        var expectedId = 1005L;

        m_cityRepository.save(city);

        assertEquals(expectedId, city.getId());
    }
}
