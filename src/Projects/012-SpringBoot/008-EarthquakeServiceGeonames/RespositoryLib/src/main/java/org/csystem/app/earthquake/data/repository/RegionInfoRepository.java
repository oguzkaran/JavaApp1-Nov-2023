package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeInfoDetails;
import org.csystem.app.earthquake.data.entity.EarthquakeInfoSave;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class RegionInfoRepository implements IRegionInfoRepository {
    private static final String FIND_DETAILS_BY_REGION_INFO = """            
            select\s
            ei.latitude, ei.longitude, ei.depth, ei.datetime, ei.magnitude, ei.earthquake_id,\s
            eai.locality, eai.street, eai.postal_code,\s
            eci.distance, eci.country_code, eci.country_name\s
            from\s
            region_info ri inner join earthquake_info ei on ei.region_info_id = ri.region_info_id\s
            inner join earthquake_address_info eai on eai.region_info_id = ri.region_info_id\s
            inner join earthquake_country_info eci on eci.region_info_id = ri.region_info_id
            where abs(east - :east) < 0.00001 and abs(west - :west) < 0.00001 and abs(north - :north) < 0.00001\s
            and abs(south - :south) < 0.00001;
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

    private final NamedParameterJdbcTemplate m_namedParameterJdbcTemplate;

    public RegionInfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        m_namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Iterable<EarthquakeInfoDetails> findByRegionInfo(double east, double west, double north, double south)
    {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    @Transactional
    public boolean saveRegionInfo(EarthquakeInfoSave earthquakeInfoSave)
    {
        throw new UnsupportedOperationException("TODO");
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
    public <S extends RegionInfo> S save(S entity)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }

    @Override
    public <S extends RegionInfo> Iterable<S> saveAll(Iterable<S> entities)
    {
        throw new UnsupportedOperationException("Not yet implemented!...");
    }
}
