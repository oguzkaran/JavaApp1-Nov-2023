package org.csystem.app.earthquake.data.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;

@EqualsAndHashCode
@Builder
@ToString
public class EarthquakeInfo {
    public long id;
    public long regionInfoId;
    public String dateTime;
    public double depth;
    public double latitude;
    public double longitude;
    public String earthquakeId;
    public double magnitude;
    public String locality;
    public String street;
    public String postalCode;
    public String distance;
    public String countryCode;
    public String countryName;
}
