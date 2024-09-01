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

import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public void savePostalCodeQueryInfo(String postalCode)
    {
        try {
            log.info("PostalCodeAppDataHelper.savePostalCodeQueryInfo:{}", postalCode);

            var opt = m_postalCodeRepository.findById(postalCode);

            if (opt.isEmpty())
                throw new SQLException("%s not found".formatted(postalCode));

            var p = opt.get();
            var postalCodeQueryInfo = new PostalCodeQueryInfo();

            postalCodeQueryInfo.postalCode = p;
            postalCodeQueryInfo.queryDateTime = p.firstQueryDateTime;
            m_postalCodeQueryInfoRepository.save(postalCodeQueryInfo);
        }
        catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.savePostalCodeQueryInfo:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.savePostalCodeQueryInfo", ex);
        }
    }

    public void savePostalCodeQueryInfo(PostalCode postalCode)
    {
        try {
            log.info("PostalCodeAppDataHelper.savePostalCodeQueryInfo:{}", postalCode.toString());

            var postalCodeQueryInfo = new PostalCodeQueryInfo();

            postalCodeQueryInfo.postalCode = postalCode;
            postalCodeQueryInfo.queryDateTime = postalCode.firstQueryDateTime;
            m_postalCodeQueryInfoRepository.save(postalCodeQueryInfo);
        }
        catch (Throwable ex) {
            log.error("Exception occurred in PostalCodeAppDataHelper.savePostalCodeQueryInfo:{}", ex.getMessage());
            throw new RepositoryException("PostalCodeAppDataHelper.savePostalCodeQueryInfo", ex);
        }
    }

    @Transactional
    public void savePostalCodes(PostalCode postalCode, Set<PostalCodeInfo> postalCodeInfo)
    {
        try {
            log.info("PostalCodeAppDataHelper.savePostalCodes");

            var pCode = m_postalCodeRepository.save(postalCode);
            var postalCodeQueryInfo = new PostalCodeQueryInfo();

            postalCodeQueryInfo.postalCode = pCode;
            postalCodeQueryInfo.queryDateTime = pCode.firstQueryDateTime;
            m_postalCodeQueryInfoRepository.save(postalCodeQueryInfo);
            postalCodeInfo.forEach(p -> savePostalCodeInfoCallback(p, pCode));
        }
        catch (Throwable ex) {
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

    //...
}
