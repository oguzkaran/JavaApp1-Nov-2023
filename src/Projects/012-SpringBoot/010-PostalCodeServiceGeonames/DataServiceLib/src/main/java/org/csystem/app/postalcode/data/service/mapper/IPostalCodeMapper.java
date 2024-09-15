package org.csystem.app.postalcode.data.service.mapper;

import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.service.dto.PostalCodeDTO;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "PostalCodeMapperImpl", componentModel = "spring")
public interface IPostalCodeMapper {
    @Mapping(source = "postalCode", target = "postalCodeValue")
    @Mapping(target = "postalCode", ignore = true)
    @Mapping(target = "id", ignore = true)
    PostalCodeInfo toPostalCode(GeonamesPostalCodeInfo geonamesPostalCodeInfo);

    PostalCodeDTO toPostalCode(PostalCodeInfo postalCodeInfo);
}
