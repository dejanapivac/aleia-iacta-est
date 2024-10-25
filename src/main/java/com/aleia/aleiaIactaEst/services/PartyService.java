package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.dto.PlayerDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PartyService {
    PartyEntity save(PartyEntity partyEntity);

    List<PartyEntity> findAll();

    Optional<PartyEntity> findOne(Integer id);

    boolean exists(Integer id);

    Optional<PartyEntity> addPlayers(Set<PlayerEntity> newPlayersSet, Integer partyId);

    Optional<PartyEntity> deletePlayers(Set<Integer> playersToDelete, Integer partyId);

    Optional<PartyEntity> update(PartyEntity party, Integer partyId);
}
