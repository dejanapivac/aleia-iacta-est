package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.repositories.RollRepository;
import com.aleia.aleiaIactaEst.services.RollService;
import org.springframework.stereotype.Service;

@Service
public class RollServiceImpl implements RollService {

    private RollRepository rollRepository;

    public RollServiceImpl(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }

    @Override
    public RollEntity save(RollEntity rollEntity) {
        return rollRepository.save(rollEntity);
    }
}
