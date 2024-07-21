package org.csystem.app.service.earthquake.geonames.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.service.earthquake.geonames.dto.*;
import org.csystem.app.service.earthquake.geonames.mapper.IGeonamesMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Slf4j
public class GeonamesEarthquakeService {
    private static final String EARTHQUAKE_URL_FORMAT = "http://api.geonames.org/earthquakesJSON?north=%f&south=%f&east=%f&west=%f&username=csystem";
    private static final String ADDRESS_URL_FORMAT = "http://api.geonames.org/addressJSON?lat=%f&lng=%f&username=csystem";
    private static final String COUNTRY_CODE_URL_FORMAT = "http://api.geonames.org/countryCodeJSON?&lat=%f&lng=%f&username=csystem";

    private final RestTemplate m_restTemplate;
    private final IGeonamesMapper m_geonamesMapper;

    public GeonamesEarthquakeService(RestTemplate restTemplate, IGeonamesMapper geonamesMapper)
    {
        m_restTemplate = restTemplate;
        m_geonamesMapper = geonamesMapper;
    }

    private GeonamesEarthQuakeInfo findEarthquakes(double north, double south, double east, double west)
    {
        var url = String.format(EARTHQUAKE_URL_FORMAT, north, south, east, west);

        log.info("Earthquake Code url:{}", url);

        return m_restTemplate.getForObject(url, GeonamesEarthQuakeInfo.class);
    }

    private GeonamesAddress findAddress(double latitude, double longitude)
    {
        var url = String.format(ADDRESS_URL_FORMAT, latitude, longitude);
        log.info("Address Code url:{}", url);

        return Objects.requireNonNull(m_restTemplate.getForObject(url, GeonamesAddressInfo.class)).address;
    }

    private GeonamesCountryCode findCountryCode(double latitude, double longitude)
    {
        var url = String.format(COUNTRY_CODE_URL_FORMAT, latitude, longitude);

        log.info("Country Code url:{}", url);

        return m_restTemplate.getForObject(url, GeonamesCountryCode.class);
    }

    public GeonamesEarthQuakeInfoDetails findEarthquakesDetails(double north, double south, double east, double west)
    {
        throw new UnsupportedOperationException("TODO");
    }
}
