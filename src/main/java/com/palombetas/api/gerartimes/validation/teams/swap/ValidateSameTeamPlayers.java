package com.palombetas.api.gerartimes.validation.teams.swap;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerSwapDTO;
import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import com.palombetas.api.gerartimes.domain.repository.PlayerRepository;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidateSameTeamPlayers implements IValidatorSwapPlayers {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void validate(PlayerSwapDTO playerSwapDTO) {
        var player1 = playerRepository.getReferenceById(playerSwapDTO.playerId1());
        var player2 = playerRepository.getReferenceById(playerSwapDTO.playerId2());

        TeamEntity team1 = player1.getTeam();
        TeamEntity team2 = player2.getTeam();

        if (team1.equals(team2)) {
            throw new GenericCustomException(
                    "about:blank",
                    "same team players",
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    "the players already same team."
            );
        }
    }
}
