package com.palombetas.api.gerartimes.domain.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PlayerRequestDTO(
        String name,

        @Min(1) @Max(5)
        int rating
) {
}
