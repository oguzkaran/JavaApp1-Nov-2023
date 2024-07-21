package org.csystem.app.service.earthquake.controller;

import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeInfoDetails;
import org.csystem.app.service.earthquake.geonames.service.GeonamesEarthquakeService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/earthquake")
@Scope("prototype")
public class EarthquakeController {
    private final GeonamesEarthquakeService m_earthquakeService;

    public EarthquakeController(GeonamesEarthquakeService earthquakeService)
    {
        m_earthquakeService = earthquakeService;
    }

    @GetMapping("/json")
    public GeonamesEarthQuakeInfoDetails findEarthquakeJson(@RequestParam double north,
                                                            @RequestParam double south,
                                                            @RequestParam double east,
                                                            @RequestParam double west)
    {
        return m_earthquakeService.findEarthquakesDetails(north, south, east, west);
    }
}
