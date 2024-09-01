package org.csystem.app.postalcode.data.mapper;

import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodeInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostalCodeMapper implements IPostalCodeMapper {
    @Override
    public PostalCodeInfo toPostalCodeInfo(GeonamesPostalCodeInfo geonamesPostalCodeInfo)
    {
        var postalCodeInfo = new PostalCodeInfo();

        postalCodeInfo.adminCode2 = geonamesPostalCodeInfo.adminCode2;
        postalCodeInfo.adminCode1 = geonamesPostalCodeInfo.adminCode1;
        postalCodeInfo.adminName2 = geonamesPostalCodeInfo.adminName2;
        postalCodeInfo.lng = geonamesPostalCodeInfo.lng;
        postalCodeInfo.countryCode = geonamesPostalCodeInfo.countryCode;
        postalCodeInfo.postalCodeValue = geonamesPostalCodeInfo.postalCode;
        postalCodeInfo.adminName1 = geonamesPostalCodeInfo.adminName1;
        postalCodeInfo.iSO31662 = geonamesPostalCodeInfo.iSO3166_2;
        postalCodeInfo.placeName = geonamesPostalCodeInfo.placeName;
        postalCodeInfo.lat = geonamesPostalCodeInfo.lat;
        postalCodeInfo.adminCode3 = geonamesPostalCodeInfo.adminCode3;
        postalCodeInfo.adminName3 = geonamesPostalCodeInfo.adminName3;

        return postalCodeInfo;
    }

    @Override
    public Set<PostalCodeInfo> toPostalCodeInfo(List<GeonamesPostalCodeInfo> geonamesPostalCodeInfo)
    {
        return geonamesPostalCodeInfo.stream().map(this::toPostalCodeInfo).collect(Collectors.toSet());
    }
}
