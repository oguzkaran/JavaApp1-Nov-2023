package org.csystem.app.postalcode.data.repository;

import org.csystem.app.postalcode.data.dal.PostalCodeAppDataHelper;
import org.csystem.app.postalcode.data.entity.PostalCode;
import org.csystem.app.postalcode.data.mapper.IPostalCodeMapper;
import org.csystem.app.postalcode.geonames.service.GeonamesPostalCodeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootApplication
@SpringBootTest
@TestPropertySource(locations = "classpath:application-unittest.properties")
@EntityScan("org.csystem")
@ComponentScan(basePackages = "org.csystem")
public class PostalCodeInfoRepositoryFindByMonthAndYearTest {
    @Autowired
    private PostalCodeAppDataHelper m_postalCodeAppDataHelper;

    @Autowired
    private IPostalCodeRepository m_postalCodeRepository;

    @Autowired
    private GeonamesPostalCodeService m_geonamesPostalCodeService;

    @Autowired
    private IPostalCodeMapper m_PostalCodeMapper;

    @BeforeEach
    public void setUp()
    {
        var postalCodeValue = "67000";

        var postalCodes = m_geonamesPostalCodeService.findPostalCodes(postalCodeValue);
        var postalCode = new PostalCode();

        postalCode.postalCode = postalCodeValue;
        postalCode.firstQueryDateTime = LocalDateTime.now().withMonth(9).withYear(2023);

        m_postalCodeAppDataHelper.savePostalCodes(postalCode, m_PostalCodeMapper.toPostalCodeInfo(postalCodes.postalCodes));

        postalCode = new PostalCode();
        postalCodeValue = "20010";
        postalCode.postalCode = postalCodeValue;
        postalCodes = m_geonamesPostalCodeService.findPostalCodes(postalCodeValue);

        m_postalCodeAppDataHelper.savePostalCodes(postalCode, m_PostalCodeMapper.toPostalCodeInfo(postalCodes.postalCodes));
    }

    @Test
    public void test()
    {
        var month = 9;
        var year = 2023;
        var expectedCount = 1;

        var postalCodes = m_postalCodeRepository.findByMonthAndYear(month, year);

        var count = postalCodes.size();
        Assertions.assertEquals(expectedCount, count);
    }
}
