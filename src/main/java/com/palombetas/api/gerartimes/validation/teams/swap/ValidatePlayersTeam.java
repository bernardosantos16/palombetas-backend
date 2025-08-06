package com.palombetas.api.gerartimes.validation.teams.swap;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerSwapDTO;
import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import com.palombetas.api.gerartimes.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatePlayersTeam implements IValidatorSwapPlayers {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void validate(PlayerSwapDTO playerSwapDTO) {
        var player1 = playerRepository.getReferenceById(playerSwapDTO.playerId1());
        var player2 = playerRepository.getReferenceById(playerSwapDTO.playerId2());

        TeamEntity team1 = player1.getTeam();
        TeamEntity team2 = player2.getTeam();

        if (team1.equals(team2)) {
            throw new RuntimeException("Os jogadores devem estar em times diferentes para serem trocados.");
        }
    }
}
