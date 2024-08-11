package org.csystem.app.service.earthquake.scheduler.update;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.service.earthquake.geonames.service.GeonamesEarthquakeService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "org.csystem")
@Slf4j
public class UpdateScheduler {
    private final GeonamesEarthquakeService m_earthquakeService;

    public UpdateScheduler(GeonamesEarthquakeService earthquakeService)
    {
        m_earthquakeService = earthquakeService;
    }

    @Scheduled(cron = "0 0,30,36,41 9,10,13,14,15,17,19,22,23 * * * ")
    public void schedule()
    {
        //update all information in database
        var earthquakes = m_earthquakeService.findEarthquakesDetails(145.54, 129.41, 45.55, 31.03);

        log.info("{}", earthquakes.toString());
    }
}
