package com.palombetas.api.gerartimes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_team_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class PlayerTeamHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id")
    private MatchEntity match;

    public PlayerTeamHistoryEntity(PlayerEntity player, TeamEntity team, MatchEntity match) {
        this.player = player;
        this.team = team;
        this.match = match;
    }
}
