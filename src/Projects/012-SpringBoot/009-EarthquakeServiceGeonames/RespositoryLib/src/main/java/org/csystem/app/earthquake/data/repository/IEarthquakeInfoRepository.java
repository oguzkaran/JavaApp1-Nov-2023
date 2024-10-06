package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.data.exception.repository.ICrudRepository;

import java.util.List;

public interface IEarthquakeInfoRepository extends ICrudRepository<EarthquakeInfo, Long> {
    List<EarthquakeInfo> findByRegion(double east, double west, double north, double south);
    List<EarthquakeInfo> findByRegionInfoId(long regionInfoId);
}
