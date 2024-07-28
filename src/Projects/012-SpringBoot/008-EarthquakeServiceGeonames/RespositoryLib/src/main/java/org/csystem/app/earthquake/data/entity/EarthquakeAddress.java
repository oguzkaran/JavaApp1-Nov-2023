package org.csystem.app.earthquake.data.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EarthquakeAddress {
    public long id;
    public String locality;
    public String street;
    public String postalCode;

    public long regionInfoId;
}
