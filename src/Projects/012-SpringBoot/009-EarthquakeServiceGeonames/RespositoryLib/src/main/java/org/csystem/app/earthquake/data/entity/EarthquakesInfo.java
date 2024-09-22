package org.csystem.app.earthquake.data.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Builder
public class EarthquakesInfo {
    public long regionInfoId;
    public List<EarthquakeInfo> earthquakes;
}
