package org.csystem.app.postalcode.data.repository;

import org.csystem.app.postalcode.data.entity.PostalCodeInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostalCodeInfoRepository extends CrudRepository<PostalCodeInfo, Long> {
    @Query(value = "select pi from PostalCodeInfo pi where pi.adminName1 = :cityName")
    List<PostalCodeInfo> findByCity(@Param("cityName") String cityName);

    Iterable<PostalCodeInfo> findByAdminCode1Contains(@Param("text") String text);
}
