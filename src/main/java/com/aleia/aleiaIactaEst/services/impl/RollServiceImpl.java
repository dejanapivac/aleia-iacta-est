package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.RollEntity;
import com.aleia.aleiaIactaEst.repositories.RollRepository;
import com.aleia.aleiaIactaEst.services.RollService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RollServiceImpl implements RollService {

    private final RollRepository rollRepository;

    public RollServiceImpl(RollRepository rollRepository) {
        this.rollRepository = rollRepository;
    }

    @Override
    public RollEntity save(RollEntity rollEntity) {
        return rollRepository.save(rollEntity);
    }

    @Override
    public Optional<RollEntity> findById(Integer id) {
        return rollRepository.findById(id);
    }

    @Override
    public List<RollEntity> findAll() {
        return rollRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        rollRepository.deleteById(id);
    }

    @Override
    public Optional<RollEntity> edit(RollEntity rollEntity, Integer id) {
        Optional<RollEntity> expectedRoll = rollRepository.findById(id);
        return expectedRoll.map(roll -> {
            roll.setDiceRollOption(rollEntity.getDiceRollOption());
            return rollRepository.save(roll);
        });
    }
}
