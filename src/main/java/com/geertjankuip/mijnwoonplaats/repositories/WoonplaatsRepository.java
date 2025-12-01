package com.geertjankuip.mijnwoonplaats.repositories;

import com.geertjankuip.mijnwoonplaats.domainobjects.Woonplaats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WoonplaatsRepository extends JpaRepository<Woonplaats, Long> {

    @Query("SELECT t.woonplaatscode FROM Woonplaats t WHERE t.id= :id")
    Optional<String> findWoonplaatscodeById(Long id);

    List<Woonplaats> findByWeergavenaamStartingWithIgnoreCase(String query);

    Optional<Woonplaats> findFirstByWoonplaatscode(String query);

    @Query("SELECT t.woonplaatscode FROM Woonplaats t")
    Set<String> findAllWoonplaatscodes();
}
