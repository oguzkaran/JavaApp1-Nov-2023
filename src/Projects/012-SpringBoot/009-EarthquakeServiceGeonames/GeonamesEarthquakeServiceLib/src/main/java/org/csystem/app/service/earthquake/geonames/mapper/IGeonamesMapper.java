package org.csystem.app.service.earthquake.geonames.mapper;

import org.csystem.app.service.earthquake.geonames.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "GeonamesMapperImpl", componentModel = "spring")
public interface IGeonamesMapper {

    @Mapping(source = "geonamesEarthquake.lat", target = "latitude")
    @Mapping(source = "geonamesEarthquake.lng", target = "longitude")
    @Mapping(source = "geonamesEarthquake.eqid", target = "earthquakeId")
    GeonamesEarthquakeDetailsInfo toGeonamesEarthquakeDetailsInfo(GeonamesEarthquake geonamesEarthquake);

    @Mapping(source = "geonamesAddress.postalcode", target = "postalCode")
    GeonamesEarthquakeDetailsAddress toGeonamesEarthquakeDetailsAddress(GeonamesAddress geonamesAddress);

    @Mapping(source = "geonamesCountryCode.distance", target = "distance")
    @Mapping(source = "geonamesCountryCode.countryCode", target = "countryCode")
    GeonamesEarthquakeDetailsCountryInfo toGeonamesEarthquakeDetailsCountryInfo(GeonamesCountryCode geonamesCountryCode);
}
