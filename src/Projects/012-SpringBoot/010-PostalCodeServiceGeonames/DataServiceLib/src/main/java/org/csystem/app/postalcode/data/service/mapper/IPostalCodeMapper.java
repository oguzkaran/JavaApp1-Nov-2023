package org.csystem.app.postalcode.data.service.mapper;

import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.service.dto.PostalCode;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodeInfo;

public interface IPostalCodeMapper {
    PostalCode toPostalCode(GeonamesPostalCodeInfo geonamesPostalCodeInfo);
    PostalCode toPostalCode(PostalCodeInfo postalCodeInfo);
}
