package org.csystem.app.postalcode.data.service.mapper;

import javax.annotation.processing.Generated;
import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.service.dto.PostalCodeDTO;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodeInfo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-15T15:13:14+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PostalCodeMapperImpl implements IPostalCodeMapper {

    @Override
    public PostalCodeInfo toPostalCode(GeonamesPostalCodeInfo geonamesPostalCodeInfo) {
        if ( geonamesPostalCodeInfo == null ) {
            return null;
        }

        PostalCodeInfo postalCodeInfo = new PostalCodeInfo();

        postalCodeInfo.postalCodeValue = geonamesPostalCodeInfo.postalCode;
        postalCodeInfo.adminCode2 = geonamesPostalCodeInfo.adminCode2;
        postalCodeInfo.adminCode1 = geonamesPostalCodeInfo.adminCode1;
        postalCodeInfo.adminName2 = geonamesPostalCodeInfo.adminName2;
        postalCodeInfo.lng = geonamesPostalCodeInfo.lng;
        postalCodeInfo.countryCode = geonamesPostalCodeInfo.countryCode;
        postalCodeInfo.adminName1 = geonamesPostalCodeInfo.adminName1;
        postalCodeInfo.iSO31662 = geonamesPostalCodeInfo.iSO31662;
        postalCodeInfo.placeName = geonamesPostalCodeInfo.placeName;
        postalCodeInfo.lat = geonamesPostalCodeInfo.lat;
        postalCodeInfo.adminName3 = geonamesPostalCodeInfo.adminName3;
        postalCodeInfo.adminCode3 = geonamesPostalCodeInfo.adminCode3;

        return postalCodeInfo;
    }

    @Override
    public PostalCodeDTO toPostalCode(PostalCodeInfo postalCodeInfo) {
        if ( postalCodeInfo == null ) {
            return null;
        }

        PostalCodeDTO postalCodeDTO = new PostalCodeDTO();

        postalCodeDTO.adminCode2 = postalCodeInfo.adminCode2;
        postalCodeDTO.adminCode1 = postalCodeInfo.adminCode1;
        postalCodeDTO.adminName2 = postalCodeInfo.adminName2;
        postalCodeDTO.lng = postalCodeInfo.lng;
        postalCodeDTO.countryCode = postalCodeInfo.countryCode;
        postalCodeDTO.adminName1 = postalCodeInfo.adminName1;
        postalCodeDTO.iSO31662 = postalCodeInfo.iSO31662;
        postalCodeDTO.placeName = postalCodeInfo.placeName;
        postalCodeDTO.postalCodeValue = postalCodeInfo.postalCodeValue;
        postalCodeDTO.lat = postalCodeInfo.lat;
        postalCodeDTO.adminName3 = postalCodeInfo.adminName3;
        postalCodeDTO.adminCode3 = postalCodeInfo.adminCode3;

        return postalCodeDTO;
    }
}
