package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.PartyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartyController {

    private PartyService partyService;

    private Mapper<PartyEntity, PartyDto> partyMapper;

    public PartyController(PartyService partyService, Mapper<PartyEntity, PartyDto> partyMapper) {
        this.partyMapper = partyMapper;
        this.partyService = partyService;
    }
}
