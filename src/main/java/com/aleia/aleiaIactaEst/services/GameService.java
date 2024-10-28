package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.GameEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GameService {

    GameEntity save(GameEntity gameEntity);

    List<GameEntity> list();

    Optional<GameEntity> findOne(Integer gameId);

    Optional<GameEntity> update(GameEntity gameEntity, Integer id);
}
