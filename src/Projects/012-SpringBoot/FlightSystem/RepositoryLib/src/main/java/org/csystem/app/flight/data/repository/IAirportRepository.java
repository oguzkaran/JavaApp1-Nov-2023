package org.csystem.app.flight.data.repository;

import org.csystem.app.flight.data.entity.Airport;
import org.csystem.data.exception.repository.ICrudRepository;

public interface IAirportRepository extends ICrudRepository<Airport, Long> {
    Iterable<Airport> findByCityId(long cityId);
    Iterable<Airport> findByNameContains(String text);
}
