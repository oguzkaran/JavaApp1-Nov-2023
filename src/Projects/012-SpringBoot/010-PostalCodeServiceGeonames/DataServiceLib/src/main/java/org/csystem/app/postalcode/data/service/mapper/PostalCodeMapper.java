package org.csystem.app.postalcode.data.service.mapper;

import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.data.service.dto.PostalCode;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodeInfo;
import org.springframework.stereotype.Component;

@Component
public class PostalCodeMapper implements IPostalCodeMapper {
    @Override
    public PostalCode toPostalCode(GeonamesPostalCodeInfo geonamesPostalCodeInfo)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public PostalCode toPostalCode(PostalCodeInfo postalCodeInfo)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
