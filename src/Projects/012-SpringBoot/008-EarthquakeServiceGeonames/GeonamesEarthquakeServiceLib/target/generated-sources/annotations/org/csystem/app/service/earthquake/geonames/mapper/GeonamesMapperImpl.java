package org.csystem.app.service.earthquake.geonames.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuake;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeDetails;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeInfo;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeInfoDetails;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T16:07:22+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class GeonamesMapperImpl implements IGeonamesMapper {

    @Override
    public GeonamesEarthQuakeInfoDetails toGeonamesEarthQuakeInfoDetails(GeonamesEarthQuakeInfo geonamesEarthQuakeInfo) {
        if ( geonamesEarthQuakeInfo == null ) {
            return null;
        }

        GeonamesEarthQuakeInfoDetails geonamesEarthQuakeInfoDetails = new GeonamesEarthQuakeInfoDetails();

        geonamesEarthQuakeInfoDetails.earthquakes = geonamesEarthQuakeListToGeonamesEarthQuakeDetailsList( geonamesEarthQuakeInfo.earthquakes );

        return geonamesEarthQuakeInfoDetails;
    }

    protected GeonamesEarthQuakeDetails geonamesEarthQuakeToGeonamesEarthQuakeDetails(GeonamesEarthQuake geonamesEarthQuake) {
        if ( geonamesEarthQuake == null ) {
            return null;
        }

        GeonamesEarthQuakeDetails geonamesEarthQuakeDetails = new GeonamesEarthQuakeDetails();

        geonamesEarthQuakeDetails.dateTime = geonamesEarthQuake.dateTime;
        geonamesEarthQuakeDetails.depth = geonamesEarthQuake.depth;
        geonamesEarthQuakeDetails.lat = geonamesEarthQuake.lat;
        geonamesEarthQuakeDetails.lng = geonamesEarthQuake.lng;
        geonamesEarthQuakeDetails.magnitude = geonamesEarthQuake.magnitude;

        return geonamesEarthQuakeDetails;
    }

    protected List<GeonamesEarthQuakeDetails> geonamesEarthQuakeListToGeonamesEarthQuakeDetailsList(List<GeonamesEarthQuake> list) {
        if ( list == null ) {
            return null;
        }

        List<GeonamesEarthQuakeDetails> list1 = new ArrayList<GeonamesEarthQuakeDetails>( list.size() );
        for ( GeonamesEarthQuake geonamesEarthQuake : list ) {
            list1.add( geonamesEarthQuakeToGeonamesEarthQuakeDetails( geonamesEarthQuake ) );
        }

        return list1;
    }
}
