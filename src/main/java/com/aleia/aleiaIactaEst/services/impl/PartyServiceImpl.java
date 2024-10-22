package com.aleia.aleiaIactaEst.services.impl;

import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.repositories.PartyRepository;
import com.aleia.aleiaIactaEst.services.PartyService;
import org.springframework.stereotype.Service;

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
}
