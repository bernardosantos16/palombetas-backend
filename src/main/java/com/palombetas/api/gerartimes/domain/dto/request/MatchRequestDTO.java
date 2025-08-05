package com.palombetas.api.gerartimes.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MatchRequestDTO(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime matchDate
) {
}
