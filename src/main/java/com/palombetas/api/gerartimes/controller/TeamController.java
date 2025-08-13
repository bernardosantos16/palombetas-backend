package com.palombetas.api.gerartimes.controller;

import com.palombetas.api.gerartimes.domain.dto.request.ConfirmedPlayersListDTO;
import com.palombetas.api.gerartimes.domain.dto.request.PlayerSwapDTO;
import com.palombetas.api.gerartimes.domain.dto.response.TeamResponseDTO;
import com.palombetas.api.gerartimes.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/generate-teams")
    public ResponseEntity<List<TeamResponseDTO>> createTeams(
            @RequestBody ConfirmedPlayersListDTO confirmedPlayersListDTO
    ){
        var teams = teamService.generateTeams(confirmedPlayersListDTO);

        return ResponseEntity.ok(teams);
    }

    @PutMapping("/swap-players")
    public ResponseEntity<String> swapPlayers(
            @RequestBody PlayerSwapDTO playerSwapDTO
    ) {
        teamService.swapPlayers(playerSwapDTO);
        return ResponseEntity.ok("Jogadores trocados com sucesso");
    }

//    @GetMapping("/{teamId}")
//    public ResponseEntity<TeamResponseDTO> getTeamById(
//            @PathVariable Long teamId
//    ) {
//        var teamResponseDTO = teamService.getTeamById(teamId);
//        return ResponseEntity.ok(teamResponseDTO);
//    }

}
