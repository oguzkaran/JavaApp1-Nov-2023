package org.csystem.app.service.earthquake.mapper;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.app.service.earthquake.dto.*;
import org.csystem.app.service.earthquake.geonames.dto.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(implementationName = "EarthquakeMapperImpl", componentModel = "spring")
public interface IEarthquakeMapper {
    EarthquakeInfo toEarthquakeInfo(EarthquakeDetailsInfo earthquakeDetailsInfo);
    EarthquakeAddress toEarthquakeAddress(EarthquakeDetailsAddress earthquakeDetailsAddress);
    EarthquakeCountryInfo toEarthquakeCountryInfo(EarthquakeDetailsCountryInfo earthquakeDetailsCountryInfo);

    EarthquakeDetailsInfo toEarthquakeDetailsInfo(GeonamesEarthquakeDetailsInfo geonamesEarthquakeDetails);
    EarthquakeDetailsAddress toEarthquakeDetailsAddress(GeonamesEarthquakeDetailsAddress geonamesEarthquakeDetailsAddress);
    EarthquakeDetailsCountryInfo toEarthquakeDetailsCountryInfo(GeonamesEarthquakeDetailsCountryInfo geonamesEarthquakeDetailsCountryInfo);

    EarthquakeDetailsInfo toEarthquakeDetailsInfo(EarthquakeInfo earthquakeInfo);
    EarthquakeDetailsAddress toEarthquakeDetailsAddress(EarthquakeAddress earthquakeAddress);
    EarthquakeDetailsCountryInfo toEarthquakeDetailsCountryInfo(EarthquakeCountryInfo earthquakeCountryInfo);

    EarthquakesDetails toEarthquakesDetails(GeonamesEarthquakeInfoDetails geonamesEarthquakeDetails);
    EarthquakesDetails toEarthquakeDetails(EarthquakesInfo earthquakesInfo);

    List<EarthquakeInfoSave> toEarthquakesInfoSaves(List<EarthquakeDetails> earthquakeDetails);

    default EarthquakeDetails toEarthquakeDetails(GeonamesEarthquakeDetails geonamesEarthquakeDetails)

    {
        var earthquakeDetails = new EarthquakeDetails();

        earthquakeDetails.earthquakeDetailsInfo = toEarthquakeDetailsInfo(geonamesEarthquakeDetails.geonamesEarthquakeDetailsInfo);
        earthquakeDetails.earthquakeDetailsAddress = toEarthquakeDetailsAddress(geonamesEarthquakeDetails.geonamesEarthquakeDetailsAddress);
        earthquakeDetails.earthquakeDetailsCountryInfo = toEarthquakeDetailsCountryInfo(geonamesEarthquakeDetails.m_geonamesEarthquakeDetailsCountryInfo);

        return earthquakeDetails;
    }

    default EarthquakeDetails toEarthquakeDetails(EarthquakeInfoDetails earthquakeInfoDetails)
    {
        var earthquakeDetails = new EarthquakeDetails();

        earthquakeDetails.earthquakeDetailsInfo = toEarthquakeDetailsInfo(earthquakeInfoDetails.earthquakeInfo);
        earthquakeDetails.earthquakeDetailsAddress = toEarthquakeDetailsAddress(earthquakeInfoDetails.earthquakeAddress);
        earthquakeDetails.earthquakeDetailsCountryInfo = toEarthquakeDetailsCountryInfo(earthquakeInfoDetails.earthquakeCountryInfo);

        return earthquakeDetails;
    }

    default EarthquakeInfoSave toEarthquakeInfoSave(EarthquakeDetails earthquakeDetails)
    {
        var earthquakeInfoSave = new EarthquakeInfoSave();

        earthquakeInfoSave.earthquakeInfo = toEarthquakeInfo(earthquakeDetails.earthquakeDetailsInfo);
        earthquakeInfoSave.earthquakeAddress = toEarthquakeAddress(earthquakeDetails.earthquakeDetailsAddress);
        earthquakeInfoSave.earthquakeCountryInfo = toEarthquakeCountryInfo(earthquakeDetails.earthquakeDetailsCountryInfo);

        return earthquakeInfoSave;
    }


}
