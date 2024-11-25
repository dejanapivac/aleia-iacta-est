package com.aleia.aleiaIactaEst.repositories;

import com.aleia.aleiaIactaEst.domain.entities.AttendsEntity;
import com.aleia.aleiaIactaEst.domain.entities.AttendsEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendsRepository extends JpaRepository<AttendsEntity, AttendsEntityId> {

    Optional<AttendsEntity> findBySessionIdAndPlayerId(Integer sessionId, Integer playerId);

    List<AttendsEntity> findBySessionId(Integer sessionId);

    List<AttendsEntity> findByPlayerId(Integer playerId);
}
