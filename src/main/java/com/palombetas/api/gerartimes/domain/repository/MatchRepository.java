package com.palombetas.api.gerartimes.domain.repository;

import com.palombetas.api.gerartimes.domain.entity.MatchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

    Page<MatchEntity> findAllByOrderByMatchDateDesc(Pageable pageable);
}
