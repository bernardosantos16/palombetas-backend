package com.palombetas.api.gerartimes.controller;

import com.palombetas.api.gerartimes.domain.dto.request.MatchRequestDTO;
import com.palombetas.api.gerartimes.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {
    @Autowired
    MatchService matchService;

    @PostMapping
    public ResponseEntity<?> createMatch(
            @RequestBody MatchRequestDTO matchRequestDTO
    ) {
        // Assuming you have a method to handle the match creation logic

        var matchResponse = matchService.createMatch(matchRequestDTO);

        return ResponseEntity.ok(matchResponse);
    }

    @PutMapping("/{matchId}/{teamId}")
    public ResponseEntity<?> setMatchWinner(
            @PathVariable Long matchId,
            @PathVariable Long teamId
    ) {
        matchService.setMatchWinner(teamId, matchId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMatchById(
            @PathVariable Long id
    ) {
        var matchResponseDTO = matchService.getMatchById(id);
        return ResponseEntity.ok(matchResponseDTO);
    }

    @GetMapping
        public ResponseEntity<?> getAllMatches(@PageableDefault(size = 1) Pageable pageable){
        var matches = matchService.getAllMatches(pageable);
        return ResponseEntity.ok(matches);
    }
}
