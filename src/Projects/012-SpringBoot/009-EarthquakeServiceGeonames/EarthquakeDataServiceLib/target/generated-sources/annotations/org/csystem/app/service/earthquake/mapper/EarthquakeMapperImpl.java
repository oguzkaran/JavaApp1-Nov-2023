package org.csystem.app.service.earthquake.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.app.service.earthquake.dto.EarthquakeInfoDTO;
import org.csystem.app.service.earthquake.dto.EarthquakesDTO;
import org.csystem.app.service.earthquake.dto.RegionInfoDTO;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeDetails;
import org.csystem.app.service.earthquake.geonames.dto.GeonamesEarthquakeInfoDetails;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-05T15:50:01+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class EarthquakeMapperImpl implements IEarthquakeMapper {

    @Override
    public EarthquakeInfoDTO toEarthquakeInfoDTO(EarthquakeInfo earthquakeInfo) {
        if ( earthquakeInfo == null ) {
            return null;
        }

        EarthquakeInfoDTO earthquakeInfoDTO = new EarthquakeInfoDTO();

        earthquakeInfoDTO.dateTime = earthquakeInfo.dateTime;
        earthquakeInfoDTO.depth = earthquakeInfo.depth;
        earthquakeInfoDTO.latitude = earthquakeInfo.latitude;
        earthquakeInfoDTO.longitude = earthquakeInfo.longitude;
        earthquakeInfoDTO.earthquakeId = earthquakeInfo.earthquakeId;
        earthquakeInfoDTO.magnitude = earthquakeInfo.magnitude;
        earthquakeInfoDTO.locality = earthquakeInfo.locality;
        earthquakeInfoDTO.street = earthquakeInfo.street;
        earthquakeInfoDTO.postalCode = earthquakeInfo.postalCode;
        earthquakeInfoDTO.distance = earthquakeInfo.distance;
        earthquakeInfoDTO.countryCode = earthquakeInfo.countryCode;
        earthquakeInfoDTO.countryName = earthquakeInfo.countryName;

        return earthquakeInfoDTO;
    }

    @Override
    public EarthquakeInfo toEarthquakeInfo(EarthquakeInfoDTO earthquakeInfoDTO) {
        if ( earthquakeInfoDTO == null ) {
            return null;
        }

        EarthquakeInfo.EarthquakeInfoBuilder earthquakeInfo = EarthquakeInfo.builder();

        earthquakeInfo.dateTime( earthquakeInfoDTO.dateTime );
        earthquakeInfo.depth( earthquakeInfoDTO.depth );
        earthquakeInfo.latitude( earthquakeInfoDTO.latitude );
        earthquakeInfo.longitude( earthquakeInfoDTO.longitude );
        earthquakeInfo.earthquakeId( earthquakeInfoDTO.earthquakeId );
        earthquakeInfo.magnitude( earthquakeInfoDTO.magnitude );
        earthquakeInfo.locality( earthquakeInfoDTO.locality );
        earthquakeInfo.street( earthquakeInfoDTO.street );
        earthquakeInfo.postalCode( earthquakeInfoDTO.postalCode );
        earthquakeInfo.distance( earthquakeInfoDTO.distance );
        earthquakeInfo.countryCode( earthquakeInfoDTO.countryCode );
        earthquakeInfo.countryName( earthquakeInfoDTO.countryName );

        return earthquakeInfo.build();
    }

    @Override
    public EarthquakesDTO toEarthquakesDTO(EarthquakesInfo earthquakesInfo) {
        if ( earthquakesInfo == null ) {
            return null;
        }

        EarthquakesDTO earthquakesDTO = new EarthquakesDTO();

        earthquakesDTO.earthquakes = earthquakeInfoListToEarthquakeInfoDTOList( earthquakesInfo.earthquakes );

        return earthquakesDTO;
    }

    @Override
    public EarthquakesInfo toEarthquakesInfo(EarthquakesDTO earthquakesDTO) {
        if ( earthquakesDTO == null ) {
            return null;
        }

        EarthquakesInfo.EarthquakesInfoBuilder earthquakesInfo = EarthquakesInfo.builder();

        earthquakesInfo.earthquakes( earthquakeInfoDTOListToEarthquakeInfoList( earthquakesDTO.earthquakes ) );

        return earthquakesInfo.build();
    }

    @Override
    public EarthquakesDTO toEarthquakeInfoDTO(GeonamesEarthquakeInfoDetails geonamesEarthquakeInfoDetails) {
        if ( geonamesEarthquakeInfoDetails == null ) {
            return null;
        }

        EarthquakesDTO earthquakesDTO = new EarthquakesDTO();

        earthquakesDTO.earthquakes = geonamesEarthquakeDetailsListToEarthquakeInfoDTOList( geonamesEarthquakeInfoDetails.earthquakes );

        return earthquakesDTO;
    }

    @Override
    public RegionInfoDTO toRegionInfoDTO(RegionInfo regionInfo) {
        if ( regionInfo == null ) {
            return null;
        }

        RegionInfoDTO regionInfoDTO = new RegionInfoDTO();

        regionInfoDTO.east = regionInfo.getEast();
        regionInfoDTO.west = regionInfo.getWest();
        regionInfoDTO.north = regionInfo.getNorth();
        regionInfoDTO.south = regionInfo.getSouth();
        regionInfoDTO.queryDateTime = regionInfo.getQueryDateTime();

        return regionInfoDTO;
    }

    protected List<EarthquakeInfoDTO> earthquakeInfoListToEarthquakeInfoDTOList(List<EarthquakeInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<EarthquakeInfoDTO> list1 = new ArrayList<EarthquakeInfoDTO>( list.size() );
        for ( EarthquakeInfo earthquakeInfo : list ) {
            list1.add( toEarthquakeInfoDTO( earthquakeInfo ) );
        }

        return list1;
    }

    protected List<EarthquakeInfo> earthquakeInfoDTOListToEarthquakeInfoList(List<EarthquakeInfoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<EarthquakeInfo> list1 = new ArrayList<EarthquakeInfo>( list.size() );
        for ( EarthquakeInfoDTO earthquakeInfoDTO : list ) {
            list1.add( toEarthquakeInfo( earthquakeInfoDTO ) );
        }

        return list1;
    }

    protected List<EarthquakeInfoDTO> geonamesEarthquakeDetailsListToEarthquakeInfoDTOList(List<GeonamesEarthquakeDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<EarthquakeInfoDTO> list1 = new ArrayList<EarthquakeInfoDTO>( list.size() );
        for ( GeonamesEarthquakeDetails geonamesEarthquakeDetails : list ) {
            list1.add( toEarthquakeInfoDTO( geonamesEarthquakeDetails ) );
        }

        return list1;
    }
}
