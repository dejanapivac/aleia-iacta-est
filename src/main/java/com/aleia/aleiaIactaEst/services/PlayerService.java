package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    List<PlayerEntity> findAll();

    Optional<PlayerEntity> findOne(Integer id);

    boolean exists(Integer id);

    void delete(Integer id);

    PlayerEntity partialUpdate(Integer id, PlayerEntity playerEntity);

    Optional<PlayerEntity> update(PlayerEntity playerEntity, Integer playerEntityId);
}
