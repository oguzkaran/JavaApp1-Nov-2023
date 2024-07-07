package org.csystem.app.flight.data.service.mapper;

import org.csystem.app.flight.data.entity.City;
import org.csystem.app.flight.data.service.dto.CityDTO;
import org.csystem.app.flight.data.service.dto.CitySaveDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "CityMapperImpl")
public interface ICityMapper {
    City toCity(CitySaveDTO citySaveDTO);
    CityDTO toCityDTO(City city);
}
