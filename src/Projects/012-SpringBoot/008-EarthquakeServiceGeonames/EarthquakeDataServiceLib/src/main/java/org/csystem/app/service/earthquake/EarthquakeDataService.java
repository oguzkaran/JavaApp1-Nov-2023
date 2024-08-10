package org.csystem.app.service.earthquake;

import org.csystem.app.earthquake.data.dal.EarthquakeAppDataHelper;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoDetails;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.service.earthquake.dto.EarthquakesDetails;
import org.csystem.app.service.earthquake.geonames.service.GeonamesEarthquakeService;
import org.csystem.app.service.earthquake.mapper.IEarthquakeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarthquakeDataService {
    private final GeonamesEarthquakeService m_earthquakeService;
    private final EarthquakeAppDataHelper m_earthquakeAppDataHelper;
    private final IEarthquakeMapper m_earthquakeMapper;

    private EarthquakesDetails notInDatabaseCallback(double east, double west, double north, double south)
    {
        var earthquakes = m_earthquakeMapper.toEarthquakeDetails(m_earthquakeService.findEarthquakesDetails(east, west, north, south));

        earthquakes.earthquakes.forEach(e -> m_earthquakeAppDataHelper.saveEarthquake(m_earthquakeMapper.toEarthquakeInfoSave(e)));

        return earthquakes;
    }

    private EarthquakesDetails inDatabaseCallback(EarthquakesInfo earthquakesInfo, long regionInfoId)
    {
        m_earthquakeAppDataHelper.saveQueryInfo(regionInfoId);

        return m_earthquakeMapper.toEarthquakeDetails(earthquakesInfo);
    }

    public EarthquakeDataService(GeonamesEarthquakeService earthquakeService, EarthquakeAppDataHelper earthquakeAppDataHelper, IEarthquakeMapper earthquakeMapper)
    {
        m_earthquakeService = earthquakeService;
        m_earthquakeAppDataHelper = earthquakeAppDataHelper;
        m_earthquakeMapper = earthquakeMapper;
    }

    public EarthquakesDetails findEarthquakesDetails(double east, double west, double north, double south)
    {
        var earthquakesInfo = m_earthquakeAppDataHelper.findByEarthquakesByRegionInfo(east, west, north, south);

        return earthquakesInfo.earthquakes.isEmpty() ? notInDatabaseCallback(east, west, north, south)
                : inDatabaseCallback(earthquakesInfo, earthquakesInfo.regionInfoId);
    }
}
