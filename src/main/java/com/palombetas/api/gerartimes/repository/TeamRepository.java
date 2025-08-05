package com.palombetas.api.gerartimes.repository;

import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
