package org.csystem.app.postalcode.data.repository;

import org.csystem.app.postalcode.data.entity.PostalCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostalCodeRepository extends CrudRepository<PostalCode, String> {
    @Query(nativeQuery = true,
            value = """
                    select  * from postal_codes\s
                    where\s
                    date_part('month', first_query_datetime) = :month and date_part('year', first_query_datetime) = :year
            """)
    List<PostalCode> findByMonthAndYear(int month, int year);
}
