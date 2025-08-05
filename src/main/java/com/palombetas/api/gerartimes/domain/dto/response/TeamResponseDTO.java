package com.palombetas.api.gerartimes.domain.dto.response;

import java.util.List;

public record TeamResponseDTO(
        Long id,
        String name,
        Double rating,
        List<PlayerResponseDTO> players
) {
}
