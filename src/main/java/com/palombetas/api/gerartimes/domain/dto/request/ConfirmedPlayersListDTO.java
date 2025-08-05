package com.palombetas.api.gerartimes.domain.dto.request;

import java.util.List;

public record ConfirmedPlayersListDTO(
        List<Long> playersIds
) {
}
