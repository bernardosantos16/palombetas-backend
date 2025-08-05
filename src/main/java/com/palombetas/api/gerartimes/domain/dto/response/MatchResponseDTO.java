package com.palombetas.api.gerartimes.domain.dto.response;

import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;

import java.time.LocalDateTime;
import java.util.List;

public record MatchResponseDTO(
        Long id,
        List<TeamResponseDTO> teams,
        LocalDateTime matchDate,
        PlayerResponseDTO playerMvp,
        TeamResponseDTO winner
) {
}
