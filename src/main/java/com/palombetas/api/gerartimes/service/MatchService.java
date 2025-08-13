package com.palombetas.api.gerartimes.service;

import com.palombetas.api.gerartimes.domain.dto.request.MatchRequestDTO;
import com.palombetas.api.gerartimes.domain.dto.response.MatchResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.MatchEntity;
import com.palombetas.api.gerartimes.mapper.MatchMapper;
import com.palombetas.api.gerartimes.domain.business.MatchBusinessService;
import com.palombetas.api.gerartimes.domain.business.TeamBusinessService;
import com.palombetas.api.gerartimes.domain.repository.MatchRepository;
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
    private MatchBusinessService matchBusinessService;

    @Autowired
    private TeamBusinessService teamBusinessService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MatchMapper matchMapper;


    public MatchEntity getMatchEntityById(Long id) {
        return matchBusinessService.getMatchEntityById(id);
    }


    @Transactional
    public MatchResponseDTO createMatch(MatchRequestDTO matchRequestDTO) {
        var matchEntity = new MatchEntity(matchRequestDTO);

        playerService.cleanTeamPlayers();

        matchRepository.save(matchEntity);
        // Convert saved entity back to DTO and return
        return matchMapper.toMatchResponseDTO(matchEntity);
    }

    @Transactional
    public void setMatchWinner(Long teamId, Long matchId) {
        var team = teamBusinessService.getTeamEntityById(teamId);
        var match = this.getMatchEntityById(matchId);

        match.setWinner(team);
        matchRepository.save(match);
    }

    public MatchResponseDTO getMatchById(Long id) {
        var match = this.getMatchEntityById(id);
        return matchMapper.toMatchResponseDTO(match);
    }

    public Page<MatchResponseDTO> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable)
                .map(matchMapper::toMatchResponseDTO);
    }
}
