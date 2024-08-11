package org.csystem.app.service.earthquake.geonames.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

@ToString
public class GeonamesEarthquakeDetailsCountryInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String distance;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryName;
}
