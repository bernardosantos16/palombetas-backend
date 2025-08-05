package com.palombetas.api.gerartimes.service;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerRequestDTO;
import com.palombetas.api.gerartimes.domain.dto.response.PlayerResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;
import com.palombetas.api.gerartimes.mapper.PlayerMapper;
import com.palombetas.api.gerartimes.repository.MatchRepository;
import com.palombetas.api.gerartimes.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    PlayerMapper playerMapper;

    @Transactional
    public PlayerResponseDTO createPlayer(PlayerRequestDTO playerRequestDTO){
        var player = new PlayerEntity(playerRequestDTO);
        playerRepository.save(player);
        return playerMapper.toPlayerResponseDTO(player);
    }

    @Transactional
    public void setPlayerMvp(Long playerId, Long matchId) {
        var match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Nenhuma partida encontrado"));


        var playerMvp = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado"));

        var matchPlayerMvp = match.getPlayerMvp();

        if (matchPlayerMvp != null) {
            matchPlayerMvp.setTimesMvp(matchPlayerMvp.getTimesMvp() - 1);
        }

        playerMvp.setTimesMvp(playerMvp.getTimesMvp() + 1);
        match.setMvpPlayer(playerMvp);
    }

    @Transactional
    public void changeRating(Long playerId, Double rating) {
        var player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Nenhum jogador encontrado"));
        player.setRating(rating);
    }

    public PlayerResponseDTO getPlayerById(Long id){
        var player = playerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Nenhum jogador encontrado")
        );
        return playerMapper.toPlayerResponseDTO(player);
    }

    public Page<PlayerResponseDTO> getAllPlayers(Pageable pageable) {
        return playerRepository.findAllByIsActiveTrueOrderByNameAsc(pageable)
                .map(playerMapper::toPlayerResponseDTO);
    }
}
