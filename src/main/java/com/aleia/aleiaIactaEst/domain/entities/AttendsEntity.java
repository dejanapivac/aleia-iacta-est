package com.aleia.aleiaIactaEst.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "attends")
public class AttendsEntity {

    @EmbeddedId
    private AttendsEntityId id;

    @OneToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @OneToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    private Boolean attend;
}
