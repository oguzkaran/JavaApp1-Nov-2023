package org.csystem.app.flight.data.service.mapper;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MapperInject {
    @Accessors(prefix = "m_")
    private final ICityMapper m_cityMapper;
    @Accessors(prefix = "m_")
    private final IAirportMapper m_airportMapper;
    @Accessors(prefix = "m_")
    private final IFlightMapper m_FlightMapper;

    public MapperInject(ICityMapper cityMapper, IAirportMapper airportMapper, IFlightMapper flightMapper)
    {
        m_cityMapper = cityMapper;
        m_airportMapper = airportMapper;
        m_FlightMapper = flightMapper;
    }
}
