package com.palombetas.api.gerartimes.domain.dto.response;

import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;

public record PlayerResponseDTO(
        Long id,
        String name,
        Integer rating,
        Integer timesChampion,
        Integer timesMvp
) {
    public PlayerResponseDTO(
        PlayerEntity playerEntity
    ){
        this(
            playerEntity.getId(),
            playerEntity.getName(),
            playerEntity.getRating(),
            playerEntity.getTimesChampion(),
            playerEntity.getTimesMvp()
        );
    }
}
