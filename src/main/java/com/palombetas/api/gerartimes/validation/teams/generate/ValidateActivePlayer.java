package com.palombetas.api.gerartimes.validation.teams.generate;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;
import com.palombetas.api.gerartimes.domain.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePlayer implements IValidatorGenerateTeams{
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void validate(ConfirmedPlayersListDTO confirmedPlayersListDTO) {
        var playersIds = confirmedPlayersListDTO.playersIds();
        if (playersIds == null){
            return;
        }
        playersIds.forEach(
                playerId -> {
                    var isActive = playerRepository.existsByIdAndIsActiveTrue(playerId);
                    if (!isActive) {
                        throw new RuntimeException("O jogador com ID " + playerId + " não está ativo.");
                    }
                }
        );

    }
}
