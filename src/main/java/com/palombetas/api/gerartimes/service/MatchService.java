package com.palombetas.api.gerartimes.service;

import com.palombetas.api.gerartimes.domain.dto.request.MatchRequestDTO;
import com.palombetas.api.gerartimes.domain.dto.response.MatchResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.MatchEntity;
import com.palombetas.api.gerartimes.mapper.MatchMapper;
import com.palombetas.api.gerartimes.repository.MatchRepository;
import com.palombetas.api.gerartimes.repository.PlayerRepository;
import com.palombetas.api.gerartimes.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private MatchMapper matchMapper;

    @Transactional
    public MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO) {
        var matchEntity = new MatchEntity(matchRequestDTO);
        var players = playerRepository.findAllByIsActiveTrue();
        players.forEach(p -> p.setTeam(null));
        matchRepository.save(matchEntity);
        // Convert saved entity back to DTO and return
        return matchMapper.toMatchResponseDTO(matchEntity);
    }

    @Transactional
    public void setMatchWinner(Long teamId, Long matchId) {
        var team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Time não encotrado")
                );
        var match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Nenhuma partida encontrada"));

        match.setWinner(team);
    }

    public MatchResponseDTO getMatchById(Long id) {
        var match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma partida encontrada"));
        return matchMapper.toMatchResponseDTO(match);
    }

    public Page<MatchResponseDTO> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable)
                .map(matchMapper::toMatchResponseDTO);
    }
}
