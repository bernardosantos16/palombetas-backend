package com.palombetas.api.gerartimes.domain.entity;

import com.palombetas.api.gerartimes.domain.dto.request.MatchRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "matches")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime matchDate;

    @OneToMany(mappedBy = "match")
    private List<TeamEntity> teams;

    @OneToOne
    @JoinColumn(name = "winner_id")
    private TeamEntity winner;

    @OneToOne
    @JoinColumn(name = "player_mvp_id")
    @Setter
    private PlayerEntity playerMvp;

    public MatchEntity(MatchRequestDTO matchRequestDTO) {
        this.matchDate = matchRequestDTO.matchDate();
    }

    public void setWinner(TeamEntity winner) {
        var players = winner.getPlayers();
        players.forEach(player -> {
            player.setTimesChampion(player.getTimesChampion() + 1);
        });
        this.winner = winner;
    }
    public void setMvpPlayer(PlayerEntity player) {
        this.playerMvp = player;
    }

}
