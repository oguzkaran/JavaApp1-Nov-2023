package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeCountryInfo;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IEarthquakeCountryInfoRepository extends ICrudRepository<EarthquakeCountryInfo, Long> {
    Iterable<EarthquakeCountryInfo> findByRegionInfoId(long regionInfoId);
}
