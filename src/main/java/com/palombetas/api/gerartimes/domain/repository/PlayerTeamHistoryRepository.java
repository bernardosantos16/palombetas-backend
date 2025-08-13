package com.palombetas.api.gerartimes.domain.repository;

import com.palombetas.api.gerartimes.domain.entity.PlayerTeamHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTeamHistoryRepository extends JpaRepository<PlayerTeamHistoryEntity, Long> {
}
