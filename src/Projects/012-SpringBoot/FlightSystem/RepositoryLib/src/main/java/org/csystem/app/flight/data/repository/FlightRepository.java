package org.csystem.app.flight.data.repository;

import org.csystem.app.flight.data.entity.Flight;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
public class FlightRepository implements IFlightRepository {
    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public FlightRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
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
    public void delete(Flight entity)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAll(Iterable<? extends Flight> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteById(String s)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public boolean existsById(String s)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<Flight> findAll()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<Flight> findAllById(Iterable<String> strings)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Optional<Flight> findById(String s)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public <S extends Flight> S save(S entity)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public <S extends Flight> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
