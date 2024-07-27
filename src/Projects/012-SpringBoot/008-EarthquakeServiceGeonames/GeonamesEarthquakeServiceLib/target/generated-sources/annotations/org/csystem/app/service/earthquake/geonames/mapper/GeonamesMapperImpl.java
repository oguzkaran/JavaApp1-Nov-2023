package org.csystem.app.service.earthquake.geonames.mapper;

import javax.annotation.processing.Generated;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesAddress;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesCountryCode;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuake;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeDetails;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-27T15:58:13+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class GeonamesMapperImpl implements IGeonamesMapper {

    @Override
    public GeonamesEarthQuakeDetails toGeonamesEarthQuakeDetails(GeonamesEarthQuake geonamesEarthQuake, GeonamesAddress geonamesAddress, GeonamesCountryCode geonamesCountryCode) {
        if ( geonamesEarthQuake == null && geonamesAddress == null && geonamesCountryCode == null ) {
            return null;
        }

        GeonamesEarthQuakeDetails geonamesEarthQuakeDetails = new GeonamesEarthQuakeDetails();

        if ( geonamesEarthQuake != null ) {
            geonamesEarthQuakeDetails.latitude = geonamesEarthQuake.lat;
            geonamesEarthQuakeDetails.longitude = geonamesEarthQuake.lng;
            geonamesEarthQuakeDetails.earthquakeId = geonamesEarthQuake.eqid;
            geonamesEarthQuakeDetails.dateTime = geonamesEarthQuake.dateTime;
            geonamesEarthQuakeDetails.depth = geonamesEarthQuake.depth;
            geonamesEarthQuakeDetails.magnitude = geonamesEarthQuake.magnitude;
        }
        if ( geonamesAddress != null ) {
            geonamesEarthQuakeDetails.postalCode = geonamesAddress.postalcode;
            geonamesEarthQuakeDetails.locality = geonamesAddress.locality;
            geonamesEarthQuakeDetails.street = geonamesAddress.street;
        }
        if ( geonamesCountryCode != null ) {
            geonamesEarthQuakeDetails.distance = geonamesCountryCode.distance;
            geonamesEarthQuakeDetails.countryCode = geonamesCountryCode.countryCode;
            geonamesEarthQuakeDetails.countryName = geonamesCountryCode.countryName;
        }

        return geonamesEarthQuakeDetails;
    }
}
