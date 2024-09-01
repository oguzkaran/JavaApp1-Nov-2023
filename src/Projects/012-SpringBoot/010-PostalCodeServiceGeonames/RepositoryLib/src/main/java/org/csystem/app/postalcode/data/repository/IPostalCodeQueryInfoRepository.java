package org.csystem.app.postalcode.data.repository;

import org.csystem.app.postalcode.data.entity.PostalCodeQueryInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostalCodeQueryInfoRepository extends CrudRepository<PostalCodeQueryInfo, Long> {
}
