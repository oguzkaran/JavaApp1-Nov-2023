package org.csystem.app.postalcode.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@SpringBootApplication
@SpringBootTest
@TestPropertySource(locations = "classpath:application-unittest.properties")
//@EnableJpaRepositories
@EntityScan("org.csystem")
public class PostalCodeInfoRepositoryFindByAdminNameTest {
    @Autowired
    private IPostalCodeInfoRepository m_postalCodeInfoRepository;

    @Test
    public void test()
    {

    }
}
