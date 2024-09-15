package org.csystem.app.postalcode.geonames.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GeonamesPostalCodeService {
    private static final String POSTAL_CODE_URL_FORMAT = "http://api.geonames.org/postalCodeSearchJSON?postalcode=%s&maxRows=%d&username=csystem&country=tr";
    private static final int MAX_ROWS = 500;

    private final RestTemplate m_restTemplate;

    public GeonamesPostalCodeService(RestTemplate restTemplate)
    {
        m_restTemplate = restTemplate;
    }

    public GeonamesPostalCodes findPostalCodes(String postalCode)
    {
        return findPostalCodes(postalCode, MAX_ROWS);
    }

    public GeonamesPostalCodes findPostalCodes(String postalCode, int maxRows)
    {
        return m_restTemplate.getForObject(POSTAL_CODE_URL_FORMAT.formatted(postalCode, maxRows), GeonamesPostalCodes.class);
    }
}
