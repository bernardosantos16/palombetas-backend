package com.palombetas.api.gerartimes.domain.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ConfirmedPlayersListDTO(
        @NotNull
        Long matchId,

        @NotNull
        List<Long> playersIds,

        Integer playersPerTeam
) {
    public ConfirmedPlayersListDTO(Long matchId, List<Long> playersIds, Integer playersPerTeam) {
        this.matchId = matchId;
        this.playersIds = playersIds;
        this.playersPerTeam = playersPerTeam != null ? playersPerTeam : 5; // Default to 5 if not provided
    }
}
