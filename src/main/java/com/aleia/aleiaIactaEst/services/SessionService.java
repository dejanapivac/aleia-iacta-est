package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.SessionEntity;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    SessionEntity save(SessionEntity session);

    List<SessionEntity> listSessions();

    Optional<SessionEntity> findById(Integer id);

    Optional<SessionEntity> updateSession(Integer id);
}
