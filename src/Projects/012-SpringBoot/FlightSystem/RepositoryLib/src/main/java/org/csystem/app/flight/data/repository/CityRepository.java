package org.csystem.app.flight.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.entity.City;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
    private static final String DELETE_BY_ID_SQL = "CALL sp_delete_city_by_id(:id)";
    private static final String FIND_ALL_SQL = "SELECT * FROM find_all_cities()";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM find_city_by_id(:id)";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM find_city_by_name(:name)";
    private static final String SAVE_SQL = "INSERT INTO cities (name) VALUES (:name)";

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
    public void deleteById(Long id)
    {
        log.info("CityRepository.deleteByID -> city_id:{}", id);

        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", id);

        m_namedParameterJdbcTemplate.update(DELETE_BY_ID_SQL, paramMap);
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
    public Optional<City> findById(Long id)
    {
        log.info("CityRepository.findById -> city_id:{}", id);
        var cities = new ArrayList<City>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("id", id);

        m_namedParameterJdbcTemplate.query(FIND_BY_ID_SQL, paramMap, (ResultSet rs) -> fillCities(cities, rs));

        return cities.isEmpty() ? Optional.empty() : Optional.of(cities.get(0));
    }

    @Override
    public Iterable<City> findByName(String name)
    {
        log.info("CityRepository.findByName -> name:{}", name);
        var cities = new ArrayList<City>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("name", name);

        m_namedParameterJdbcTemplate.query(FIND_BY_NAME_SQL, paramMap, (ResultSet rs) -> fillCities(cities, rs));

        return cities;
    }

    @Override
    public <S extends City> S save(S city)
    {
        log.info("CityRepository.save -> city:{}", city.toString());
        var parameterSource = new BeanPropertySqlParameterSource(city);
        var keyHolder = new GeneratedKeyHolder();

        m_namedParameterJdbcTemplate.update(SAVE_SQL, parameterSource, keyHolder);
        city.setId((long)keyHolder.getKeys().get("city_id"));

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
    public void deleteAllById(Iterable<? extends Long> ids)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }



    @Override
    public boolean existsById(Long id)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }

    @Override
    public Iterable<City> findAllById(Iterable<Long> ids)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }



    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
