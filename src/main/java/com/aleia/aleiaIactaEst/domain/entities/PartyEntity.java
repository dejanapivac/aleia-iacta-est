package com.aleia.aleiaIactaEst.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "party")
public class PartyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "party_players", joinColumns = @JoinColumn(name = "party_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<PlayerEntity> players;
}
