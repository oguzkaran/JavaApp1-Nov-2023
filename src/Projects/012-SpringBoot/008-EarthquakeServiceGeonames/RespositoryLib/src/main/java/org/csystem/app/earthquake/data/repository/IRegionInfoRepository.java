package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.*;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IRegionInfoRepository extends ICrudRepository<RegionInfo, Long> {
    void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave);
    Iterable<EarthquakeInfoDetails> findByRegionInfo(double east, double west, double north, double south);
    void saveEarthquakeQueryInfo(long regionInfoId);
}
