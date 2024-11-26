package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.AttendsEntity;
import com.aleia.aleiaIactaEst.domain.entities.AttendsEntityId;
import com.aleia.aleiaIactaEst.repositories.AttendsRepository;
import com.aleia.aleiaIactaEst.services.AttendsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendsServiceImpl implements AttendsService {

    private final AttendsRepository attendsRepository;

    @Override
    public AttendsEntity save(AttendsEntity attendsEntity) {
        return attendsRepository.save(attendsEntity);
    }

    @Override
    public Optional<AttendsEntity> findBySessionIdAndPlayerId(Integer sessionId, Integer playerId) {
        return attendsRepository.findBySessionIdAndPlayerId(sessionId, playerId);
    }

    @Override
    public List<AttendsEntity> findBySessionId(Integer sessionId) {
        return attendsRepository.findBySessionId(sessionId);
    }

    @Override
    public List<AttendsEntity> findByPlayerId(Integer playerId) {
        return attendsRepository.findByPlayerId(playerId);
    }

    @Override
    public void deleteBySessionIdAndPlayerId(Integer sessionId, Integer playerId) {
        Optional<AttendsEntity> expectedAttend = attendsRepository.findBySessionIdAndPlayerId(sessionId, playerId);
        expectedAttend.ifPresent(attendsRepository::delete);
    }

    @Override
    public Optional<AttendsEntity> updateBySessionIdAndPlayerId(AttendsEntity newAttend, AttendsEntityId attendId) {
        Optional<AttendsEntity> expectedEntity = attendsRepository.findById(attendId);
        return expectedEntity.map(attend -> {
            attend.setAttend(newAttend.getAttend());
            return attendsRepository.save(attend);
        });
    }
}
