package com.palombetas.api.gerartimes.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank String login,
        @NotBlank String password
) {
}
