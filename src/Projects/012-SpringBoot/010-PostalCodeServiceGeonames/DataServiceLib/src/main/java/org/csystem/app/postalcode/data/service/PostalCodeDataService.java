package org.csystem.app.postalcode.data.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.postalcode.data.dal.PostalCodeAppDataHelper;
import org.csystem.app.postalcode.data.service.dto.PostalCodes;
import org.csystem.app.postalcode.data.service.mapper.IPostalCodeMapper;
import org.csystem.app.postalcode.geonames.service.GeonamesPostalCodeService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostalCodeDataService {
    private final GeonamesPostalCodeService m_geonamesPostalCodeService;
    private final PostalCodeAppDataHelper m_postalCodeAppDataHelper;
    private final IPostalCodeMapper m_postalCodeMapper;

    public PostalCodeDataService(GeonamesPostalCodeService geonamesPostalCodeService,
                                 PostalCodeAppDataHelper postalCodeAppDataHelper,
                                 IPostalCodeMapper postalCodeMapper)
    {
        m_geonamesPostalCodeService = geonamesPostalCodeService;
        m_postalCodeAppDataHelper = postalCodeAppDataHelper;
        m_postalCodeMapper = postalCodeMapper;
    }

    public PostalCodes findPostalCodesByCityName(String cityName)
    {
        throw new UnsupportedOperationException("TODO: Just search in db");
    }

    public PostalCodes findPostalCodes(String postalCode)
    {
        throw new UnsupportedOperationException("TODO: Check if data in db otherwise fetch data from geonames and save to db");
    }
}
