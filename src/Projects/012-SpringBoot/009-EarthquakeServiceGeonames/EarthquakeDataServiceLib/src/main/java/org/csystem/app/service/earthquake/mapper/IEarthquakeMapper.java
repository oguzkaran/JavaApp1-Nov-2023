package org.csystem.app.service.earthquake.mapper;

import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.service.earthquake.dto.EarthquakeInfoDTO;
import org.csystem.app.service.earthquake.dto.EarthquakesDTO;
import org.csystem.app.service.earthquake.dto.RegionInfoDTO;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeDetails;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeInfoDetails;
import org.mapstruct.Mapper;

@Mapper(implementationName = "EarthquakeMapperImpl", componentModel = "spring")
public interface IEarthquakeMapper {
    EarthquakeInfoDTO toEarthquakeInfoDTO(EarthquakeInfo earthquakeInfo);
    EarthquakeInfo toEarthquakeInfo(EarthquakeInfoDTO earthquakeInfoDTO);
    EarthquakesDTO toEarthquakesDTO(EarthquakesInfo earthquakesInfo);
    EarthquakesInfo toEarthquakesInfo(EarthquakesDTO earthquakesDTO);
    default EarthquakeInfoDTO toEarthquakeInfoDTO(GeonamesEarthquakeDetails geonamesEarthquakeDetails)
    {
        var dto = new EarthquakeInfoDTO();

        dto.dateTime = geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo.dateTime;
        dto.depth = geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo.depth;
        dto.latitude = geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo.latitude;
        dto.longitude = geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo.longitude;
        dto.earthquakeId = geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo.earthquakeId;
        dto.magnitude = geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo.magnitude;
        dto.locality = geonamesEarthquakeDetails.geonamesEarthquakeDetailsAddress.locality;
        dto.street = geonamesEarthquakeDetails.geonamesEarthquakeDetailsAddress.street;
        dto.postalCode = geonamesEarthquakeDetails.geonamesEarthquakeDetailsAddress.postalCode;
        dto.distance = geonamesEarthquakeDetails.m_geonamesEarthquakeDetailsCountryInfo.distance;
        dto.countryCode = geonamesEarthquakeDetails.m_geonamesEarthquakeDetailsCountryInfo.countryCode;
        dto.countryName = geonamesEarthquakeDetails.m_geonamesEarthquakeDetailsCountryInfo.countryName;

        return dto;
    }

    EarthquakesDTO toEarthquakeInfoDTO(GeonamesEarthquakeInfoDetails geonamesEarthquakeInfoDetails);

    RegionInfoDTO toRegionInfoDTO(RegionInfo regionInfo);
}
