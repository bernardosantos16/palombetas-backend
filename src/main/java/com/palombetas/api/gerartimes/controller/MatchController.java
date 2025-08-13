package com.palombetas.api.gerartimes.controller;

import com.palombetas.api.gerartimes.domain.dto.request.MatchRequestDTO;
import com.palombetas.api.gerartimes.domain.dto.response.MatchResponseDTO;
import com.palombetas.api.gerartimes.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(
            @RequestBody MatchRequestDTO matchRequestDTO
    ) {
        // Assuming you have a method to handle the match creation logic

        var matchResponse = matchService.createMatch(matchRequestDTO);

        return ResponseEntity.ok(matchResponse);
    }

    @PutMapping("/winner")
    public ResponseEntity<Void> setMatchWinner(
            @RequestParam Long matchId,
            @RequestParam Long teamId
    ) {
        matchService.setMatchWinner(teamId, matchId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getMatchById(
            @PathVariable Long id
    ) {
        var matchResponseDTO = matchService.getMatchById(id);
        return ResponseEntity.ok(matchResponseDTO);
    }

//    @GetMapping
//        public ResponseEntity<Page<MatchResponseDTO>> getAllMatches(@PageableDefault(size = 1) Pageable pageable){
//        var matches = matchService.getAllMatches(pageable);
//        return ResponseEntity.ok(matches);
//    }
}
