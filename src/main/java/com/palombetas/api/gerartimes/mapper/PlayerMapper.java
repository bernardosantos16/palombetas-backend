package com.palombetas.api.gerartimes.mapper;

import com.palombetas.api.gerartimes.domain.dto.response.PlayerResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "timesChampion", source = "timesChampion")
    @Mapping(target = "timesMvp", source = "timesMvp")
    PlayerResponseDTO toPlayerResponseDTO(PlayerEntity playerEntity);

}
