package org.csystem.app.flight.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.flight.data.entity.Airport;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Optional;

@Repository
@Lazy
@Slf4j
public class AirportRepository implements IAirportRepository {
    private static final String SAVE_SQL = "insert into airports (name, city_id, open_date) values (:name, :cityId, :openDate)";
    //...

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private static void fillAirPort(ResultSet rs, ArrayList<Airport> airports) throws SQLException
    {
        do {
            var id = rs.getLong(1);
            var name = rs.getString(2);
            var cityId = rs.getLong(3);
            var openDate = rs.getDate(4).toLocalDate();
            var registerDateTime = rs.getTimestamp(5).toLocalDateTime();

            airports.add(new Airport(id, name, cityId, openDate, registerDateTime));
        } while (rs.next());
    }

    public AirportRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<Airport> findByCityId(long cityId)
    {
        throw new UnsupportedOperationException("TODO:Berkay Yılmaz, Merve Artar");
    }

    @Override
    public Iterable<Airport> findByNameContains(String text)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Optional<Airport> findById(Long aLong)
    {
        throw new UnsupportedOperationException("TODO:Berkay Yılmaz");
    }

    @Override
    public <S extends Airport> S save(S airport)
    {
        var paramSource = new BeanPropertySqlParameterSource(airport);

        paramSource.registerSqlType("openTime", Types.DATE);
        paramSource.registerSqlType("registerDateTime", Types.TIMESTAMP);

        //TODO:

        return airport;
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
    public <S extends Airport> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not implemented yet!...");
    }
}
