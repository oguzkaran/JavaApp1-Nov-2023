package org.csystem.app.postalcode.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.postalcode.data.entity.PostalCode;
import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.repository.IPostalCodeInfoRepository;
import org.csystem.app.postalcode.data.repository.IPostalCodeRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class PostalCodeAppDataHelper {
    private final IPostalCodeRepository m_postalCodeRepository;
    private final IPostalCodeInfoRepository m_postalCodeInfoRepository;

    public PostalCodeAppDataHelper(IPostalCodeRepository postalCodeRepository, IPostalCodeInfoRepository postalCodeInfoRepository)
    {
        m_postalCodeRepository = postalCodeRepository;
        m_postalCodeInfoRepository = postalCodeInfoRepository;
    }
    private void savePostalCodeInfoCallback(PostalCodeInfo postalCodeInfo, PostalCode postalCode)
    {
        postalCodeInfo.postalCode = postalCode;

        m_postalCodeInfoRepository.save(postalCodeInfo);
    }

    @Transactional
    public void savePostalCodes(PostalCode postalCode, Set<PostalCodeInfo> postalCodeInfo)
    {
        try {
            log.info("PostalCodeAppDataHelper.savePostalCodes");

            var pCode = m_postalCodeRepository.save(postalCode);

            postalCodeInfo.forEach(p -> savePostalCodeInfoCallback(p, pCode));
        } catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.savePostalCodes:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.savePostalCodes", ex);
        }
    }


    public List<PostalCodeInfo> findPostalCodeInfoByCity(String cityName)
    {
        try {
            log.info("PostalCodeAppDataHelper.findPostalCodeInfoByCity({})", cityName);
            return m_postalCodeInfoRepository.findByCity(cityName);
        }
        catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.findPostalCodeInfoByCity:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.findPostalCodeInfoByCity", ex);
        }
    }
}
