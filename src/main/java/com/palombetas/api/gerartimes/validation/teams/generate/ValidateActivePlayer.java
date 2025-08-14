package com.palombetas.api.gerartimes.validation.teams.generate;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;
import com.palombetas.api.gerartimes.domain.repository.PlayerRepository;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePlayer implements IValidatorGenerateTeams {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void validate(ConfirmedPlayersListDTO confirmedPlayersListDTO) {
        var playersIds = confirmedPlayersListDTO.playersIds();
        if (playersIds == null) {
            return;
        }
        playersIds.forEach(
                playerId -> {
                    var isActive = playerRepository.existsByIdAndIsActiveTrue(playerId);
                    if (!isActive) {
                        throw new GenericCustomException(
                                "about:blank",
                                "inactive player",
                                HttpStatus.BAD_REQUEST.value(),
                                "player is not active"
                        );
                    }
                }
        );

    }
}
