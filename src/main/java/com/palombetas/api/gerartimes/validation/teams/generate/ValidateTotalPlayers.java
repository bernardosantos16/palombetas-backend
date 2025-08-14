package com.palombetas.api.gerartimes.validation.teams.generate;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component("validateTotalPlayers")
public class ValidateTotalPlayers implements IValidatorGenerateTeams {
    @Override
    public void validate(ConfirmedPlayersListDTO confirmedPlayersListDTO) {
        int totalPlayers = confirmedPlayersListDTO.playersIds().size();
        int minimumPlayers = confirmedPlayersListDTO.playersPerTeam() * 2;

        if (totalPlayers < minimumPlayers) {
            throw new GenericCustomException(
                    "about:blank",
                    "insufficient players",
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    "There are no minimum players to form at least two teams"
            );
        }
    }
}
