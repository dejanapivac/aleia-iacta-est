package com.aleia.aleiaIactaEst.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendsEntityId implements Serializable {

    private Integer sessionId;

    private Integer playerId;
}
