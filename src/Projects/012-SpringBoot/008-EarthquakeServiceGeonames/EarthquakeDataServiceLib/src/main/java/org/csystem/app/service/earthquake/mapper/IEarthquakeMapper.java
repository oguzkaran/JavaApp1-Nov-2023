package org.csystem.app.service.earthquake.mapper;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.app.service.earthquake.dto.*;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeInfoDetails;
import org.mapstruct.Mapper;

@Mapper(implementationName = "EarthquakeMapperImpl", componentModel = "spring")
public interface IEarthquakeMapper {
    EarthquakeInfo toEarthquakeInfo(EarthquakeDetailsInfo earthquakeDetailsInfo);
    EarthquakeAddress toEarthquakeAddress(EarthquakeDetailsAddress earthquakeDetailsAddress);
    EarthquakeCountryInfo toEarthquakeCountryInfo(EarthquakeDetailsCountryInfo earthquakeDetailsCountryInfo);

    EarthquakesDetails toEarthquakeDetails(GeonamesEarthquakeInfoDetails geonamesEarthquakeDetails);

    EarthquakesDetails toEarthquakeDetails(EarthquakesInfo earthquakesInfo);

    default EarthquakeInfoSave toEarthquakeInfoSave(EarthquakeDetails earthquakeDetails)
    {
        var earthquakeInfoSave = new EarthquakeInfoSave();

        earthquakeInfoSave.earthquakeInfo = toEarthquakeInfo(earthquakeDetails.earthquakeDetailsInfo);
        earthquakeInfoSave.earthquakeAddress = toEarthquakeAddress(earthquakeDetails.earthquakeDetailsAddress);
        earthquakeInfoSave.earthquakeCountryInfo = toEarthquakeCountryInfo(earthquakeDetails.earthquakeDetailsCountryInfo);

        return earthquakeInfoSave;
    }
}
