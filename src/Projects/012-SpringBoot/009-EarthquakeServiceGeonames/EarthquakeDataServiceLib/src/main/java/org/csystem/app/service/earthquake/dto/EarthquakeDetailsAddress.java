package org.csystem.app.service.earthquake.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

@ToString
public class EarthquakeDetailsAddress {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String locality;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String street;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String postalCode;
}
