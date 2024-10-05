package org.csystem.app.service.earthquake.scheduler.update;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.service.earthquake.EarthquakeDataService;
import org.csystem.app.service.earthquake.dto.RegionInfoDTO;
import org.csystem.app.service.earthquake.geonames.service.GeonamesEarthquakeService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "org.csystem")
@Slf4j
public class UpdateScheduler {
    private final GeonamesEarthquakeService m_earthquakeService;
    private final EarthquakeDataService m_earthquakeDataService;

    private void regionInfoCallBack(RegionInfoDTO regionInfo)
    {
        var earthquakes = m_earthquakeService.findEarthquakesDetails(regionInfo.east, regionInfo.west, regionInfo.north, regionInfo.south);

        log.info("{}", earthquakes.toString());
    }

    public UpdateScheduler(GeonamesEarthquakeService earthquakeService, EarthquakeDataService earthquakeDataService)
    {
        m_earthquakeService = earthquakeService;
        m_earthquakeDataService = earthquakeDataService;
    }

    //@Scheduled(cron = "0 0,30,36,41 9,10,13,14,15,17,19,22,23 * * * ")
    @Scheduled(cron = "* * * * * *")
    public void schedule()
    {
        var regions = m_earthquakeDataService.findAllRegions();
        regions.forEach(this::regionInfoCallBack);
    }
}
