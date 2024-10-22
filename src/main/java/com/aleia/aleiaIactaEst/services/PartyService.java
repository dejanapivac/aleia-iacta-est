package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;

import java.util.List;

public interface PartyService {
    PartyEntity save(PartyEntity partyEntity);

    List<PartyEntity> findAll();
}
