package org.csystem.app.earthquake.data.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Builder
public class EarthquakeInfo {
    public long id;
    public String dateTime;
    public double depth;
    public double latitude;
    public double longitude;
    public String earthquakeId;
    public double magnitude;

    public long regionInfoId;
}
