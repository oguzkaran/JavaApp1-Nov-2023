package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.data.exception.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Repository
public class RegionInfoRepository implements IRegionInfoRepository {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");

    private static final String FIND_BY_REGION = """
            select\s
            ri.region_info_id\s
            from\s
            region_info ri\s
            where abs(ri.east - :east) < 0.00001 and abs(ri.west - :west) < 0.00001 and abs(ri.north - :north) < 0.00001\s
            and abs(ri.south - :south) < 0.00001;\s
            """;

    private static final String SAVE_REGION_INFO_SQL = """
            insert into region_info (east, west, north, south) values (:east, :west, :north, :south)
            """;
    private static final String SAVE_EARTHQUAKE_INFO_SQL = """
            insert into earthquake_info (region_info_id, datetime, depth, latitude, longitude, earthquake_id, magnitude)\s
            values (:region_info_id, :datetime, :depth, :latitude, :longitude, :earthquake_id, :magnitude)
            """;

    private static final String SAVE_EARTHQUAKE_COUNTRY_INFO_SQL = """
            insert into earthquake_country_info (region_info_id, distance, country_code, country_name)
            values (:region_info_id, :distance, :country_code, :country_name)\s
            """;

    private static final String SAVE_EARTHQUAKE_ADDRESS_INFO_SQL = """
            insert into earthquake_address_info (region_info_id, locality, street, postal_code)\s
            values (:region_info_id, :locality, :street, :postal_code)
            """;

    private static final String SAVE_EARTHQUAKE_QUERY_INFO_SQL = """
            insert into earthquake_query_info (region_info_id) values (:region_info_id)
            """;
    private static final Logger log = LoggerFactory.getLogger(RegionInfoRepository.class);

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    private void saveEarthquakeInfo(EarthquakeInfo earthquakeInfo)
    {
        var paramMap = new HashMap<String, Object>();

        paramMap.put("datetime", LocalDateTime.parse(earthquakeInfo.dateTime, FORMATTER));
        paramMap.put("depth", earthquakeInfo.depth);
        paramMap.put("latitude", earthquakeInfo.latitude);
        paramMap.put("longitude", earthquakeInfo.longitude);
        paramMap.put("earthquake_id", earthquakeInfo.earthquakeId);
        paramMap.put("magnitude", earthquakeInfo.magnitude);
        paramMap.put("region_info_id", earthquakeInfo.regionInfoId);

        m_namedParameterJdbcTemplate.update(SAVE_EARTHQUAKE_INFO_SQL, paramMap);
    }

    private void saveEarthquakeAddress(EarthquakeAddress earthquakeAddress)
    {
        var paramMap = new HashMap<String, Object>();

        paramMap.put("locality", earthquakeAddress.locality);
        paramMap.put("street", earthquakeAddress.street);
        paramMap.put("postal_code", earthquakeAddress.postalCode);
        paramMap.put("region_info_id", earthquakeAddress.regionInfoId);

        m_namedParameterJdbcTemplate.update(SAVE_EARTHQUAKE_ADDRESS_INFO_SQL, paramMap);
    }

    private void saveEarthquakeCountryInfo(EarthquakeCountryInfo earthquakeCountryInfo)
    {
        var paramMap = new HashMap<String, Object>();

        paramMap.put("distance", earthquakeCountryInfo.distance);
        paramMap.put("country_code", earthquakeCountryInfo.countryCode);
        paramMap.put("country_name", earthquakeCountryInfo.countryName);
        paramMap.put("region_info_id", earthquakeCountryInfo.regionInfoId);

        m_namedParameterJdbcTemplate.update(SAVE_EARTHQUAKE_COUNTRY_INFO_SQL, paramMap);
    }



    private EarthquakeInfoDetails createEarthquakeDetails(EarthquakeInfo earthquakeInfo,
                                                          EarthquakeAddress earthquakeAddress,
                                                          EarthquakeCountryInfo earthquakeCountryInfo)
    {
        return EarthquakeInfoDetails.builder()
                .earthquakeInfo(earthquakeInfo)
                .earthquakeAddress(earthquakeAddress)
                .earthquakeCountryInfo(earthquakeCountryInfo)
                .build();
    }

    private void fillRegionInfo(ResultSet rs, RegionInfo regionInfo) throws SQLException
    {
        regionInfo.id = rs.getLong(1);
    }

    public RegionInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public RegionInfo findByRegion(double east, double west, double north, double south)
    {
        var regionInfo = RegionInfo.builder().east(east).west(west).north(north).south(south).build();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("east", east);
        paramMap.put("west", west);
        paramMap.put("north", north);
        paramMap.put("south", south);

        m_namedParameterJdbcTemplate.query(FIND_BY_REGION, paramMap, (ResultSet rs) -> fillRegionInfo(rs, regionInfo));

        return regionInfo;
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

            return regionInfo;
        }
        catch (SQLException ex) {
            log.info("EarthquakeAppDataHelper.saveRegionInfo: Message: {}", ex.getMessage());
            throw new RepositoryException("EarthquakeAppDataHelper.saveRegionInfo", ex);
        }
    }

    @Override
    @Transactional
    public void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave)
    {
        try {
            if (earthquakeInfoSave.earthquakeInfo != null)
                saveEarthquakeInfo(earthquakeInfoSave.earthquakeInfo);

            if (earthquakeInfoSave.earthquakeAddress != null)
                saveEarthquakeAddress(earthquakeInfoSave.earthquakeAddress);

            if (earthquakeInfoSave.earthquakeCountryInfo != null)
                saveEarthquakeCountryInfo(earthquakeInfoSave.earthquakeCountryInfo);
        }
        catch (Throwable ex) {
            log.error("RegionInfoRepository.saveEarthQuake -> Message:{}", ex.getMessage());
            throw new RepositoryException("RegionInfoRepository.saveEarthQuake", ex);
        }
    }

    @Override
    public void saveEarthquakeQueryInfo(long regionInfoId)
    {
        var paramMap = new HashMap<String, Object>();

        paramMap.put("region_info_id", regionInfoId);

        m_namedParameterJdbcTemplate.update(SAVE_EARTHQUAKE_QUERY_INFO_SQL, paramMap);
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
    public Iterable<RegionInfo> findAll()
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
