package org.csystem.app.earthquake.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoDetails;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoSave;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.earthquake.data.repository.IEarthquakeInfoRepository;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class EarthquakeAppDataHelper {
    private final IRegionInfoRepository m_earthquakeRepository;
    private final IEarthquakeInfoRepository m_earthquakeInfoRepository;

    private void saveRegionInfo(RegionInfo regionInfo)
    {
        m_earthquakeRepository.save(regionInfo);

        log.info("RegionInfo Id:{}", regionInfo.id);
    }

    private void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave, long regionInfoId)
    {
        log.info("EarthquakeAppDataHelper.saveEarthquake:{}", earthquakeInfoSave.toString());

        earthquakeInfoSave.earthquakeInfo.regionInfoId = regionInfoId;
        earthquakeInfoSave.earthquakeAddress.regionInfoId = regionInfoId;
        earthquakeInfoSave.earthquakeCountryInfo.regionInfoId = regionInfoId;

        m_earthquakeRepository.saveEarthquake(earthquakeInfoSave);
    }

    public EarthquakeAppDataHelper(IRegionInfoRepository earthquakeRepository,
                                   IEarthquakeInfoRepository earthquakeInfoRepository)
    {
        m_earthquakeRepository = earthquakeRepository;
        m_earthquakeInfoRepository = earthquakeInfoRepository;

    }

    @Transactional
    public EarthquakesInfo findByEarthquakesByRegionInfo(double east, double west, double north, double south)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Transactional
    public void saveEarthquakes(List<EarthquakeInfoSave> earthquakes, RegionInfo regionInfo)
    {
        saveRegionInfo(regionInfo);
        log.info("RegionInfo:{}", regionInfo.toString());
        earthquakes.forEach(e -> saveEarthquake(e, regionInfo.id));
        m_earthquakeRepository.saveEarthquakeQueryInfo(regionInfo.id);
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
