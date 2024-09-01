package org.csystem.app.postalcode.data.mapper;


import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.csystem.app.postalcode.geonames.dto.GeonamesPostalCodeInfo;

import java.util.List;
import java.util.Set;

public interface IPostalCodeMapper {
    PostalCodeInfo toPostalCodeInfo(GeonamesPostalCodeInfo geonamesPostalCodeInfo);
    Set<PostalCodeInfo> toPostalCodeInfo(List<GeonamesPostalCodeInfo> geonamesPostalCodeInfo);
}
