package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.data.exception.repository.ICrudRepository;

import java.util.Optional;

public interface IRegionInfoRepository extends ICrudRepository<RegionInfo, Long> {
    Optional<RegionInfo> findByRegion(double east, double west, double north, double south);
    void saveEarthquake(EarthquakeInfo earthquakeInfoSave);
    void saveEarthquakeQueryInfo(long regionInfoId);
}
