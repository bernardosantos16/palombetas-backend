package com.palombetas.api.gerartimes.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jogadores")
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer rating;
    private Integer timesChampion;


    private Boolean isActive;
}
