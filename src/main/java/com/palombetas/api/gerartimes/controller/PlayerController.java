package com.palombetas.api.gerartimes.controller;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerRequestDTO;
import com.palombetas.api.gerartimes.domain.dto.response.PlayerResponseDTO;
import com.palombetas.api.gerartimes.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<PlayerResponseDTO> createPlayer(
            @RequestBody PlayerRequestDTO playerRequestDTO
    ) {
        var playerResponseDTO = playerService.createPlayer(playerRequestDTO);
        return ResponseEntity.ok(playerResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable Long id) {
        var playerResponseDTO = playerService.getPlayerById(id);
        return ResponseEntity.ok(playerResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PlayerResponseDTO>> getAllPlayers(
            @PageableDefault(/*size = 10 ,*/ sort = "id") Pageable pageable
    ) {
        var players = playerService.getAllPlayers(pageable);
        return ResponseEntity.ok(players);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> activatePlayer(@PathVariable Long id) {
        playerService.activatePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/mvp")
    public ResponseEntity<String> setPlayerMvp(
            @RequestParam Long playerId,
            @RequestParam Long matchId
    ) {
        playerService.setPlayerMvp(playerId, matchId);
        return ResponseEntity.ok("Jogador melhor da partida sucesso");
    }

    @PutMapping("/{playerId}/rating")
    public ResponseEntity<String> changeRating(
            @PathVariable Long playerId,
            @RequestParam Double rating
    ) {
        playerService.changeRating(playerId, rating);
        return ResponseEntity.ok("Rating do jogador alterado com sucesso");
    }
}
