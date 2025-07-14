package com.palombetas.api.gerartimes.dto;

public record PlayerRequestDTO(
        String name,
        String position,
        String team,
        Integer age
) {
}
