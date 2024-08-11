package org.csystem.app.earthquake.data.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Builder
public class EarthquakeAddress {
    public long id;
    public String locality;
    public String street;
    public String postalCode;

    public long regionInfoId;
}
