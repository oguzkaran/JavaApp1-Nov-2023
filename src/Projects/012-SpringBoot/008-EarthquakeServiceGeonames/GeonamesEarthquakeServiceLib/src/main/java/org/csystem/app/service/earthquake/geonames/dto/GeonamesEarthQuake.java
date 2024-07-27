package org.csystem.app.service.earthquake.geonames.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GeonamesEarthQuake {
    @JsonProperty("datetime")
    public String dateTime;
    public double depth;
    public double lat;
    public double lng;
    public String eqid;
    public double magnitude;
}
