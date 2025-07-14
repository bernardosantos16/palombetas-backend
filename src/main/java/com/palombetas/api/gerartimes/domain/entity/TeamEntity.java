package com.palombetas.api.gerartimes.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
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
@EqualsAndHashCode(of = "id")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private List<PlayerEntity> players;
    private Integer rating;
    private Date
}
