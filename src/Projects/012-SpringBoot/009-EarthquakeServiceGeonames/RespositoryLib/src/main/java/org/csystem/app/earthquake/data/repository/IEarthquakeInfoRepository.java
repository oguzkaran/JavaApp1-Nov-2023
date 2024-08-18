package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IEarthquakeInfoRepository extends ICrudRepository<EarthquakeInfo, Long> {
    Iterable<EarthquakeInfo> findByRegionInfoId(long regionInfoId);
}
