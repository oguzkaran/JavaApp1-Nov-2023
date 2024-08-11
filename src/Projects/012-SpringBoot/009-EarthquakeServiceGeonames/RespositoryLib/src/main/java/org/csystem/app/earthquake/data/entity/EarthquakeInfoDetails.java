package org.csystem.app.earthquake.data.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Builder
public class EarthquakeInfoDetails {
    public EarthquakeInfo earthquakeInfo;
    public EarthquakeAddress earthquakeAddress;
    public EarthquakeCountryInfo earthquakeCountryInfo;
}
