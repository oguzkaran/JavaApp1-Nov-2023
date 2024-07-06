package org.csystem.app.flight.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.entity.City;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class CityRepository implements ICityRepository {
    private static final String SAVE_SQL = "INSERT INTO cities (name) VALUES (:name)";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM cities where id = :id";
    private static final String FIND_ALL_SQL = "SELECT * FROM cities";

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;


    private void fillCities(ArrayList<City> cities, ResultSet rs) throws SQLException
    {
        do
            cities.add(new City(rs.getLong(1), rs.getString(2)));
        while (rs.next());
    }

    public CityRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<City> findAll()
    {
        log.info("CityRepository.findAll");
        var cities = new ArrayList<City>();

        m_namedParameterJdbcTemplate.query(FIND_ALL_SQL, rs -> {fillCities(cities, rs);});

        return cities;
    }

    @Override
    public Optional<City> findById(Integer id)
    {
        var cities = new ArrayList<City>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", id);

        m_namedParameterJdbcTemplate.query(FIND_BY_ID_SQL, paramMap, (ResultSet rs) -> fillCities(cities, rs));

        return cities.isEmpty() ? Optional.empty() : Optional.of(cities.get(0));
    }

    @Override
    public <S extends City> S save(S city)
    {
        var parameterSource = new BeanPropertySqlParameterSource(city);

        m_namedParameterJdbcTemplate.update(SAVE_SQL, parameterSource);

        return city;
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
    public Iterable<City> findAllById(Iterable<Integer> integers)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }



    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
