package com.palombetas.api.gerartimes.validation.teams.generate;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;

public interface IValidatorGenerateTeams {
    void validate(ConfirmedPlayersListDTO confirmedPlayersListDTO);
}
