package com.geertjankuip.mijnwoonplaats.repositories;

import com.geertjankuip.mijnwoonplaats.domainobjects.FetchAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FetchAttemptRepository extends JpaRepository<FetchAttempt, Long> {
}
