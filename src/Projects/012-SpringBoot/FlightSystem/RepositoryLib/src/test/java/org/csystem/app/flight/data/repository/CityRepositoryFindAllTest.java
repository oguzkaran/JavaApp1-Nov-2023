package org.csystem.app.flight.data.repository;

import org.csystem.app.flight.data.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class CityRepositoryFindAllTest {
    @Autowired
    private ICityRepository m_cityRepository;

    @Test
    public void test()
    {
        var expectedCount = 1004L;
        var count = StreamSupport.stream(m_cityRepository.findAll().spliterator(), false).count();

        assertEquals(expectedCount, count);
    }
}
