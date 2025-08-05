package com.palombetas.api.gerartimes.mapper;

import com.palombetas.api.gerartimes.domain.dto.response.TeamResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface TeamMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "players", source = "players")
    TeamResponseDTO toTeamResponseDTO(TeamEntity teamEntity);


}
