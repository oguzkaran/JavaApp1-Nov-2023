package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeAddress;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IEarthquakeAddressRepository extends ICrudRepository<EarthquakeAddress, Long> {
    Iterable<EarthquakeAddress> findByRegionInfoId(long regionInfoId);
}
