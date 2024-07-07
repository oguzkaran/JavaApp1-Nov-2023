package org.csystem.app.flight.data;

import org.csystem.app.flight.data.constant.TestConstant;
import org.csystem.app.flight.data.repository.ICityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class CityRepositoryFindByNameTest {
    @Autowired
    private ICityRepository m_cityRepository;

    @Test
    public void givenValue_whenName_thenFound()
    {
        var name = "Bumba";
        var expectedCount = 3L;
        var count = StreamSupport.stream(m_cityRepository.findByName(name).spliterator(), false).count();

        assertEquals(expectedCount, count);
    }

}
