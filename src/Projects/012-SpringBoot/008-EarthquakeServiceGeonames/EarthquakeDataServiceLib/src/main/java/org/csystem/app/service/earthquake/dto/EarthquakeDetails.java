package org.csystem.app.service.earthquake.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EarthquakeDetails {
    @JsonProperty("info")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public EarthquakeDetailsInfo earthquakeDetailsInfo;

    @JsonProperty("address")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public EarthquakeDetailsAddress earthquakeDetailsAddress;

    @JsonProperty("countryInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public EarthquakeDetailsCountryInfo earthquakeDetailsCountryInfo;
}
