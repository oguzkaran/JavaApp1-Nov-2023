package org.csystem.app.postalcode.geonames.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeonamesPostalCodeInfo {
    public String adminCode2;
    public String adminCode1;
    public String adminName2;
    public double lng;
    public String countryCode;
    public String postalCode;
    public String adminName1;
    @JsonProperty("ISO3166-2")
    public String iSO31662;
    public String placeName;
    public double lat;
    public String adminCode3;
    public String adminName3;
}