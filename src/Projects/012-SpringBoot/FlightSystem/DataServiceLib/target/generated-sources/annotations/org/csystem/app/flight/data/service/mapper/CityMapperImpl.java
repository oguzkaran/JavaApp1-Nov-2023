package org.csystem.app.flight.data.service.mapper;

import javax.annotation.processing.Generated;
import org.csystem.app.flight.data.entity.City;
import org.csystem.app.flight.data.service.dto.CityDTO;
import org.csystem.app.flight.data.service.dto.CitySaveDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-13T16:05:19+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements ICityMapper {

    @Override
    public City toCity(CitySaveDTO citySaveDTO) {
        if ( citySaveDTO == null ) {
            return null;
        }

        String name = null;

        name = citySaveDTO.getName();

        long id = 0L;

        City city = new City( id, name );

        return city;
    }

    @Override
    public CityDTO toCityDTO(City city) {
        if ( city == null ) {
            return null;
        }

        long id = 0L;
        String name = null;

        id = city.getId();
        name = city.getName();

        CityDTO cityDTO = new CityDTO( id, name );

        return cityDTO;
    }
}
