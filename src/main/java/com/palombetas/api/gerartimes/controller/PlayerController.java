package com.palombetas.api.gerartimes.controller;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerRequestDTO;
import com.palombetas.api.gerartimes.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<?> createPlayer(
            @RequestBody PlayerRequestDTO playerRequestDTO
    ) {
        var playerResponseDTO = playerService.createPlayer(playerRequestDTO);
        return ResponseEntity.ok(playerResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) {
        var dto = playerService.getPlayerById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers(
            @PageableDefault(size = 10 , sort = "id") Pageable pageable
    ) {
        var players = playerService.getAllPlayers(pageable);
        return ResponseEntity.ok(players);
    }

    @PutMapping("/{playerId}/{matchId}")
    public ResponseEntity<?> setPlayerMvp(
            @PathVariable Long playerId,
            @PathVariable Long matchId
    ) {
        playerService.setPlayerMvp(playerId, matchId);
        return ResponseEntity.ok("Jogador melhor da partida sucesso");
    }

    @PutMapping("/{playerId}/rating")
    public ResponseEntity<?> changeRating(
            @PathVariable Long playerId,
            @RequestParam Double rating
    ) {
        playerService.changeRating(playerId, rating);
        return ResponseEntity.ok("Rating do jogador alterado com sucesso");
    }
}
