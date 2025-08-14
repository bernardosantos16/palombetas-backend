package com.palombetas.api.gerartimes.domain.dto.response;

import java.util.UUID;

public record UserResponseDTO (
    UUID id,
    String login,
    String password
){

}
