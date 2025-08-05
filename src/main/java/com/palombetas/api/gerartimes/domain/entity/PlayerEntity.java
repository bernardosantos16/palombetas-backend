package com.palombetas.api.gerartimes.domain.entity;

import com.palombetas.api.gerartimes.domain.dto.request.PlayerRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double rating;
    private Integer timesChampion;
    private Integer timesMvp;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    private Boolean isActive;

    public PlayerEntity(PlayerRequestDTO playerRequestDTO){
        this.name = playerRequestDTO.name();
        this.rating = playerRequestDTO.rating();
        this.timesChampion = 0;
        this.timesMvp = 0;
        this.team = null;
        this.isActive = true;
    }

    public Double calculatePlayerForce() {
        return this.getRating() * 1.0 +
                this.getTimesChampion() * 0.5 +
                this.getTimesMvp() * 0.3;
    }
}
