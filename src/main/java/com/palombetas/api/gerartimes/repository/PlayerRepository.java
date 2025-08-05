package com.palombetas.api.gerartimes.repository;

import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    Page<PlayerEntity> findAllByIsActiveTrueOrderByNameAsc(Pageable pageable);
    List<PlayerEntity> findAllByIsActiveTrue();
}
