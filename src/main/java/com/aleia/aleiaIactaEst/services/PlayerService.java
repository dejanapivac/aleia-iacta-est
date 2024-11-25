package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    List<PlayerEntity> findAll();

    Optional<PlayerEntity> findOne(Integer id);

    List<PlayerEntity> findAllByIds(List<Integer> playerIds);

    boolean exists(Integer id);

    void deleteById(Integer id);

    PlayerEntity partialUpdate(Integer id, PlayerEntity playerEntity);

    Optional<PlayerEntity> update(PlayerEntity playerEntity, Integer playerEntityId);
}
