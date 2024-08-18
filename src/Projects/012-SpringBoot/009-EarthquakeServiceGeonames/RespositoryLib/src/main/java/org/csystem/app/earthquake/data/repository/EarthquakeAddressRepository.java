package org.csystem.app.earthquake.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeAddress;
import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class EarthquakeAddressRepository implements IEarthquakeAddressRepository {
    private static final String FIND_BY_REGION_INFO_ID = """
            select\s
            eai.locality, eai.street, eai.postal_code\s
            from\s
            region_info ri inner join earthquake_address_info eai on eai.region_info_id = ri.region_info_id\s
            where ri.region_info_id = :region_info_id;\s
            """;
    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private static EarthquakeAddress createEarthquakeAddress(String locality, String street, String postalCode)
    {
        return EarthquakeAddress.builder()
                .locality(locality)
                .street(street)
                .postalCode(postalCode)
                .build();
    }

    private static void fillEarthquakeAddress(ResultSet rs, List<EarthquakeAddress> earthquakeAddresses) throws SQLException
    {
        do
            earthquakeAddresses.add(createEarthquakeAddress(rs.getString(1), rs.getString(2), rs.getString(3)));
        while (rs.next());
    }

    public EarthquakeAddressRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<EarthquakeAddress> findByRegionInfoId(long regionInfoId)
    {
        var earthquakeAddresses = new ArrayList<EarthquakeAddress>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("region_info_id", regionInfoId);

        m_namedParameterJdbcTemplate.query(FIND_BY_REGION_INFO_ID, paramMap,
                (ResultSet rs) -> fillEarthquakeAddress(rs, earthquakeAddresses));

        return earthquakeAddresses;
    }

    ////////////////////////////////////////////////////////////////////////

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void delete(EarthquakeAddress entity)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends EarthquakeAddress> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean existsById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Iterable<EarthquakeAddress> findAll()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Iterable<EarthquakeAddress> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Optional<EarthquakeAddress> findById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <S extends EarthquakeAddress> S save(S entity)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <S extends EarthquakeAddress> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
