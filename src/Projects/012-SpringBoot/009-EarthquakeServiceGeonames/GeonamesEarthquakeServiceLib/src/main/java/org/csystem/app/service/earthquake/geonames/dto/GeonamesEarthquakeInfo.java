package org.csystem.app.service.earthquake.geonames.dto;

import lombok.ToString;

import java.util.List;

@ToString
public class GeonamesEarthquakeInfo {
    public List<GeonamesEarthquake> earthquakes;
}
