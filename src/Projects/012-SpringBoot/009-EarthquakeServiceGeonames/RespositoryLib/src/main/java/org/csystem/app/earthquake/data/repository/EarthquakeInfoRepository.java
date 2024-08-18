package org.csystem.app.earthquake.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class EarthquakeInfoRepository implements IEarthquakeInfoRepository {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final String FIND_BY_REGION_INFO_ID = """
            select\s
            ei.latitude, ei.longitude, ei.depth, ei.datetime, ei.magnitude, ei.earthquake_id\s
            from\s
            region_info ri inner join earthquake_info ei on ei.region_info_id = ri.region_info_id\s
            where ri.region_info_id = :region_info_id;
            """;
    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private static EarthquakeInfo createEarthquakeInfo(double latitude, double longitude, double depth,
                                                String dateTime, double magnitude, String earthquakeId)
    {
        return EarthquakeInfo.builder()
                .latitude(latitude)
                .longitude(longitude)
                .depth(depth)
                .dateTime(dateTime)
                .magnitude(magnitude)
                .earthquakeId(earthquakeId)
                .build();
    }

    private static void fillEarthquakeInfo(ResultSet rs, List<EarthquakeInfo> earthquakeInfo) throws SQLException
    {
        do
            earthquakeInfo.add(createEarthquakeInfo(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3),
                    rs.getTimestamp(4).toLocalDateTime().format(DATE_TIME_FORMATTER), rs.getDouble(5), rs.getString(6)));
        while (rs.next());
    }

    public EarthquakeInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<EarthquakeInfo> findByRegionInfoId(long regionInfoId)
    {
        var earthquakeInfoList = new ArrayList<EarthquakeInfo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("region_info_id", regionInfoId);

        m_namedParameterJdbcTemplate.query(FIND_BY_REGION_INFO_ID, paramMap,
                (ResultSet rs) -> fillEarthquakeInfo(rs, earthquakeInfoList));

        return earthquakeInfoList;
    }

    ////////////////////////////////////////////////////////////////////////


    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void delete(EarthquakeInfo entity)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends EarthquakeInfo> entities)
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
    public Iterable<EarthquakeInfo> findAll()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Iterable<EarthquakeInfo> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Optional<EarthquakeInfo> findById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <S extends EarthquakeInfo> S save(S entity)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public <S extends EarthquakeInfo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
