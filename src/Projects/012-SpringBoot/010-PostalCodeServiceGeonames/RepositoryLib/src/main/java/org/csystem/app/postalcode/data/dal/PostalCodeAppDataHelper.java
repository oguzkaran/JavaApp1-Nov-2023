package org.csystem.app.postalcode.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.postalcode.data.entity.PostalCode;
import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.entity.PostalCodeQueryInfo;
import org.csystem.app.postalcode.data.repository.IPostalCodeInfoRepository;
import org.csystem.app.postalcode.data.repository.IPostalCodeQueryInfoRepository;
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
    private final IPostalCodeQueryInfoRepository m_postalCodeQueryInfoRepository;

    public PostalCodeAppDataHelper(IPostalCodeRepository postalCodeRepository, IPostalCodeInfoRepository postalCodeInfoRepository, IPostalCodeQueryInfoRepository postalCodeQueryInfoRepository)
    {
        m_postalCodeRepository = postalCodeRepository;
        m_postalCodeInfoRepository = postalCodeInfoRepository;
        m_postalCodeQueryInfoRepository = postalCodeQueryInfoRepository;
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
            var postalCodeQueryInfo = PostalCodeQueryInfo.builder()
                    .postalCode(pCode)
                    .queryDateTime(pCode.firstQueryDateTime)
                    .build();

            log.info("PostalCodeQueryInfo:{}", postalCodeQueryInfo.toString());
            m_postalCodeQueryInfoRepository.save(postalCodeQueryInfo);
            postalCodeInfo.forEach(p -> savePostalCodeInfoCallback(p, pCode));
        } catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.savePostalCodes:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.savePostalCodes", ex);
        }
    }

    @Transactional
    public void savePostalCodeQueryInfo(String postalCode)
    {
        try {
            log.info("PostalCodeAppDataHelper.savePostalCodeQueryInfo");

            m_postalCodeQueryInfoRepository.savePostalQueryInfo(postalCode);
        } catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.savePostalCodeQueryInfo:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.savePostalCodeQueryInfo", ex);
        }
    }

    @Transactional
    public List<PostalCodeInfo> findPostalCodeAndSaveQueryInfoByCity(String cityName)
    {
        try {
            log.info("PostalCodeAppDataHelper.findAndSavePostalCodeInfoByCity({})", cityName);

            var postalCodes = findPostalCodeInfoByCity(cityName);

            if (!postalCodes.isEmpty())
                m_postalCodeQueryInfoRepository.savePostalQueryInfo(postalCodes.get(0).postalCodeValue);

            return postalCodes;
        }
        catch (RepositoryException ex) {
            log.error("RepositoryException occurred in PostalCodeAppDataHelper.findAndSavePostalCodeInfoByCity:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.findAndSavePostalCodeInfoByCity", ex.getCause());
        }
        catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.findAndSavePostalCodeInfoByCity:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.findAndSavePostalCodeInfoByCity", ex);
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

    public List<PostalCodeInfo> findByPostalCodeInfoByPostalCode(String postalCode)
    {
        try {
            log.info("PostalCodeAppDataHelper.findByPostalCodeInfoByPostalCode({})", postalCode);
            return m_postalCodeInfoRepository.findByPostalCode(postalCode);
        }
        catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.findByPostalCodeInfoByPostalCode:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.findByPostalCodeInfoByPostalCode", ex);
        }
    }


    //...
}
