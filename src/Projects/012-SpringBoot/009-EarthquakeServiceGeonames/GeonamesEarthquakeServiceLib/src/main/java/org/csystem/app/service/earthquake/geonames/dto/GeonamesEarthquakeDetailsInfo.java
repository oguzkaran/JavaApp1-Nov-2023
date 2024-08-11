package org.csystem.app.service.earthquake.geonames.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

@ToString
public class GeonamesEarthquakeDetailsInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String dateTime;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double depth;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double latitude;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double longitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String earthquakeId;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double magnitude;
}
