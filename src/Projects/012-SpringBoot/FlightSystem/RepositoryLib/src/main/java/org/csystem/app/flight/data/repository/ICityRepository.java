package org.csystem.app.flight.data.repository;

import org.csystem.app.flight.data.entity.City;
import org.csystem.data.exception.repository.ICrudRepository;

public interface ICityRepository extends ICrudRepository<City, Long> {
    Iterable<City> findByName(String name);
}
