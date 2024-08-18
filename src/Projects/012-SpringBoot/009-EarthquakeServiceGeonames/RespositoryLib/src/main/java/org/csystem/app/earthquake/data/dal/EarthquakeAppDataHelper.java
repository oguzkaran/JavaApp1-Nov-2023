package org.csystem.app.earthquake.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoDetails;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoSave;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.earthquake.data.repository.IEarthquakeAddressRepository;
import org.csystem.app.earthquake.data.repository.IEarthquakeCountryInfoRepository;
import org.csystem.app.earthquake.data.repository.IEarthquakeInfoRepository;
import org.csystem.app.earthquake.data.repository.IRegionInfoRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class EarthquakeAppDataHelper {
    private final IRegionInfoRepository m_earthquakeRepository;
    private final IEarthquakeInfoRepository m_earthquakeInfoRepository;
    private final IEarthquakeAddressRepository m_earthquakeAddressRepository;
    private final IEarthquakeCountryInfoRepository m_earthquakeCountryInfoRepository;

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
                                   IEarthquakeInfoRepository earthquakeInfoRepository,
                                   IEarthquakeAddressRepository earthquakeAddressRepository,
                                   IEarthquakeCountryInfoRepository earthquakeCountryInfoRepository)
    {
        m_earthquakeRepository = earthquakeRepository;
        m_earthquakeInfoRepository = earthquakeInfoRepository;
        m_earthquakeAddressRepository = earthquakeAddressRepository;
        m_earthquakeCountryInfoRepository = earthquakeCountryInfoRepository;
    }

    @Transactional
    public EarthquakesInfo findByEarthquakesByRegionInfo(double east, double west, double north, double south)
    {
        try {
            log.info("EarthquakeAppDataHelper.findByEarthquakesByRegionInfo");
            var earthquakeDetails = new ArrayList<EarthquakeInfoDetails>();
            var earthQuakes = EarthquakesInfo.builder().earthquakes(earthquakeDetails).build();
            var regionInfo = m_earthquakeRepository.findByRegion(east, west, north, south);
            var earthQuakeInfoList = m_earthquakeInfoRepository.findByRegionInfoId(regionInfo.id);
            var earthquakeAddressList = m_earthquakeAddressRepository.findByRegionInfoId(regionInfo.id);
            var earthquakeCountryInfo = m_earthquakeCountryInfoRepository.findByRegionInfoId(regionInfo.id);
            var size = StreamSupport.stream(earthQuakeInfoList.spliterator(), false).count();

            LongStream.range(0, size).forEach(i -> earthquakeDetails.add(EarthquakeInfoDetails.builder().build()));

            //...

            return earthQuakes;
        }
        catch (Throwable ex) {
            log.info("EarthquakeAppDataHelper.findByEarthquakesByRegionInfo: Message: {}", ex.getMessage());
            throw new RepositoryException("EarthquakeAppDataHelper.saveQueryInfo", ex);
        }
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
