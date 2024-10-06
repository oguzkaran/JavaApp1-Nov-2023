package org.csystem.app.earthquake.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.earthquake.data.repository.IEarthquakeInfoRepository;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class EarthquakeAppDataHelper {
    private final IRegionInfoRepository m_regionInfoRepository;
    private final IEarthquakeInfoRepository m_earthquakeInfoRepository;

    private void saveRegionInfo(RegionInfo regionInfo)
    {
        m_regionInfoRepository.save(regionInfo);

        log.info("RegionInfo Id:{}", regionInfo.id);
    }

    private void saveEarthquake(EarthquakeInfo earthquakeInfo, long regionInfoId)
    {
        log.info("EarthquakeAppDataHelper.saveEarthquake:{}", earthquakeInfo.toString());

        earthquakeInfo.regionInfoId = regionInfoId;

        m_regionInfoRepository.saveEarthquake(earthquakeInfo);
    }

    public EarthquakeAppDataHelper(IRegionInfoRepository regionInfoRepository,
                                   IEarthquakeInfoRepository earthquakeInfoRepository)
    {
        m_regionInfoRepository = regionInfoRepository;
        m_earthquakeInfoRepository = earthquakeInfoRepository;
    }

    public Optional<EarthquakesInfo> findByEarthquakesByRegionInfo(double east, double west, double north, double south)
    {
        var earthQuakes = m_earthquakeInfoRepository.findByRegion(east, west, north, south);

        return earthQuakes.isEmpty() ? Optional.empty() : Optional.of(EarthquakesInfo.builder()
                .earthquakes(earthQuakes)
                .regionInfoId(earthQuakes.get(0).regionInfoId)
                .build());
    }

    public Optional<EarthquakesInfo> findByEarthquakesByRegionInfoId(long regionInfoId)
    {
        var earthQuakes = m_earthquakeInfoRepository.findByRegionInfoId(regionInfoId);

        return earthQuakes.isEmpty() ? Optional.empty() : Optional.of(EarthquakesInfo.builder()
                .earthquakes(earthQuakes)
                .regionInfoId(earthQuakes.get(0).regionInfoId)
                .build());
    }

    public Iterable<RegionInfo> findAllRegions()
    {
        return m_regionInfoRepository.findAll();
    }

    @Transactional
    public void saveEarthquakes(List<EarthquakeInfo> earthquakes, RegionInfo regionInfo)
    {
        saveRegionInfo(regionInfo);
        log.info("RegionInfo:{}", regionInfo.toString());
        earthquakes.forEach(e -> saveEarthquake(e, regionInfo.id));
        m_regionInfoRepository.saveEarthquakeQueryInfo(regionInfo.id);
    }

    public void saveQueryInfo(long regionInfoId)
    {
        log.info("EarthquakeAppDataHelper.saveQueryInfo");
        m_regionInfoRepository.saveEarthquakeQueryInfo(regionInfoId);
    }

    //...
}
