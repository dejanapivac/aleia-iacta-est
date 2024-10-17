package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.PlayerEntity;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    List<PlayerEntity> findAll();

    Optional<PlayerEntity> findOne(Integer id);

    boolean exists(Integer id);
}
