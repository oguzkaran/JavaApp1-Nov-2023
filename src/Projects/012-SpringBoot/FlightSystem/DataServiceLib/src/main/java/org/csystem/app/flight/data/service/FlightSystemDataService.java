package org.csystem.app.flight.data.service;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.dal.FlightSystemDataHelper;
import org.csystem.app.flight.data.service.dto.CityDTO;
import org.csystem.app.flight.data.service.dto.CitySaveDTO;
import org.csystem.app.flight.data.service.dto.CityUpdateDTO;
import org.csystem.app.flight.data.service.mapper.MapperInject;
import org.csystem.data.exception.repository.RepositoryException;
import org.csystem.data.exception.service.DataServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FlightSystemDataService {
    private final FlightSystemDataHelper m_flightSystemDataHelper;
    private final MapperInject m_mapperInject;

    public FlightSystemDataService(FlightSystemDataHelper flightSystemDataHelper, MapperInject mapperInject)
    {
        m_flightSystemDataHelper = flightSystemDataHelper;
        m_mapperInject = mapperInject;
    }

    public void deleteCityById(long id)
    {
        throw new UnsupportedOperationException("TODO: Buğrahan Kısa, Fatih Karabulut");
    }

    public Iterable<CityDTO> findAllCities()
    {
        throw new UnsupportedOperationException("TODO: Buğrahan Kısa, Fatih Karabulut");
    }

    public Optional<CityDTO> findCityById(long id)
    {
        throw new UnsupportedOperationException("TODO: Buğrahan Kısa, Fatih Karabulut");
    }

    public Optional<CityDTO> findCityByName(String name)
    {
        throw new UnsupportedOperationException("TODO: Buğrahan Kısa, Fatih Karabulut");
    }

    public CitySaveDTO saveCity(CitySaveDTO citySaveDTO)
    {
        try {
            log.info("FlightSystemDataService.saveCity: City -> {}", citySaveDTO);

            m_flightSystemDataHelper.saveCity(m_mapperInject.getCityMapper().toCity(citySaveDTO));

            return citySaveDTO;
        }
        catch (RepositoryException ex) {
            log.error("FlightSystemDataService.saveCity: City -> {}, Exception -> RepositoryException, Message: {}",
                    citySaveDTO, ex.getMessage());
            throw new DataServiceException("FlightSystemDataService.saveCity: RepositoryException", ex);
        }
        catch (Throwable ex) {
            log.error("FlightSystemDataService.saveCity: City -> {}, Exception -> {}, Message: {}",
                    citySaveDTO, ex.getClass().getName(), ex.getMessage());
            throw new DataServiceException("FlightSystemDataService.saveCity: Exception", ex);
        }
    }

    public CityUpdateDTO updateCity(CityUpdateDTO cityUpdateDTO)
    {
        throw new UnsupportedOperationException("TODO: Buğrahan Kısa, Fatih Karabulut");
    }

    //...
}
