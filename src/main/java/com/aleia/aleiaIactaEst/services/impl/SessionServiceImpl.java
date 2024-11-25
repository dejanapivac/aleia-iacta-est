package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.AttendsEntity;
import com.aleia.aleiaIactaEst.domain.entities.SessionEntity;
import com.aleia.aleiaIactaEst.repositories.SessionRepository;
import com.aleia.aleiaIactaEst.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public SessionEntity save(SessionEntity session) {
        return sessionRepository.save(session);
    }

    @Override
    public Optional<SessionEntity> findById(Integer id) {
        return sessionRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        sessionRepository.deleteById(id);
    }
}
