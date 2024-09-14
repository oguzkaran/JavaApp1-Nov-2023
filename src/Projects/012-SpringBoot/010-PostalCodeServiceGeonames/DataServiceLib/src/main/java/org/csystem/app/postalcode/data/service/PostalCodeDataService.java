package org.csystem.app.postalcode.data.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.postalcode.data.dal.PostalCodeAppDataHelper;
import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.service.dto.PostalCodes;
import org.csystem.app.postalcode.data.service.mapper.IPostalCodeMapper;
import org.csystem.app.postalcode.geonames.service.GeonamesPostalCodeService;
import org.csystem.data.exception.repository.RepositoryException;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostalCodeDataService {
    private final GeonamesPostalCodeService m_geonamesPostalCodeService;
    private final PostalCodeAppDataHelper m_postalCodeAppDataHelper;
    private final IPostalCodeMapper m_postalCodeMapper;

    private PostalCodes postalCodesInDB(List<PostalCodeInfo> postalCodeInfo)
    {
        m_postalCodeAppDataHelper.savePostalCodeQueryInfo(postalCodeInfo.get(0).postalCodeValue);
        return PostalCodes.builder().postalCodes(postalCodeInfo.stream().map(m_postalCodeMapper::toPostalCode).toList())
                .build();
    }

    private PostalCodes postalCodesNotInDB(String postalCode)
    {
        throw new UnsupportedOperationException("TODO: fetch from Geonames, save to dbi, return data");
    }

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
        try {
            log.info("PostalCodeDataService.findPostalCodesByCityName:{}", cityName);

            var postalCodeInfo = m_postalCodeAppDataHelper.findPostalCodeInfoByCity(cityName);

            return PostalCodes.builder().postalCodes(postalCodeInfo.stream().map(m_postalCodeMapper::toPostalCode).toList())
                    .build();
        }
        catch (RepositoryException ex) {
            log.info("RepositoryException occurred in PostalCodeDataService.findPostalCodesByCityName:{}", ex.getMessage());
            throw new DataServiceException("PostalCodeDataService.findPostalCodesByCityName", ex.getCause());
        }
        catch (Throwable ex) {
            log.info("Exception occurred in PostalCodeDataService.findPostalCodesByCityName:{}", ex.getMessage());
            throw new DataServiceException("PostalCodeDataService.findPostalCodesByCityName", ex);
        }
    }

    @Transactional
    public PostalCodes findPostalCodes(String postalCode)
    {
        try {
            log.info("PostalCodeDataService.findPostalCodes:{}", postalCode);

            var postalCodeInfo = m_postalCodeAppDataHelper.findByPostalCodeInfoByPostalCode(postalCode);

            return postalCodeInfo.isEmpty() ? postalCodesNotInDB(postalCode) : postalCodesInDB(postalCodeInfo);
        }
        catch (RepositoryException ex) {
            log.info("RepositoryException occurred in PostalCodeDataService.findPostalCodes:{}", ex.getMessage());
            throw new DataServiceException("PostalCodeDataService.findPostalCodes", ex.getCause());
        }
        catch (Throwable ex) {
            log.info("Exception occurred in PostalCodeDataService.findPostalCodes:{}", ex.getMessage());
            throw new DataServiceException("PostalCodeDataService.findPostalCodesByCityName", ex);
        }
    }
}
