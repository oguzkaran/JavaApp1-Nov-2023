package org.csystem.app.service.earthquake.geonames.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.service.earthquake.geonames.dto.*;
import org.csystem.app.service.earthquake.geonames.mapper.IGeonamesMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class GeonamesEarthquakeService {
    private static final String EARTHQUAKE_URL_FORMAT = "http://api.geonames.org/earthquakesJSON?north=%f&south=%f&east=%f&west=%f&username=csystem";
    private static final String ADDRESS_URL_FORMAT = "http://api.geonames.org/addressJSON?lat=%f&lng=%f&username=csystem";
    private static final String COUNTRY_CODE_URL_FORMAT = "http://api.geonames.org/countryCodeJSON?&lat=%f&lng=%f&username=csystem";

    private final RestTemplate m_restTemplate;
    private final IGeonamesMapper m_geonamesMapper;

    private GeonamesEarthquakeInfo findEarthquakes(double east, double west, double north, double south)
    {
        var url = String.format(EARTHQUAKE_URL_FORMAT, north, south, east, west);

        log.info("Earthquake Code url:{}", url);

        return m_restTemplate.getForObject(url, GeonamesEarthquakeInfo.class);
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

    private GeonamesEarthquakeDetails toGeonamesEarthQuakeDetails(GeonamesEarthquake geonamesEarthQuake,
                                                                  GeonamesAddress geonamesAddress,
                                                                  GeonamesCountryCode geonamesCountryCode)
    {
        var details = new GeonamesEarthquakeDetails();

        details.geonamesEarthquakeDetailsInfo = m_geonamesMapper.toGeonamesEarthquakeDetailsInfo(geonamesEarthQuake);
        details.geonamesEarthquakeDetailsAddress = m_geonamesMapper.toGeonamesEarthquakeDetailsAddress(geonamesAddress);
        details.m_geonamesEarthquakeDetailsCountryInfo = m_geonamesMapper.toGeonamesEarthquakeDetailsCountryInfo(geonamesCountryCode);

        return details;
    }

    private void earthquakeInfoDetailsCallback(GeonamesEarthquake geonamesEarthQuake, List<GeonamesEarthquakeDetails> details)
    {
        var address = findAddress(geonamesEarthQuake.lat, geonamesEarthQuake.lng);
        var countryInfo = findCountryCode(geonamesEarthQuake.lat, geonamesEarthQuake.lng);

        details.add(toGeonamesEarthQuakeDetails(geonamesEarthQuake, address, countryInfo));
    }

    private GeonamesEarthquakeInfoDetails toGeonamesEarthQuakeInfoDetails(GeonamesEarthquakeInfo geonamesEarthQuakeInfo)
    {
        var infoDetails = new GeonamesEarthquakeInfoDetails();
        
        infoDetails.earthquakes = new ArrayList<>();

        geonamesEarthQuakeInfo.earthquakes.forEach(e -> earthquakeInfoDetailsCallback(e, infoDetails.earthquakes));

        return infoDetails;
    }

    public GeonamesEarthquakeService(RestTemplate restTemplate, IGeonamesMapper geonamesMapper)
    {
        m_restTemplate = restTemplate;
        m_geonamesMapper = geonamesMapper;
    }

    public GeonamesEarthquakeInfoDetails findEarthquakesDetails(double east, double west, double north, double south)
    {
        var earthquake = findEarthquakes(north, south, east, west);

        return toGeonamesEarthQuakeInfoDetails(earthquake);
    }
}
