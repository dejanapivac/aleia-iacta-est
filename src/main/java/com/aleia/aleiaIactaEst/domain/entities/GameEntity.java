package com.aleia.aleiaIactaEst.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_sequence")
    private Long id;
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "game_id",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
//    @JoinTable(
//            name = "game_player", // Name of the join table
//            joinColumns = @JoinColumn(name = "game_id"), // Foreign key column in the join table for GameEntity
//            inverseJoinColumns = @JoinColumn(name = "player_id") // Foreign key column in the join table for PlayerEntity
//    )
    private Set<PlayerEntity> playerEntity;
}
