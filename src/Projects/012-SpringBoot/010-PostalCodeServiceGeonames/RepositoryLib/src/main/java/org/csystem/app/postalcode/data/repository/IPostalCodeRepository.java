package org.csystem.app.postalcode.data.repository;

import org.csystem.app.postalcode.data.entity.PostalCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostalCodeRepository extends CrudRepository<PostalCode, Long> {
    Iterable<PostalCode> findByPostalCodeBetween(@Param("first") long first, @Param("last") long last);
}
