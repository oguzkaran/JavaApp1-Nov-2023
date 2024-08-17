package org.csystem.app.earthquake.data.repository;

import org.csystem.app.earthquake.data.entity.EarthquakeInfoSave;
import org.csystem.app.earthquake.data.entity.EarthquakesInfo;
import org.csystem.app.earthquake.data.entity.RegionInfo;
import org.csystem.data.exception.repository.ICrudRepository;

import java.sql.SQLException;

public interface IRegionInfoRepository extends ICrudRepository<RegionInfo, Long> {
    EarthquakesInfo findByRegionInfo(double east, double west, double north, double south);
    void saveEarthquake(EarthquakeInfoSave earthquakeInfoSave, long regionInfoId);
    void saveEarthquakeQueryInfo(long regionInfoId);
}
