package org.csystem.app.flight.data.dal;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.entity.City;
import org.csystem.app.flight.data.repository.IAirportRepository;
import org.csystem.app.flight.data.repository.ICityRepository;
import org.csystem.app.flight.data.repository.IFlightRepository;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Lazy
@Slf4j
public class FlightSystemDataHelper {
    private final ICityRepository m_cityRepository;
    private final IAirportRepository m_airportRepository;
    private final IFlightRepository m_flightRepository;

    public FlightSystemDataHelper(ICityRepository cityRepository, IAirportRepository airportRepository,
                                  IFlightRepository flightRepository)
    {
        m_cityRepository = cityRepository;
        m_airportRepository = airportRepository;
        m_flightRepository = flightRepository;
    }

    public Iterable<City> findAllCities()
    {
        try {
            log.info("FlightSystemDataHelper.findAllCities");
            return m_cityRepository.findAll();
        }
        catch (Throwable ex) {
            log.error("FlightSystemDataHelper.findAllCities: Exception -> {}, Message -> {}",
                    ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("FlightSystemDataHelper.findAllCities", ex);
        }
    }

    public Optional<City> findCityById(int id)
    {
        try {
            log.info("FlightSystemDataHelper.findCityById: id ->{}", id);
            return m_cityRepository.findById(id);
        }
        catch (Throwable ex) {
            log.error("FlightSystemDataHelper.findCityById: id -> {}, Exception -> {}, Message -> {}", id,
                    ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("FlightSystemDataHelper.findCityById", ex);
        }
    }

    public City saveCity(City city)
    {
        try {
            log.info("FlightSystemDataHelper.saveCity:{}", city.toString());
            return m_cityRepository.save(city);
        }
        catch (Throwable ex) {
            log.error("FlightSystemDataHelper.saveCity: City -> {}, Exception -> {}, Message -> {}", city.toString(),
                    ex.getClass().getSimpleName(), ex.getMessage());
            throw new RepositoryException("FlightSystemDataHelper.saveCity", ex);
        }
    }

    //...
}
