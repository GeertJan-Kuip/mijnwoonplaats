package com.geertjankuip.mijnwoonplaats.repositories;

import com.geertjankuip.mijnwoonplaats.domainobjects.Buurt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BuurtRepository  extends JpaRepository<Buurt, Long> {

    @Query(value = "SELECT b.* FROM buurten b " +
            "INNER JOIN postcodes4 p ON b.meestvoorkomendepostcode_123 = p.pdok_postcode4 " +
            "INNER JOIN woonplaatsen w on w.woonplaatscode = p.pdok_woonplaatscode " +
            "WHERE w.woonplaatscode = :code",
            nativeQuery = true)
    List<Buurt> findBuurtenByWoonplaatscode(@Param("code") String code);
}
