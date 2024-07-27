package org.csystem.app.service.earthquake.geonames.mapper;

import org.csystem.app.service.earthquake.geonames.dto.GeonamesAddress;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesCountryCode;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuake;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "GeonamesMapperImpl", componentModel = "spring")
public interface IGeonamesMapper {
    @Mapping(source = "geonamesEarthQuake.lat", target = "latitude")
    @Mapping(source = "geonamesEarthQuake.lng", target = "longitude")
    @Mapping(source = "geonamesEarthQuake.eqid", target = "earthquakeId")
    @Mapping(source = "geonamesAddress.postalcode", target = "postalCode")
    @Mapping(source = "geonamesCountryCode.distance", target = "distance")
    @Mapping(source = "geonamesCountryCode.countryCode", target = "countryCode")
    GeonamesEarthQuakeDetails toGeonamesEarthQuakeDetails(GeonamesEarthQuake geonamesEarthQuake,
                                                          GeonamesAddress geonamesAddress,
                                                          GeonamesCountryCode geonamesCountryCode);
}
