package org.csystem.app.earthquake.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoSave;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class EarthquakeAppDataHelper {
    private final IRegionInfoRepository m_earthquakeRepository;

    public EarthquakeAppDataHelper(IRegionInfoRepository earthquakeRepository)
    {
        m_earthquakeRepository = earthquakeRepository;
    }

    public EarthquakesInfo findByEarthquakesByRegionInfo(double east, double west, double north, double south)
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

    @Transactional
    public void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave, RegionInfo regionInfo)
    {
        m_earthquakeRepository.save(regionInfo);

        log.info("regionInfoId:{}", regionInfo.id);
        log.info("EarthquakeAppDataHelper.saveEarthquake:{}", earthquakeInfoSave.toString());

        earthquakeInfoSave.earthquakeInfo.regionInfoId = regionInfo.id;
        earthquakeInfoSave.earthquakeAddress.regionInfoId = regionInfo.id;
        earthquakeInfoSave.earthquakeCountryInfo.regionInfoId = regionInfo.id;

        m_earthquakeRepository.saveEarthquake(earthquakeInfoSave, regionInfo.id);
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
