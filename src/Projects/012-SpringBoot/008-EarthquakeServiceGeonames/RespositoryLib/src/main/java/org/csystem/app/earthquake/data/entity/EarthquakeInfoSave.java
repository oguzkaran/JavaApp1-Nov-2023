package org.csystem.app.earthquake.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class EarthquakeInfoSave {
    public RegionInfo regionInfo;
    public EarthquakeInfo earthquakeInfo;
    public EarthquakeAddress earthquakeAddress;
    public EarthquakeCountryInfo earthquakeCountryInfo;
}
