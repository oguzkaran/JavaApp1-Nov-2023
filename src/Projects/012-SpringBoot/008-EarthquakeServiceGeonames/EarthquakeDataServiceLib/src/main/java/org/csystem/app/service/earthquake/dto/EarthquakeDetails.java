package org.csystem.app.service.earthquake.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EarthquakeDetails {
    @JsonProperty("info")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public EarthquakeDetailsInfo m_earthquakeDetailsInfo;

    @JsonProperty("address")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public EarthquakeDetailsAddress m_earthquakeDetailsAddress;

    @JsonProperty("countryInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public EarthquakeDetailsCountryInfo m_earthquakeDetailsCountryInfo;
}
