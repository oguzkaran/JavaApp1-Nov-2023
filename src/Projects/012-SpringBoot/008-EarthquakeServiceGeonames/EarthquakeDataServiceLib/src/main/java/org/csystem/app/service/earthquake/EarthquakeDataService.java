package org.csystem.app.service.earthquake;

import org.csystem.app.earthquake.data.dal.EarthquakeAppDataHelper;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoDetails;
import org.csystem.app.service.earthquake.dto.EarthquakeDetails;
import org.csystem.app.service.earthquake.geonames.service.GeonamesEarthquakeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarthquakeDataService {
    private final GeonamesEarthquakeService m_earthquakeService;
    private final EarthquakeAppDataHelper m_earthquakeAppDataHelper;

    private EarthquakeDetails notInDataBaseCallback()
    {
        throw new UnsupportedOperationException("TODO");
    }

    private EarthquakeDetails inDataBaseCallback(List<EarthquakeInfoDetails> earthquakes)
    {
        throw new UnsupportedOperationException("TODO");
    }

    public EarthquakeDataService(GeonamesEarthquakeService earthquakeService, EarthquakeAppDataHelper earthquakeAppDataHelper)
    {
        m_earthquakeService = earthquakeService;
        m_earthquakeAppDataHelper = earthquakeAppDataHelper;
    }

    public EarthquakeDetails findEarthquakesDetails(double east, double west, double north, double south)
    {
        var earthquakes = m_earthquakeAppDataHelper.findByEarthquakesByRegionInfo(east, west, north, south);

        return earthquakes.isEmpty() ? notInDataBaseCallback() : inDataBaseCallback(earthquakes);
    }
}
