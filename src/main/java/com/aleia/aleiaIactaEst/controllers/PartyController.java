package com.aleia.aleiaIactaEst.controllers;

import com.aleia.aleiaIactaEst.domain.dto.PartyDto;
import com.aleia.aleiaIactaEst.domain.entities.PartyEntity;
import com.aleia.aleiaIactaEst.mappers.Mapper;
import com.aleia.aleiaIactaEst.services.PartyService;
import com.aleia.aleiaIactaEst.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/party")
public class PartyController {

    private PartyService partyService;

    private Mapper<PartyEntity, PartyDto> partyMapper;

    public PartyController(PartyService partyService, Mapper<PartyEntity, PartyDto> partyMapper) {
        this.partyMapper = partyMapper;
        this.partyService = partyService;
    }

    @PostMapping
    public ResponseEntity<PartyDto> createParty(@RequestBody PartyDto party) {
        PartyEntity partyEntity = partyMapper.mapFrom(party);
        PartyEntity savedPartyEntity = partyService.save(partyEntity);
        return new ResponseEntity<>(partyMapper.mapTo(savedPartyEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PartyDto> listParties() {
        List<PartyEntity> parties = partyService.findAll();
        return parties.stream()
                .map(partyMapper::mapTo)
                .collect(Collectors.toList());
    }
}
