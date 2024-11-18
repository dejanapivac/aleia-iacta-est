package com.aleia.aleiaIactaEst.services.impl.out;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;

import java.util.Optional;

public record AddPlayersResponse(PartyEntity partyEntity, String message) {
}
