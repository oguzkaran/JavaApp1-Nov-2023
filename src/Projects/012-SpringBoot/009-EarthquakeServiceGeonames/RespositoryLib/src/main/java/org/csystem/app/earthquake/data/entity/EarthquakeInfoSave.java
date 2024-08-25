package org.csystem.app.earthquake.data.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class EarthquakeInfoSave {
    public EarthquakeInfo earthquakeInfo;
    public EarthquakeAddress earthquakeAddress;
    public EarthquakeCountryInfo earthquakeCountryInfo;
}
