package org.csystem.app.service.earthquake.geonames.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesPostalCodes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GeonamesPostalCodeService {
    private static final String POSTAL_CODE_URL_FORMAT = "http://api.geonames.org/postalCodeSearchJSON?postalcode=%s&maxRows=%d&username=csystem&country=tr";

    private final RestTemplate m_restTemplate;

    public GeonamesPostalCodeService(RestTemplate restTemplate)
    {
        m_restTemplate = restTemplate;
    }

    public GeonamesPostalCodes findPostalCodes(String postalCode)
    {
        return m_restTemplate.getForObject(POSTAL_CODE_URL_FORMAT.formatted(postalCode, 10), GeonamesPostalCodes.class);
    }
}
