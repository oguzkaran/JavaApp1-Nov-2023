package org.csystem.app.service.earthquake.geonames.mapper;

import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeInfo;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthQuakeInfoDetails;
import org.mapstruct.Mapper;

@Mapper(implementationName = "GeonamesMapperImpl", componentModel = "spring")
public interface IGeonamesMapper {
    GeonamesEarthQuakeInfoDetails toGeonamesEarthQuakeInfoDetails(GeonamesEarthQuakeInfo geonamesEarthQuakeInfo);
}
