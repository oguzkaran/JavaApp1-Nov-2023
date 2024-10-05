package org.csystem.app.service.earthquake;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.dal.EarthquakeAppDataHelper;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.service.earthquake.dto.EarthquakesDTO;
import org.csystem.app.service.earthquake.dto.RegionInfoDTO;
import org.csystem.app.service.earthquake.geonames.service.GeonamesEarthquakeService;
import org.csystem.app.service.earthquake.mapper.IEarthquakeMapper;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class EarthquakeDataService {
    private final GeonamesEarthquakeService m_earthquakeService;
    private final EarthquakeAppDataHelper m_earthquakeAppDataHelper;
    private final IEarthquakeMapper m_earthquakeMapper;

    private EarthquakesDTO notInDatabaseCallback(double east, double west, double north, double south)
    {
        var geonamesEarthquakes = m_earthquakeService.findEarthquakesDetails(east, west, north, south);
        log.info("Geonames earthquakes:{}", geonamesEarthquakes.toString());

        var earthquakes = m_earthquakeMapper.toEarthquakeInfoDTO(geonamesEarthquakes);

        log.info("Earthquakes:{}", earthquakes.toString());

        var ri = RegionInfo.builder().east(east).west(west).north(north).south(south).build();

        m_earthquakeAppDataHelper.saveEarthquakes(m_earthquakeMapper.toEarthquakesInfo(earthquakes).earthquakes, ri);

        return earthquakes;
    }

    private EarthquakesDTO inDatabaseCallback(EarthquakesInfo earthquakesInfo, long regionInfoId)
    {
        m_earthquakeAppDataHelper.saveQueryInfo(regionInfoId);

        return m_earthquakeMapper.toEarthquakesDTO(earthquakesInfo);
    }

    public EarthquakeDataService(GeonamesEarthquakeService earthquakeService, EarthquakeAppDataHelper earthquakeAppDataHelper, IEarthquakeMapper earthquakeMapper)
    {
        m_earthquakeService = earthquakeService;
        m_earthquakeAppDataHelper = earthquakeAppDataHelper;
        m_earthquakeMapper = earthquakeMapper;
    }

    public List<RegionInfoDTO> findAllRegions()
    {
        try {
            return StreamSupport.stream(m_earthquakeAppDataHelper.findAllRegions().spliterator(), false)
                    .map(m_earthquakeMapper::toRegionInfoDTO).toList();
        }
        catch (Throwable ex) {
            log.error("Exception occurred in EarthquakeDataService.findAllRegions:{}", ex.getMessage());
            throw new DataServiceException("EarthquakeDataService.findAllRegions", ex);
        }
    }
    
    public EarthquakesDTO findEarthquakes(double east, double west, double north, double south)
    {
        try {
            var earthquakesInfo = m_earthquakeAppDataHelper.findByEarthquakesByRegionInfo(east, west, north, south);

            return earthquakesInfo.isEmpty() ? notInDatabaseCallback(east, west, north, south)
                    : inDatabaseCallback(earthquakesInfo.get(), earthquakesInfo.get().regionInfoId);
        }
        catch (Throwable ex) {
            log.error("Exception occurred in EarthquakeDataService.findEarthquakes:{}", ex.getMessage());
            throw new DataServiceException("EarthquakeDataService.findEarthquakes", ex);
        }
    }
}
