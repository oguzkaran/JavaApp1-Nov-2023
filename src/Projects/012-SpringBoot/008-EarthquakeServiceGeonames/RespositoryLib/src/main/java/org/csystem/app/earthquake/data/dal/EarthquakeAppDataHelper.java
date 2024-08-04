package org.csystem.app.earthquake.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoDetails;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoSave;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class EarthquakeAppDataHelper {
    private final IRegionInfoRepository m_earthquakeRepository;

    public EarthquakeAppDataHelper(IRegionInfoRepository earthquakeRepository)
    {
        m_earthquakeRepository = earthquakeRepository;
    }

    public List<EarthquakeInfoDetails> findByEarthquakesByRegionInfo(double east, double west, double north, double south)
    {
        try {
            log.info("EarthquakeAppDataHelper.findByEarthquakesByRegionInfo");
            return m_earthquakeRepository.findByRegionInfo(east, west, north, south);
        }
        catch (Throwable ex) {
            log.info("EarthquakeAppDataHelper.findByEarthquakesByRegionInfo: Message: {}", ex.getMessage());
            throw new RepositoryException("EarthquakeAppDataHelper.saveQueryInfo", ex);
        }
    }

    public void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave)
    {
       log.info("EarthquakeAppDataHelper.saveEarthquake");
       m_earthquakeRepository.saveEarthquake(earthquakeInfoSave);
    }

    public void saveQueryInfo(long regionInfoId)
    {
        try {
            log.info("EarthquakeAppDataHelper.saveQueryInfo");
            m_earthquakeRepository.saveEarthquakeQueryInfo(regionInfoId);
        }
        catch (Throwable ex) {
            log.info("EarthquakeAppDataHelper.saveQueryInfo: Message: {}", ex.getMessage());
            throw new RepositoryException("EarthquakeAppDataHelper.saveQueryInfo", ex);
        }
    }
}
