package com.aleia.aleiaIactaEst.services;

import com.aleia.aleiaIactaEst.domain.entities.RollEntity;

import java.util.List;
import java.util.Optional;

public interface RollService {

    RollEntity save(RollEntity rollEntity);

    Optional<RollEntity> findById(Integer id);

    List<RollEntity> findAll();

    void delete(Integer id);

    Optional<RollEntity> edit(RollEntity rollEntity, Integer id);
}
