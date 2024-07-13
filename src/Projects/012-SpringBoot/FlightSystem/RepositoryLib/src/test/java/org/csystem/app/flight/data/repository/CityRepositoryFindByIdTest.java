package org.csystem.app.flight.data.repository;

import org.csystem.app.flight.data.constant.TestConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootApplication
@SpringBootTest
@TestPropertySource(locations = TestConstant.UNITTEST_PROPS_FILE)
public class CityRepositoryFindByIdTest {
    @Autowired
    private ICityRepository m_cityRepository;

    @Test
    public void givenValue_whenId_thenFound()
    {
        var name = "Palhais";
        var id = 6L;

        assertEquals(name, m_cityRepository.findById(id).get().getName());
    }

    @Test
    public void givenValue_whenId_thenOptionalNotEmpty()
    {
        var id = 6L;

        assertTrue(m_cityRepository.findById(id).isPresent());
    }

    @Test
    public void givenValue_whenId_then_thenOptionalEmpty()
    {
        var id = 3000L;

        assertTrue(m_cityRepository.findById(id).isEmpty());
    }
}
