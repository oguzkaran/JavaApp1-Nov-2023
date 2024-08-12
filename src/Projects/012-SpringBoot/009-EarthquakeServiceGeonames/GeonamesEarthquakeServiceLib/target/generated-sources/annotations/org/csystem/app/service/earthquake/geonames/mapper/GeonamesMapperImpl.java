package org.csystem.app.service.earthquake.geonames.mapper;

import javax.annotation.processing.Generated;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesAddress;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesCountryCode;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquake;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeDetailsAddress;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeDetailsCountryInfo;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeDetailsInfo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-12T21:20:44+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class GeonamesMapperImpl implements IGeonamesMapper {

    @Override
    public GeonamesEarthquakeDetailsInfo toGeonamesEarthquakeDetailsInfo(GeonamesEarthquake geonamesEarthquake) {
        if ( geonamesEarthquake == null ) {
            return null;
        }

        GeonamesEarthquakeDetailsInfo geonamesEarthquakeDetailsInfo = new GeonamesEarthquakeDetailsInfo();

        geonamesEarthquakeDetailsInfo.latitude = geonamesEarthquake.lat;
        geonamesEarthquakeDetailsInfo.longitude = geonamesEarthquake.lng;
        geonamesEarthquakeDetailsInfo.earthquakeId = geonamesEarthquake.eqid;
        geonamesEarthquakeDetailsInfo.dateTime = geonamesEarthquake.dateTime;
        geonamesEarthquakeDetailsInfo.depth = geonamesEarthquake.depth;
        geonamesEarthquakeDetailsInfo.magnitude = geonamesEarthquake.magnitude;

        return geonamesEarthquakeDetailsInfo;
    }

    @Override
    public GeonamesEarthquakeDetailsAddress toGeonamesEarthquakeDetailsAddress(GeonamesAddress geonamesAddress) {
        if ( geonamesAddress == null ) {
            return null;
        }

        GeonamesEarthquakeDetailsAddress geonamesEarthquakeDetailsAddress = new GeonamesEarthquakeDetailsAddress();

        geonamesEarthquakeDetailsAddress.postalCode = geonamesAddress.postalcode;
        geonamesEarthquakeDetailsAddress.locality = geonamesAddress.locality;
        geonamesEarthquakeDetailsAddress.street = geonamesAddress.street;

        return geonamesEarthquakeDetailsAddress;
    }

    @Override
    public GeonamesEarthquakeDetailsCountryInfo toGeonamesEarthquakeDetailsCountryInfo(GeonamesCountryCode geonamesCountryCode) {
        if ( geonamesCountryCode == null ) {
            return null;
        }

        GeonamesEarthquakeDetailsCountryInfo geonamesEarthquakeDetailsCountryInfo = new GeonamesEarthquakeDetailsCountryInfo();

        geonamesEarthquakeDetailsCountryInfo.distance = geonamesCountryCode.distance;
        geonamesEarthquakeDetailsCountryInfo.countryCode = geonamesCountryCode.countryCode;
        geonamesEarthquakeDetailsCountryInfo.countryName = geonamesCountryCode.countryName;

        return geonamesEarthquakeDetailsCountryInfo;
    }
}
