package com.palombetas.api.gerartimes.mapper;

import com.palombetas.api.gerartimes.domain.dto.response.MatchResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.MatchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    MatchResponseDTO toMatchResponseDTO(MatchEntity matchEntity);
}
