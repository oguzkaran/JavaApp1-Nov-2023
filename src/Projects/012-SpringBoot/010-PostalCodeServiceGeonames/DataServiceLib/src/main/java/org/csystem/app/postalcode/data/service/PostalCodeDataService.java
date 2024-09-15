package org.csystem.app.postalcode.data.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.postalcode.data.dal.PostalCodeAppDataHelper;
import org.csystem.app.postalcode.data.entity.PostalCode;
import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.service.dto.PostalCodesDTO;
import org.csystem.app.postalcode.data.service.mapper.IPostalCodeMapper;
import org.csystem.app.postalcode.geonames.service.GeonamesPostalCodeService;
import org.csystem.data.exception.repository.RepositoryException;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostalCodeDataService {
    private final GeonamesPostalCodeService m_geonamesPostalCodeService;
    private final PostalCodeAppDataHelper m_postalCodeAppDataHelper;
    private final IPostalCodeMapper m_postalCodeMapper;

    private PostalCodesDTO postalCodesInDB(List<PostalCodeInfo> postalCodeInfo)
    {
        m_postalCodeAppDataHelper.savePostalCodeQueryInfo(postalCodeInfo.get(0).postalCodeValue);
        return PostalCodesDTO.builder().postalCodes(postalCodeInfo.stream().map(m_postalCodeMapper::toPostalCode).toList())
                .build();
    }

    private PostalCodesDTO postalCodesNotInDB(String postalCodeValue)
    {
        var geoPostalCodes = m_geonamesPostalCodeService.findPostalCodes(postalCodeValue);
        var postalCodeInfo = geoPostalCodes.postalCodes.stream()
                .map(m_postalCodeMapper::toPostalCode)
                .collect(Collectors.toSet());

        var postalCode = new PostalCode();
        postalCode.postalCode = postalCodeValue;

        m_postalCodeAppDataHelper.savePostalCodes(postalCode, postalCodeInfo);

        return PostalCodesDTO.builder().
                postalCodes(postalCodeInfo.stream().map(m_postalCodeMapper::toPostalCode).toList())
                .build();
    }

    public PostalCodeDataService(GeonamesPostalCodeService geonamesPostalCodeService,
                                 PostalCodeAppDataHelper postalCodeAppDataHelper,
                                 IPostalCodeMapper postalCodeMapper)
    {
        m_geonamesPostalCodeService = geonamesPostalCodeService;
        m_postalCodeAppDataHelper = postalCodeAppDataHelper;
        m_postalCodeMapper = postalCodeMapper;
    }

    public PostalCodesDTO findPostalCodesByCityName(String cityName)
    {
        try {
            log.info("PostalCodeDataService.findPostalCodesByCityName:{}", cityName);

            var postalCodeInfo = m_postalCodeAppDataHelper.findPostalCodeInfoByCity(cityName);

            return PostalCodesDTO.builder().postalCodes(postalCodeInfo.stream().map(m_postalCodeMapper::toPostalCode).toList())
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
    public PostalCodesDTO findPostalCodes(String postalCode)
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
