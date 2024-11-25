package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.AttendsEntity;
import com.aleia.aleiaIactaEst.domain.entities.SessionEntity;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    SessionEntity save(SessionEntity session);

    Optional<SessionEntity> findById(Integer id);

    void deleteById(Integer id);
}
