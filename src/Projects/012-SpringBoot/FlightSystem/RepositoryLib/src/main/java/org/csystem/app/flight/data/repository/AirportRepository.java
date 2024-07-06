package org.csystem.app.flight.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.entity.Airport;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class AirportRepository implements IAirportRepository {
    private static final String SAVE_SQL = "INSERT INTO airports (name, city_id) VALUES (:name, :cityId)";
    //...
    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public AirportRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    ///////////////////////////////////////////////////////////////////////

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void delete(Airport entity)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAll(Iterable<? extends Airport> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteById(Long aLong)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public boolean existsById(Long aLong)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<Airport> findAll()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<Airport> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Optional<Airport> findById(Long aLong)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public <S extends Airport> S save(S entity)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public <S extends Airport> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
