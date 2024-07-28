package org.csystem.app.service.earthquake.geonames.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GeonamesEarthQuakeDetails {
    @JsonProperty("info")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public GeonamesEarthquakeDetailsInfo geonamesEarthquakeDetailsInfo;

    @JsonProperty("address")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public GeonamesEarthquakeDetailsAddress geonamesEarthquakeDetailsAddress;

    @JsonProperty("countryInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public GeonamesEarthquakeDetailsCountryInfo m_geonamesEarthquakeDetailsCountryInfo;
}
