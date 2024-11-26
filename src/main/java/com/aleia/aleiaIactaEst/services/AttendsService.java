package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.AttendsEntity;
import com.aleia.aleiaIactaEst.domain.entities.AttendsEntityId;

import java.util.List;
import java.util.Optional;

public interface AttendsService {

    AttendsEntity save(AttendsEntity attendsEntity);

    Optional<AttendsEntity> findBySessionIdAndPlayerId(Integer sessionId, Integer playerId);

    List<AttendsEntity> findBySessionId(Integer sessionId);

    List<AttendsEntity> findByPlayerId(Integer playerId);

    void deleteBySessionIdAndPlayerId(Integer sessionId, Integer playerId);

    Optional<AttendsEntity> updateBySessionIdAndPlayerId(AttendsEntity newAttend, AttendsEntityId attendId);
}
