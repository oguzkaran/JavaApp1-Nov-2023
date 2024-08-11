package org.csystem.app.service.earthquake.controller;

import org.csystem.app.service.earthquake.EarthquakeDataService;
import org.csystem.app.service.earthquake.dto.EarthquakeDetails;
import org.csystem.app.service.earthquake.dto.EarthquakesDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/earthquake")
@Scope("prototype")
public class EarthquakeController {
    private final EarthquakeDataService m_earthquakeService;

    public EarthquakeController(EarthquakeDataService earthquakeService)
    {
        m_earthquakeService = earthquakeService;
    }

    @GetMapping("/json")
    public EarthquakesDetails findEarthquakeJson(@RequestParam double east,
                                                 @RequestParam double west,
                                                 @RequestParam double north,
                                                 @RequestParam double south)
    {
        return m_earthquakeService.findEarthquakesDetails(east, west, north, south);
    }
}
