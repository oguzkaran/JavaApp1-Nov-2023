package org.csystem.app.flight.data.repository;

import org.csystem.app.flight.data.entity.City;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
public class CityRepository implements ICityRepository {
    private static final String SAVE_SQL = "INSERT INTO cities (name) VALUES (:name)";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public CityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public <S extends City> S save(S entity)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    ///////////////////////////////////////////////////////////////////////

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void delete(City entity)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAll(Iterable<? extends City> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public void deleteById(Integer integer)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public boolean existsById(Integer integer)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<City> findAll()
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<City> findAllById(Iterable<Integer> integers)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Optional<City> findById(Integer integer)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
