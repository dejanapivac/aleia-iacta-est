package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.GameEntity;

import java.util.List;
import java.util.Optional;

public interface GameService {

    GameEntity save(GameEntity gameEntity);

    List<GameEntity> list();

    Optional<GameEntity> findById(Integer gameId);

    Optional<GameEntity> update(GameEntity gameEntity, Integer id);

    void delete(Integer id);
}
