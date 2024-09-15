package org.csystem.app.postalcode.data.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class PostalCodeDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminCode2;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminCode1;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminName2;

    public double lng;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String countryCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminName1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String iSO31662;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String placeName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String postalCodeValue;

    public double lat;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminName3;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String adminCode3;
}
