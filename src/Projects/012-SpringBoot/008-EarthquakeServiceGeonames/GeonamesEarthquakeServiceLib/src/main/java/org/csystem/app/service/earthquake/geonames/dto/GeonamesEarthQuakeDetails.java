package org.csystem.app.service.earthquake.geonames.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

public class GeonamesEarthQuakeDetails {
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String locality;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String street;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String postalCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String distance;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryName;
}

/*
{
  "earthquakes": [
    {
       "info": {
          "dateTime": "2010-04-04 22:40:41",
          "depth": 10,
          "latitude": 32.128,
          "longitude": -115.303,
          "earthquakeId": "14607652",
          "magnitude": 7.2
      }
      "address": {
        "locality": "Test",
        "street": "Street"
        "postalCode": "34187"
      }
      "countryInfo:" {
          "distance": "0",
          "countryCode": "MX",
          "countryName": "Mexico"
       }
    },
    ...
  ]
}
 */
