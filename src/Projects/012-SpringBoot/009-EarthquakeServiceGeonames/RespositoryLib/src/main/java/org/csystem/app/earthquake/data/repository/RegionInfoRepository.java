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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Repository
public class RegionInfoRepository implements IRegionInfoRepository {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");

    private static final String FIND_DETAILS_BY_REGION_INFO = """            
            select\s
            ei.latitude, ei.longitude, ei.depth, ei.datetime, ei.magnitude, ei.earthquake_id,\s
            eai.locality, eai.street, eai.postal_code,\s
            eci.distance, eci.country_code, eci.country_name,\s
            ri.region_info_id\s
            from\s
            region_info ri inner join earthquake_info ei on ei.region_info_id = ri.region_info_id\s
            inner join earthquake_address_info eai on eai.region_info_id = ri.region_info_id\s
            inner join earthquake_country_info eci on eci.region_info_id = ri.region_info_id
            where abs(ri.east - :east) < 0.00001 and abs(ri.west - :west) < 0.00001 and abs(ri.north - :north) < 0.00001\s
            and abs(ri.south - :south) < 0.00001;
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

    private EarthquakeInfo createEarthquakeInfo(double latitude, double longitude, double depth,
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

    private EarthquakeAddress createEarthquakeAddress(String locality, String street, String postalCode)
    {
        return EarthquakeAddress.builder()
                .locality(locality)
                .street(street)
                .postalCode(postalCode)
                .build();
    }

    private EarthquakeCountryInfo createEarthquakeCountryInfo(String distance, String countryCode, String countryName)
    {
        return EarthquakeCountryInfo.builder()
                .distance(distance)
                .countryCode(countryCode)
                .countryName(countryName)
                .build();
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

    private void fillEarthquakeInfoDetails(ResultSet rs, EarthquakesInfo earthquakesInfo) throws SQLException
    {
        earthquakesInfo.regionInfoId = rs.getLong(13);

        do {
            var earthquakeInfo = createEarthquakeInfo(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3),
                    rs.getString(4), rs.getDouble(5), rs.getString(6));
            var earthquakeAddress = createEarthquakeAddress(rs.getString(7), rs.getString(8), rs.getString(9));
            var earthquakeCountryInfo = createEarthquakeCountryInfo(rs.getString(10), rs.getString(11), rs.getString(12));

            earthquakesInfo.earthquakes.add(createEarthquakeDetails(earthquakeInfo, earthquakeAddress, earthquakeCountryInfo));
        } while (rs.next());
    }

    public RegionInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public EarthquakesInfo findByRegionInfo(double east, double west, double north, double south)
    {
        var earthquakesInfo = EarthquakesInfo.builder().earthquakes(new ArrayList<>()).build();
        var paramMap = new HashMap<String, Object>();

        paramMap.put("east", east);
        paramMap.put("west", west);
        paramMap.put("north", north);
        paramMap.put("south", south);

        m_namedParameterJdbcTemplate.query(FIND_DETAILS_BY_REGION_INFO, paramMap,
                (ResultSet rs) -> fillEarthquakeInfoDetails(rs, earthquakesInfo));

        return earthquakesInfo;
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
    public void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave, long regionInfoId)
    {
        try {
            saveEarthquakeInfo(earthquakeInfoSave.earthquakeInfo);
            saveEarthquakeAddress(earthquakeInfoSave.earthquakeAddress);
            saveEarthquakeCountryInfo(earthquakeInfoSave.earthquakeCountryInfo);
            saveEarthquakeQueryInfo(regionInfoId);
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
