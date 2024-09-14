package org.csystem.app.postalcode.data.repository;

import org.csystem.app.postalcode.data.entity.PostalCodeQueryInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostalCodeQueryInfoRepository extends CrudRepository<PostalCodeQueryInfo, Long> {
    String SAVE_SQL = """
            insert into postal_code_query_info (postal_code_id, query_date_time) \s
            values (:postalCodeValue, current_timestamp)
            """;
    @Query(nativeQuery = true, value = SAVE_SQL)
    @Modifying
    void savePostalQueryInfo(@Param("postalCodeValue") String postalCodeValue);
}
