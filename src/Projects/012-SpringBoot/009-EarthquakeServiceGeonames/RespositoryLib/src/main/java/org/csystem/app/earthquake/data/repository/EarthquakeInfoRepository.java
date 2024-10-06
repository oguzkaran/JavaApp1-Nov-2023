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

import static org.csystem.app.earthquake.data.constant.FieldNameConstant.*;

@Repository
@Slf4j
public class EarthquakeInfoRepository implements IEarthquakeInfoRepository {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final String FIND_EARTHQUAKES_BY_REGION_INFO_ID = """
            select\s
            ei.datetime, ei.depth, ei.latitude, ei.longitude, ei.earthquake_id, ei.magnitude, ei.distance,\s
            ei.country_code, ei.country_name, ei.locality, ei.street, ei.postal_code
            from\s
            region_info ri inner join earthquake_info ei on ei.region_info_id = ri.region_info_id\s
            where ri.region_info_id = :region_info_id;
            """;

    private static final String FIND_EARTHQUAKES_BY_REGION_INFO = """
            select\s
            ei.datetime, ei.depth, ei.latitude, ei.longitude, ei.earthquake_id, ei.magnitude, ei.distance,\s
            ei.country_code, ei.country_name, ei.locality, ei.street, ei.postal_code,\s
            ri.region_info_id\s
            from\s
            region_info ri inner join earthquake_info ei on ei.region_info_id = ri.region_info_id\s
            where abs(ri.east - :east) < 0.00001 and abs(ri.west - :west) < 0.00001 and abs(ri.north - :north) < 0.00001\s
            and abs(ri.south - :south) < 0.00001;
            """;

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private static EarthquakeInfo createEarthquakeInfoWithRegionId(ResultSet rs) throws SQLException
    {
        return EarthquakeInfo.builder()
                .dateTime(rs.getTimestamp(1).toLocalDateTime().format(DATE_TIME_FORMATTER))
                .depth(rs.getDouble(2))
                .latitude(rs.getDouble(3))
                .longitude(rs.getDouble(4))
                .earthquakeId(rs.getString(5))
                .magnitude(rs.getDouble(6))
                .distance(rs.getString(7))
                .countryCode(rs.getString(8))
                .countryName(rs.getString(9))
                .locality(rs.getString(10))
                .street(rs.getString(11))
                .postalCode(rs.getString(12))
                .regionInfoId(rs.getLong(13))
                .build();
    }

    private static EarthquakeInfo createEarthquakeInfoWithoutRegionId(ResultSet rs) throws SQLException
    {
        return EarthquakeInfo.builder()
                .dateTime(rs.getTimestamp(1).toLocalDateTime().format(DATE_TIME_FORMATTER))
                .depth(rs.getDouble(2))
                .latitude(rs.getDouble(3))
                .longitude(rs.getDouble(4))
                .earthquakeId(rs.getString(5))
                .magnitude(rs.getDouble(6))
                .distance(rs.getString(7))
                .countryCode(rs.getString(8))
                .countryName(rs.getString(9))
                .locality(rs.getString(10))
                .street(rs.getString(11))
                .postalCode(rs.getString(12))
                .build();
    }

    private static void fillEarthquakeInfoWithRegionId(ResultSet rs, List<EarthquakeInfo> earthquakeInfo) throws SQLException
    {
        do
            earthquakeInfo.add(createEarthquakeInfoWithRegionId(rs));
        while (rs.next());
    }

    private static void fillEarthquakeInfoWithoutRegionId(ResultSet rs, List<EarthquakeInfo> earthquakeInfo) throws SQLException
    {
        do
            earthquakeInfo.add(createEarthquakeInfoWithoutRegionId(rs));
        while (rs.next());
    }

    public EarthquakeInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<EarthquakeInfo> findByRegionInfoId(long regionInfoId)
    {
        var earthquakeInfoList = new ArrayList<EarthquakeInfo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("region_info_id", regionInfoId);

        m_namedParameterJdbcTemplate.query(FIND_EARTHQUAKES_BY_REGION_INFO_ID, paramMap,
                (ResultSet rs) -> fillEarthquakeInfoWithoutRegionId(rs, earthquakeInfoList));

        return earthquakeInfoList;
    }

    @Override
    public List<EarthquakeInfo> findByRegion(double east, double west, double north, double south)
    {
        var earthquakeInfoList = new ArrayList<EarthquakeInfo>();
        var paramMap = new HashMap<String, Object>();

        paramMap.put(EAST, east);
        paramMap.put(WEST, west);
        paramMap.put(NORTH, north);
        paramMap.put(SOUTH, south);

        m_namedParameterJdbcTemplate.query(FIND_EARTHQUAKES_BY_REGION_INFO, paramMap,
                (ResultSet rs) -> fillEarthquakeInfoWithRegionId(rs, earthquakeInfoList));

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
