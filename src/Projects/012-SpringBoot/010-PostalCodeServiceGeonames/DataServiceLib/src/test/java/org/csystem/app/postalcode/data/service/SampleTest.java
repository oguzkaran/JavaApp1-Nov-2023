package org.csystem.app.postalcode.data.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootApplication
@SpringBootTest
@ComponentScan(basePackages = "org.csystem")
@EntityScan(basePackages = "org.csystem")
@EnableJpaRepositories(basePackages = "org.csystem")
@TestPropertySource(locations = "classpath:application-unittest.properties")
public class SampleTest {
    @Autowired
    private PostalCodeDataService m_postalCodeDataService;
    @Test
    public void test()
    {

    }
}
