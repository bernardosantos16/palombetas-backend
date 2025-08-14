package com.palombetas.api.gerartimes.service;

import com.palombetas.api.gerartimes.domain.business.MatchBusinessService;
import com.palombetas.api.gerartimes.domain.business.PlayerBusinessService;
import com.palombetas.api.gerartimes.domain.dto.request.PlayerRequestDTO;
import com.palombetas.api.gerartimes.domain.dto.response.PlayerResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;
import com.palombetas.api.gerartimes.mapper.PlayerMapper;
import com.palombetas.api.gerartimes.domain.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchBusinessService matchBusinessService;

    @Autowired
    private PlayerBusinessService playerBusinessService;

    @Autowired
    PlayerMapper playerMapper;


//    public PlayerEntity getPlayerEntityById(Long id) {
//        return playerBusinessService.getPlayerEntityById(id);
//    }


    @Transactional
    public PlayerResponseDTO createPlayer(PlayerRequestDTO playerRequestDTO){
        var player = new PlayerEntity(playerRequestDTO);
        playerRepository.save(player);
        return playerMapper.toPlayerResponseDTO(player);
    }

    @Transactional
    public void setPlayerMvp(Long playerId, Long matchId) {
        var match = matchBusinessService.getMatchEntityById(matchId);
        var playerMvp = playerBusinessService.getPlayerEntityById(playerId);
        var matchPlayerMvp = match.getPlayerMvp();

        if (matchPlayerMvp != null) {
            matchPlayerMvp.setTimesMvp(matchPlayerMvp.getTimesMvp() - 1);
        }

        playerMvp.setTimesMvp(playerMvp.getTimesMvp() + 1);
        match.setMvpPlayer(playerMvp);
    }

    @Transactional
    public void changeRating(Long playerId, Integer rating) {
        var player = playerBusinessService.getPlayerEntityById(playerId);
        player.setRating(rating);
    }

    public PlayerResponseDTO getPlayerById(Long id){
        var player = playerBusinessService.getPlayerEntityById(id);
        return playerMapper.toPlayerResponseDTO(player);
    }

    public Page<PlayerResponseDTO> getAllPlayers(Pageable pageable) {
        return playerRepository.findAllByIsActiveTrueOrderByNameAsc(pageable)
                .map(playerMapper::toPlayerResponseDTO);
    }

    public List<PlayerEntity> getListOfPlayersByIds(List<Long> playersIds) {
        return playerBusinessService.getListOfPlayersByIds(playersIds);
    }

    public List<PlayerEntity> getAllActivePlayers() {
        return playerBusinessService.getAllActivePlayers();
    }

    public void cleanTeamPlayers() {
        playerRepository.findAllByIsActiveTrue()
                .forEach(player -> player.setTeam(null));
    }

    @Transactional
    public void deletePlayer(Long playerId) {
        var player = playerBusinessService.getPlayerEntityById(playerId);
        player.deletePlayer();
    }

    @Transactional
    public void activatePlayer(Long playerId) {
        var player = playerBusinessService.getPlayerEntityById(playerId);
        player.activatePlayer();
    }

}
