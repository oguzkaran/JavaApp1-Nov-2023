package org.csystem.app.service.earthquake.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class EarthquakeDetailsCountryInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String distance;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryName;
}
