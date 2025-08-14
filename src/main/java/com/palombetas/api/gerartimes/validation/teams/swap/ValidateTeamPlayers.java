package com.palombetas.api.gerartimes.validation.teams.swap;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerSwapDTO;
import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import com.palombetas.api.gerartimes.domain.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ValidateTeamPlayers implements IValidatorSwapPlayers {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void validate(PlayerSwapDTO playerSwapDTO) {
        var player1 = playerRepository.getReferenceById(playerSwapDTO.playerId1());
        var player2 = playerRepository.getReferenceById(playerSwapDTO.playerId2());

        TeamEntity team1 = player1.getTeam();
        TeamEntity team2 = player2.getTeam();

        if (team1 == null || team2 == null) {
            throw new GenericCustomException(
                    "about:blank",
                    "player(s) without a team",
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    "Both players must be assigned to teams to perform the swap."
            );
        }
    }
}
