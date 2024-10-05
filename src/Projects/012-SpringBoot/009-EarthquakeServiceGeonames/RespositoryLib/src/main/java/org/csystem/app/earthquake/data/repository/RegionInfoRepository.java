package org.csystem.app.earthquake.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.data.exception.repository.RepositoryException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

import static org.csystem.app.earthquake.data.constant.FieldNameConstant.*;
@Repository
@Slf4j
public class RegionInfoRepository implements IRegionInfoRepository {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");

    private static final String FIND_BY_REGION = """
            select\s
            ri.region_info_id\s
            from\s
            region_info ri\s
            where abs(ri.east - :east) < 0.00001 and abs(ri.west - :west) < 0.00001 and abs(ri.north - :north) < 0.00001\s
            and abs(ri.south - :south) < 0.00001;
            """;

    private static final String SAVE_REGION_INFO_SQL = """
            insert into region_info (east, west, north, south) values (:east, :west, :north, :south)
            """;
    private static final String SAVE_EARTHQUAKE_INFO_SQL = """
            insert into earthquake_info (region_info_id, datetime, depth, latitude, longitude, earthquake_id, magnitude,\s
                             distance, country_code, country_name, locality, street, postal_code)
            values (:region_info_id, :datetime, :depth, :latitude, :longitude, :earthquake_id, :magnitude,\s
            :distance, :country_code, :country_name, :locality, :street, :postal_code)
            """;

    private static final String SAVE_EARTHQUAKE_QUERY_INFO_SQL = """
            insert into earthquake_query_info (region_info_id) values (:region_info_id)
            """;

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private void saveEarthquakeInfo(EarthquakeInfo earthquakeInfo)
    {
        var paramMap = new HashMap<String, Object>();

        paramMap.put("region_info_id", earthquakeInfo.regionInfoId);
        paramMap.put("datetime", LocalDateTime.parse(earthquakeInfo.dateTime, FORMATTER));
        paramMap.put("depth", earthquakeInfo.depth);
        paramMap.put("latitude", earthquakeInfo.latitude);
        paramMap.put("longitude", earthquakeInfo.longitude);
        paramMap.put("earthquake_id", earthquakeInfo.earthquakeId);
        paramMap.put("magnitude", earthquakeInfo.magnitude);
        paramMap.put("locality", earthquakeInfo.locality);
        paramMap.put("street", earthquakeInfo.street);
        paramMap.put("postal_code", earthquakeInfo.postalCode);
        paramMap.put("distance", earthquakeInfo.distance);
        paramMap.put("country_code", earthquakeInfo.countryCode);
        paramMap.put("country_name", earthquakeInfo.countryName);

        m_namedParameterJdbcTemplate.update(SAVE_EARTHQUAKE_INFO_SQL, paramMap);
    }

    private Optional<RegionInfo> fillRegionInfo(ResultSet rs, RegionInfo regionInfo) throws SQLException
    {
        boolean flag = rs.next();

        if (flag)
            regionInfo.id = rs.getLong(1);

        return flag ? Optional.of(regionInfo) : Optional.empty();
    }

    public RegionInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<RegionInfo> findAll()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Optional<RegionInfo> findByRegion(double east, double west, double north, double south)
    {
        var regionInfo = RegionInfo.builder().east(east).west(west).north(north).south(south).build();
        var paramMap = new HashMap<String, Object>();

        paramMap.put(EAST, east);
        paramMap.put(WEST, west);
        paramMap.put(NORTH, north);
        paramMap.put(SOUTH, south);

        return m_namedParameterJdbcTemplate.query(FIND_BY_REGION, paramMap, (ResultSet rs) -> fillRegionInfo(rs, regionInfo));
    }

    @Override
    public <S extends RegionInfo> S save(S regionInfo)
    {
        try {
            var paramSource = new BeanPropertySqlParameterSource(regionInfo);
            var keyHolder = new GeneratedKeyHolder();

            if (m_namedParameterJdbcTemplate.update(SAVE_REGION_INFO_SQL, paramSource, keyHolder) != 1)
                throw new SQLException();

            regionInfo.id = (long) keyHolder.getKeyList().get(0).get("region_info_id");
            log.info("EarthquakeAppDataHelper.save -> Generated id:{}", regionInfo.id);

            return regionInfo;
        }
        catch (SQLException ex) {
            log.error("RegionInfoRepository.save: Message: {}", ex.getMessage());
            throw new RepositoryException("RegionInfoRepository.save", ex);
        }
    }

    @Override
    @Transactional
    public void saveEarthquake(EarthquakeInfo earthquakeInfo)
    {
        try {
            log.info("RegionInfoRepository.saveEarthQuake -> {}", earthquakeInfo);
            saveEarthquakeInfo(earthquakeInfo);
        }
        catch (Throwable ex) {
            log.error("RegionInfoRepository.saveEarthQuake -> Message:{}", ex.getMessage());
            throw new RepositoryException("RegionInfoRepository.saveEarthQuake", ex);
        }
    }

    @Override
    public void saveEarthquakeQueryInfo(long regionInfoId)
    {
        try {
            var paramMap = new HashMap<String, Object>();

            paramMap.put("region_info_id", regionInfoId);

            m_namedParameterJdbcTemplate.update(SAVE_EARTHQUAKE_QUERY_INFO_SQL, paramMap);
        }
        catch (Throwable ex) {
            log.error("RegionInfoRepository.saveEarthquakeQueryInfo -> Message:{}", ex.getMessage());
            throw new RepositoryException("RegionInfoRepository.saveEarthquakeQueryInfo", ex);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////


    @Override
    public long count()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void delete(RegionInfo entity)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteAll(Iterable<? extends RegionInfo> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public void deleteById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public boolean existsById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Iterable<RegionInfo> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public Optional<RegionInfo> findById(Long aLong)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public <S extends RegionInfo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
