package com.palombetas.api.gerartimes.validation.teams.swap;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerSwapDTO;

public interface IValidatorSwapPlayers {
    void validate(PlayerSwapDTO playerSwapDTO);
}
