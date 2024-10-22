package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.services.PartyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;

    public PartyServiceImpl(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;

    }

    @Override
    public PartyEntity save(PartyEntity partyEntity) {
        return partyRepository.save(partyEntity);
    }

    @Override
    public List<PartyEntity> findAll() {
        return StreamSupport.stream(partyRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }
}
