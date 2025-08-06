package com.palombetas.api.gerartimes.validation.teams.generate;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;

public class ValidateTotalPlayers implements IValidatorGenerateTeams {
    @Override
    public void validate(ConfirmedPlayersListDTO confirmedPlayersListDTO) {
        int totalPlayers = confirmedPlayersListDTO.playersIds().size();
        int minimumPlayers = confirmedPlayersListDTO.playersPerTeam() * 2;

        if (totalPlayers < minimumPlayers) {
            throw new RuntimeException(
                    "Número de jogadores insuficiente para formar ao menos dois times. É necessário pelo menos " + minimumPlayers + " jogadores.");
        }
    }
}
