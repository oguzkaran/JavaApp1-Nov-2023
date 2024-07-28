package org.csystem.app.earthquake.data.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EarthquakeCountryInfo {
    public long id;
    public String distance;
    public String countryCode;
    public String countryName;

    public long regionInfoId;
}
