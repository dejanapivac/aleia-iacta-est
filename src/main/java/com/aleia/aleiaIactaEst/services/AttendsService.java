package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.AttendsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AttendsService {

    AttendsEntity save(AttendsEntity attendsEntity);

    Optional<AttendsEntity> findBySessionIdAndPlayerId(Integer sessionId, Integer playerId);

    List<AttendsEntity> findBySessionId(Integer sessionId);

    void deleteBySessionIdAndPlayerId(Integer sessionId, Integer playerId);

    Optional<AttendsEntity> updateBySessionIdAndPlayerId(Integer sessionId, Integer playerId);
}
