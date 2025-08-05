package com.palombetas.api.gerartimes.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/* Toda terça feira é criado um novo time, e o time é composto por 5 jogadores
 * os times devem ser o mais equilibrados possíveis, ou seja
 * a soma dos ratings dos jogadores deve ser a mais próxima possível entre os times
 * e a soma das vezes que os jogadores foram campeões deve ser a mais próxima possível entre os times
 */

@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Setter
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<PlayerEntity> players;

    @Setter
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private MatchEntity match;

    public TeamEntity(MatchEntity match, String name) {
        this.match = match;
        //this.rating = rating;
        this.name = name;
        this.players = new ArrayList<>();
    }

    public void setCalculateTeamForce() {
        this.setRating(
                this.getPlayers().stream()
                        .mapToDouble(PlayerEntity::calculatePlayerForce)
                        .sum()
        );
    }
}
