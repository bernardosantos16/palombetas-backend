package com.palombetas.api.gerartimes.service;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;
import com.palombetas.api.gerartimes.domain.dto.request.PlayerSwapDTO;
import com.palombetas.api.gerartimes.domain.dto.response.PlayerResponseDTO;
import com.palombetas.api.gerartimes.domain.dto.response.TeamResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.MatchEntity;
import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;
import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import com.palombetas.api.gerartimes.mapper.PlayerMapper;
import com.palombetas.api.gerartimes.mapper.TeamMapper;
import com.palombetas.api.gerartimes.repository.MatchRepository;
import com.palombetas.api.gerartimes.repository.PlayerRepository;
import com.palombetas.api.gerartimes.repository.TeamRepository;
import com.palombetas.api.gerartimes.validation.teams.generate.IValidatorGenerateTeams;
import com.palombetas.api.gerartimes.validation.teams.swap.IValidatorSwapPlayers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired List<IValidatorSwapPlayers> swapPlayerValidators;

    @Autowired List<IValidatorGenerateTeams> generateTeamsValidators;


    public TeamEntity getTeamEntityById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com id: " + id));
    }

//    private void validateTotalPlayers(Integer totalPlayers) {
//        if (totalPlayers < 10) {
//            throw new RuntimeException("Número de jogadores insuficiente para formar ao menos dois times. É necessário pelo menos 10 jogadores.");
//        }
//    }

    private Integer calculateTotalTeams(Integer totalPlayers, Integer playersPerTeam) {
        return totalPlayers / playersPerTeam;
    }



    private List<List<PlayerEntity>> distributePlayersIntoBalancedTeams(List<PlayerEntity> players, Integer totalTeams, Integer totalPlayers){
        players.sort(Comparator.comparing(PlayerEntity::calculatePlayerForce).reversed());

        List<List<PlayerEntity>> teamPlayerEntities = new ArrayList<>();

        for (int i = 0; i < totalTeams; i++) {
            teamPlayerEntities.add(new ArrayList<>());
        }

        for (int i = 0; i < totalPlayers; i++) {
            int teamIndex = i % totalTeams;
            if ((i / totalTeams) % 2 == 1) {
                teamIndex = totalTeams - 1 - teamIndex;
            }
            teamPlayerEntities.get(teamIndex).add(players.get(i));
        }

        return teamPlayerEntities;

    }

    private List<TeamEntity> persistTeams(List<List<PlayerEntity>> teamPlayerEntities, MatchEntity match) {
        List<TeamResponseDTO> teamsResponse = new ArrayList<>();
        List<TeamEntity> teamEntityList = new ArrayList<>();
        for (int i = 0; i < teamPlayerEntities.size(); i++) {
            List<PlayerEntity> teamPlayers = teamPlayerEntities.get(i);
            String teamName = "Time " + (i + 1);

            // Criar e associar time à partida
            TeamEntity teamEntity = new TeamEntity(match, teamName);

            // Associar jogadores ao time
            teamPlayers.forEach(p -> p.setTeam(teamEntity));
            teamEntity.setPlayers(teamPlayers);
            teamEntity.setCalculateTeamForce();

            // Persistir equipe e jogadores
            teamRepository.save(teamEntity);
            teamEntityList.add(teamEntity);

        }

        return teamEntityList;
    }

    private List<TeamResponseDTO> generateTeamsResponse(List<TeamEntity> teamEntityList) {
        List<TeamResponseDTO> teamsResponse = new ArrayList<>();

        teamEntityList.forEach(
            teamEntity -> {
                List<PlayerResponseDTO> playerDTOs = teamEntity.getPlayers().stream()
                        .map(playerMapper::toPlayerResponseDTO)
                        .toList();

                teamsResponse.add(new TeamResponseDTO(
                        teamEntity.getId(),
                        teamEntity.getName(),
                        teamEntity.getRating(),
                        playerDTOs
                ));
            }
        );

        return teamsResponse;
    }


    @Transactional
    public List<TeamResponseDTO> generateTeams(
            ConfirmedPlayersListDTO confirmedPlayersList
    ) {
        // Buscar a partida
        MatchEntity match = matchService.getMatchEntityById(confirmedPlayersList.matchId());

        // Buscar os jogadores confirmados no banco

        List<PlayerEntity> players = playerService.getListOfPlayersByIds(confirmedPlayersList.playersIds());

        int totalPlayers = players.size();
        int playersPerTeam = confirmedPlayersList.playersPerTeam();

        generateTeamsValidators.forEach(v -> v.validate(confirmedPlayersList));

        int totalTeams = this.calculateTotalTeams(totalPlayers, playersPerTeam);

        var teamPlayerEntities = this.distributePlayersIntoBalancedTeams(players, totalTeams, totalPlayers);

        var teamEntityList = this.persistTeams(teamPlayerEntities, match);

        return this.generateTeamsResponse(teamEntityList);
    }

    @Transactional
    public void swapPlayers(PlayerSwapDTO playerSwapDTO) {
        var playerId1 = playerSwapDTO.playerId1();
        var playerId2 = playerSwapDTO.playerId2();

        PlayerEntity player1 = playerService.getPlayerEntityById(playerId1);
        PlayerEntity player2 = playerService.getPlayerEntityById(playerId2);

        TeamEntity team1 = player1.getTeam();
        TeamEntity team2 = player2.getTeam();

        swapPlayerValidators.forEach(v -> v.validate(playerSwapDTO));

        // Trocar jogadores entre os times
        team1.getPlayers().remove(player1);
        team1.getPlayers().add(player2);
        player2.setTeam(team1);

        team2.getPlayers().remove(player2);
        team2.getPlayers().add(player1);
        player1.setTeam(team2);

        // Atualizar times no banco de dados
        teamRepository.save(team1);
        teamRepository.save(team2);

        team1.setCalculateTeamForce();
        team2.setCalculateTeamForce();

    }

    public TeamResponseDTO getTeamById(Long id) {
        TeamEntity team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));
        return teamMapper.toTeamResponseDTO(team);
    }
}
