package org.csystem.app.postalcode.geonames;

import org.csystem.app.postalcode.geonames.service.GeonamesPostalCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
@SpringBootTest
public class GeonamesPostalCodeServiceFindPostalCodesTest {
    @Autowired
    private GeonamesPostalCodeService m_geonamesPostalCodeService;

    @Test
    public void test()
    {
        //TODO: Write test code for a method
    }
}
