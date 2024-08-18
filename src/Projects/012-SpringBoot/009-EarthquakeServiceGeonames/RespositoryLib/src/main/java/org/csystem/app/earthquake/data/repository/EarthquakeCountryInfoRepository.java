package org.csystem.app.earthquake.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeCountryInfo;
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
public class EarthquakeCountryInfoRepository implements IEarthquakeCountryInfoRepository {
    private static final String FIND_BY_REGION_INFO_ID = """
            select\s
            eci.distance, eci.country_code, eci.country_name\s
            from\s
            region_info ri inner join earthquake_country_info eci on eci.region_info_id = ri.region_info_id\s
            where ri.region_info_id = :region_info_id;
            """;
    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private static EarthquakeCountryInfo createEarthquakeCountryInfo(String distance, String countryCode, String countryName)
    {
        return EarthquakeCountryInfo.builder()
                .distance(distance)
                .countryCode(countryCode)
                .countryName(countryName)
                .build();
    }

    private static void fillEarthquakeCountryInfo(ResultSet rs, List<EarthquakeCountryInfo> earthquakeCountryInfoList) throws SQLException
    {
        do
            earthquakeCountryInfoList.add(createEarthquakeCountryInfo(rs.getString(1), rs.getString(2), rs.getString(3)));
        while (rs.next());
    }

    public EarthquakeCountryInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<EarthquakeCountryInfo> findByRegionInfoId(long regionInfoId)
    {
        var earthquakeCountryInfoList = new ArrayList<EarthquakeCountryInfo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("region_info_id", regionInfoId);

        m_namedParameterJdbcTemplate.query(FIND_BY_REGION_INFO_ID, paramMap,
                (ResultSet rs) -> fillEarthquakeCountryInfo(rs, earthquakeCountryInfoList));

        return earthquakeCountryInfoList;
    }

    ////////////////////////////////////////////////////////////////////////

    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void delete(EarthquakeCountryInfo entity)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends EarthquakeCountryInfo> entities)
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
    public Iterable<EarthquakeCountryInfo> findAll()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Iterable<EarthquakeCountryInfo> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Optional<EarthquakeCountryInfo> findById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <S extends EarthquakeCountryInfo> S save(S entity)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <S extends EarthquakeCountryInfo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
